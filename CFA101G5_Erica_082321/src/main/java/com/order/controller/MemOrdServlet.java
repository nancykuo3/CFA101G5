package com.order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemManagementService;
import com.mem.model.MemVO;
import com.order.model.OrderService;


@WebServlet("/order/memOrd.do")
public class MemOrdServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("get_memId".equals(action)) {
			try {
				Integer memId = new Integer(req.getParameter("memId"));
				MemManagementService memSvc = new MemManagementService();
				MemVO memVO = memSvc.getOneMem(memId);
				HttpSession session = req.getSession();
				session.setAttribute("memVO", memVO);
				String requestURL = req.getParameter("requestURL");
				if (!requestURL.equals("")) {
					res.sendRedirect("/CFA101G5" + requestURL);
				} else {
				res.sendRedirect("/CFA101G5/front-end/mem/order/select_page.jsp");
				}
			} catch (Exception e){
				throw new ServletException (e);
			}
		}
		
		//取消訂單	
		if ("cancel_order".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			String whichPage = req.getParameter("whichPage");
			Integer ordId = new Integer(req.getParameter("ordId"));
				
			try {	
				OrderService ordSvc = new OrderService();
				ordSvc.cancelOrder(1, ordId); //訂單狀態1:已取消
				res.sendRedirect("/CFA101G5" + requestURL + "?whichPage=" + whichPage);

			} catch (Exception e) {
					throw new ServletException(e);
			}
		}
		
		//前往信用卡付款頁面
		if ("pay_by_card".equals(action)) {

			try {
				req.setAttribute("requestURL", req.getParameter("requestURL"));
				req.setAttribute("whichPage", req.getParameter("whichPage"));
				req.setAttribute("ordId", req.getParameter("ordId"));
				req.setAttribute("amount", req.getParameter("amount"));
				RequestDispatcher view = req.getRequestDispatcher("/front-end/mem/order/pay.jsp");
				view.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		//進行信用卡付款
		if ("pay_finish".equals(action)) {

			String requestURL = req.getParameter("requestURL");
			String whichPage = req.getParameter("whichPage");
			System.out.println(requestURL+whichPage);
			Integer ordId = new Integer(req.getParameter("ordId"));
			System.out.println(ordId);
		
			try {
				OrderService ordSvc = new OrderService();
				ordSvc.confirmOrder(3, ordId); //訂單狀態3:出貨準備中
				res.sendRedirect("/CFA101G5" + requestURL + "?whichPage=" + whichPage);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
