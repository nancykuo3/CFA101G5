package com.order.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.model.OrdDetVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.store.model.StoreVO;

@WebServlet("/order/storeOrd.do")
public class StoreOrdServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		StoreVO storeVO = (StoreVO)session.getAttribute("StoreVO");
		Integer storeId = storeVO.getStoreId();
		
		//依訂單狀態列出訂單
		if ("order_type".equals(action)) {
			try {
				String status = req.getParameter("type");
				if (status.equals("4")) {
					status = "4 or 5"; //"已完成"包含狀態代碼4(已出貨)和5(未取貨)
				}
				OrderService ordSvc = new OrderService();
				List<OrderVO> list  = ordSvc.listOrdsByStatus(storeId, status);
				req.setAttribute("list", list);
				req.setAttribute("type", req.getParameter("type"));
	
				String url = "/front-end/store/order/order_type.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		//以訂單編號查詢訂單
		if ("find_by_ordId".equals(action)) {
			try {
				String str = req.getParameter("ordId");
				if (str == null || (str.trim()).length() == 0) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/order/order_all.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer ordId = new Integer(req.getParameter("ordId"));
				OrderService ordSvc = new OrderService();
				OrderVO orderVO = ordSvc.findByPrimaryKey(ordId);
				
				if (orderVO != null && !storeId.equals(orderVO.getStoreId())) {
					orderVO = null;
				}
				req.setAttribute("orderVO", orderVO);
				req.setAttribute("ordId", ordId);
				String url = "/front-end/store/order/order_search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//查詢訂單明細	
		if ("order_details".equals(action)) {
			try {
				Integer ordId = new Integer(req.getParameter("ordId"));
				OrderService ordSvc = new OrderService();
				List<OrdDetVO> list = ordSvc.getOrdDetsByOrdId(ordId);
				OrderVO orderVO = ordSvc.findByPrimaryKey(ordId);

				if (orderVO == null || !storeId.equals(orderVO.getStoreId())) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/order/order_all.jsp");
					failureView.forward(req, res);
					return;
				}			
				req.setAttribute("order_items", list);
				req.setAttribute("orderVO", orderVO);
				req.setAttribute("type", req.getParameter("type"));
				req.setAttribute("whichPage", req.getParameter("whichPage"));
				

				String url = "/front-end/store/order/order_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}	
		}
		
		//更新訂單狀態		
		if ("confirm_order".equals(action) || "cancel_order".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			try {
				Integer ordId = new Integer(req.getParameter("ordId"));
				OrderService ordSvc = new OrderService();
				//確認訂單
				if ("confirm_order".equals(action)) {
					ordSvc.confirmOrder(3, ordId); //訂單狀態3:出貨準備中
				}
				//取消訂單
				else if ("cancel_order".equals(action)) {
					ordSvc.cancelOrder(1, ordId); //訂單狀態1:已取消
				}

				List<OrdDetVO> list = ordSvc.getOrdDetsByOrdId(ordId);
				OrderVO orderVO = ordSvc.findByPrimaryKey(ordId);
				req.setAttribute("order_items", list);
				req.setAttribute("orderVO", orderVO);
				req.setAttribute("type", req.getParameter("type"));
				req.setAttribute("whichPage", req.getParameter("whichPage"));
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//出貨
		if ("order_shipment".equals(action)) {
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				Integer ordId = new Integer (req.getParameter("ordId"));
				String trackNo = req.getParameter("trackNo");
				OrderService ordSvc = new OrderService();
				if (trackNo == null || (trackNo.trim()).length() == 0) {
					String errorMsg = "請輸入貨運追蹤號碼";
					req.setAttribute("errorMsg", errorMsg);
					
					List<OrdDetVO> list = ordSvc.getOrdDetsByOrdId(ordId);
					OrderVO orderVO = ordSvc.findByPrimaryKey(ordId);
					
					req.setAttribute("order_items", list);
					req.setAttribute("orderVO", orderVO);
					req.setAttribute("type", req.getParameter("type"));
					req.setAttribute("whichPage", req.getParameter("whichPage"));

					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				ordSvc.updateStatus(ordId, trackNo);
				
				List<OrdDetVO> list = ordSvc.getOrdDetsByOrdId(ordId);
				OrderVO orderVO = ordSvc.findByPrimaryKey(ordId);
				
				req.setAttribute("order_items", list);
				req.setAttribute("orderVO", orderVO);
				req.setAttribute("type", req.getParameter("type"));
				req.setAttribute("whichPage", req.getParameter("whichPage"));
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
