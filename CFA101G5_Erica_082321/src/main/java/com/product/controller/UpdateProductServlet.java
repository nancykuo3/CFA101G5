package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/product/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("getOneProduct_A".equals(action) || "getOneProduct_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			try {
				Integer prodid = new Integer(request.getParameter("prodid"));
				HttpSession session = request.getSession();
				String storeid = (String) session.getAttribute("storeid");
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = prodSvc.getOneProduct(prodid);
				
				request.setAttribute("productVO", prodVO);
				session.setAttribute("storeid", storeid);
				String url = "/front-end/product/updateProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
		
		if("updateProduct".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			try {
				Integer prodid = new Integer(request.getParameter("prodid"));
				Integer price = new Integer(request.getParameter("price"));
				Integer prodQty = new Integer(request.getParameter("prodQty"));
				String intro = request.getParameter("intro");
				
				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(prodid);
				prodVO.setPrice(price);
				prodVO.setProdQty(prodQty);
				prodVO.setIntro(intro);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return; // 程式中斷
				}
				
				ProductService prodSvc = new ProductService();
				prodVO = prodSvc.updateProduct(prodid, price, prodQty, intro);
				
				request.setAttribute("prodVO", prodVO);
//				String url = "/front-end/product/listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
	}
}
