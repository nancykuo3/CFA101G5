package com.product.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductVO;
import com.productInfo.model.ProdInfoService;

/**
 * Servlet implementation class ListProdsByISBNServlet
 */
@WebServlet("/product/ListProdsByISBNServlet")
public class ListProdsByISBNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProdsByISBNServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("listProds_By_ISBN_A".equals(action) || "listProds_By_ISBN_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			try {
				String isbn = request.getParameter("isbn");
				ProdInfoService prodInfoSvc = new ProdInfoService();
				
				List<ProductVO> list = prodInfoSvc.getProdsByISBN(isbn);
				request.setAttribute("listProds_By_ISBN", list);
				
				String url = "/back-end/product/prodInfoQuery.jsp";
//				if("listProds_By_ISBN_A".equals(action)) {
//					url = "/back-end/product/listAllProdInfo.jsp";
//				}
//				if("listProds_By_ISBN_B".equals(action)) {
//					url = "/back-end/product/listAllProdInfo_compositeQuery.jsp";
//				}
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
				
				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("資料取出時失敗："+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
	}

}
