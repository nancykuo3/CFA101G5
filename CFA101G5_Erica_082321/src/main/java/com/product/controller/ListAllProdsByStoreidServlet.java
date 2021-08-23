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
 * Servlet implementation class ListAllProdsByStoreidServlet
 */
@WebServlet("/product/ListAllProdsByStoreidServlet")
public class ListAllProdsByStoreidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllProdsByStoreidServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			Integer storeid = new Integer(request.getParameter("storeid"));

			ProductService prodSvc = new ProductService();
			List<ProductVO> list = prodSvc.findByStoreid(storeid);
			request.setAttribute("listProdsByStoreid", list);
			
			String url = "/front-end/product/index-prodManagement.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

		} catch (Exception e) {
//			錯誤時，發送錯誤訊息並轉回原請求頁面
			errorMsgs.add("無法取得資料" + e.getMessage());
			e.printStackTrace();
			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/product/index-prodManagement.jsp");
			failureView.forward(request, response);
		}
	}
}
