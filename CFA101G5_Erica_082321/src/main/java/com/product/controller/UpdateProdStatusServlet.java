package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class UpdateProdStatusServlet
 */
@WebServlet("/product/UpdateProdStatusServlet")
public class UpdateProdStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProdStatusServlet() {
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

		if ("getProds_By_Prodid".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				Integer prodid = new Integer(request.getParameter("prodid"));

				ProductService prodSvc = new ProductService();
				ProductVO prodVO = prodSvc.getOneProduct(prodid);
				System.out.println(prodVO);

				request.setAttribute("productVO", prodVO);
				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
		
		if("update_status".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				Integer prodid = new Integer(request.getParameter("prodid"));
				Byte status = new Byte(request.getParameter("status"));

				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(prodid);
				prodVO.setStatus(status);
				
				ProductService prodSvc = new ProductService();
				prodVO = prodSvc.updateStatus(prodid, status);
		System.out.println(prodVO);
				
				request.setAttribute("productVO", prodVO);
				String url = "/back-end/product/prodInfoQuery.jsp";
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
	}

}
