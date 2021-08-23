package com.productType.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productType.model.TypeService;
import com.productType.model.TypeVO;

/**
 * Servlet implementation class AddTypeServlet
 */
@WebServlet("/productType/AddTypeServlet")
public class AddTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTypeServlet() {
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

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String typeName = request.getParameter("typeName");
				String typeNameReg = "^[(\u4e00-\u9fa5)]{2,20}$";

				if (!typeName.trim().matches(typeNameReg)) {
					errorMsgs.add("標籤類型只能輸入中文，且長度介於2至20字元");
				}
				String typeClass = request.getParameter("typeClass");

				TypeVO typeVO = new TypeVO();
				typeVO.setTypeName(typeName);
				typeVO.setTypeClass(typeClass);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("typeVO", typeVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/product/listAllType.jsp");
					failureView.forward(request, response);
					return;
				}

				TypeService typeSvc = new TypeService();
				typeVO = typeSvc.addType(typeName, typeClass);

				String url = "/back-end/product/listAllType.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("新增失敗：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/product/listAllType.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
