package com.productType.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productType.model.TypeService;
import com.productType.model.TypeVO;

/**
 * Servlet implementation class UpdateTypeServlet
 */
@WebServlet("/productType/UpdateTypeServlet")
public class UpdateTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTypeServlet() {
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

		if ("getOne_Type_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				Integer typeid = new Integer(request.getParameter("typeid"));

				TypeService typeSvc = new TypeService();
				TypeVO typeVO = typeSvc.getOneType(typeid);

				request.setAttribute("typeVO", typeVO);
				String url = "/back-end/product/updateType.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("無法取得欲修改之資料：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		// 來自 updateType.jsp 的請求
		if ("updateType".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				Integer typeid = new Integer(request.getParameter("typeid"));

				String typeName = request.getParameter("typeName");
				String typeNameReg = "^[(\u4e00-\u9fa5)]{2,20}$";

				if (!typeName.trim().matches(typeNameReg)) {
					errorMsgs.add("標籤類型只能輸入中文，且長度介於2至20字元");
				}

				String typeClass = request.getParameter("typeClass");

				TypeVO typeVO = new TypeVO();
				typeVO.setTypeid(typeid);
				typeVO.setTypeName(typeName);
				typeVO.setTypeClass(typeClass);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("typeVO", typeVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/product/updateType.jsp");
					failureView.forward(request, response);
					return;
				}
				
				TypeService typeSvc = new TypeService();
				typeVO = typeSvc.updateType(typeName, typeClass, typeid);
				
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/product/updateType.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
