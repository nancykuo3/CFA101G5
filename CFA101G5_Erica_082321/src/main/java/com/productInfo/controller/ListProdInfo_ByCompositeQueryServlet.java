package com.productInfo.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.productInfo.model.ProdInfoService;
import com.productInfo.model.ProdInfoVO;

/**
 * Servlet implementation class ListProdInfo_ByCompositeQuery
 */
@WebServlet("/productInfo/ListProdInfo_ByCompositeQueryServlet")
public class ListProdInfo_ByCompositeQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProdInfo_ByCompositeQueryServlet() {
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

		if ("listProdInfo_ByCompositeQuery".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
System.out.println(map);
				// if區塊只在第一次執行時有效
				if (request.getParameter("whichPage") == null) {
					Map<String, String[]> map1 = new HashMap<String, String[]>(request.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;
				}
System.out.println(map.size());

				ProdInfoService prodInfoSvc = new ProdInfoService();
				List<ProdInfoVO> list = prodInfoSvc.getAll(map);

				request.setAttribute("listProdInfoByCompositeQuery", list);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/product/prodInfoQuery.jsp");
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("複合查詢失敗：" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/product/prodInfoQuery.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
