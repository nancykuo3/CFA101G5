package com.productInfo.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productInfo.model.ProdInfoVO;
import com.productType.model.TypeService;

/**
 * Servlet implementation class ListProdInfoByTypeidServlet
 */
@WebServlet("/productInfo/ListProdInfoByTypeidServlet")
public class ListProdInfoByTypeidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		// 來自 listAllType.jsp 的查詢請求
		if ("listProdInfoByTypeid_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
//			取得來源的網頁路徑(/back-end/product/listAllType.jsp)
			String requestURL = request.getParameter("requestURL");

			try {
				// 取得前頁傳送的 request 參數
				Integer typeid = new Integer(request.getParameter("typeid"));

				// 取得資訊
				TypeService typeSvc = new TypeService();
				Set<ProdInfoVO> set = typeSvc.getProdInfoByTypeId(typeid);
				request.setAttribute("listProdInfoByTypeid", set);

				// 設定轉交的目標 url
				// 注意：因為會 include，所以先轉交回原請求網頁
				String url = null;
				if ("listProdInfoByTypeid_B".equals(action)) {
					url = "/back-end/product/listAllType.jsp";
				}

				// 轉交
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
//				錯誤時，發送錯誤訊息並轉回原請求頁面
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
	}
}
