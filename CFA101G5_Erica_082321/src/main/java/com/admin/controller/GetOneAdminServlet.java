package com.admin.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.AdministratorService;
import com.admin.model.AdministratorVO;

/**
 * Servlet implementation class GetOneAdminServlet
 */
@WebServlet("/admin/GetOneAdminServlet")
public class GetOneAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetOneAdminServlet() {
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

		if ("getOneAdmin".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String textAdminid = request.getParameter("adminid");
				if (textAdminid == null || (textAdminid.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號後再按查詢！");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/index-admin.jsp");
					failureView.forward(request, response);
					return;
				}

				Integer adminid = null;
				try {
					adminid = new Integer(textAdminid);
				} catch (Exception e) {
					errorMsgs.add("輸入格式錯誤，需為4碼數字");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/index-admin.jsp");
					failureView.forward(request, response);
					return;
				}

				AdministratorService adminSvc = new AdministratorService();
				AdministratorVO adminVO = adminSvc.getOneAdmin(adminid);
				if (adminVO == null) {
					errorMsgs.add("查無此筆資料！");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				request.setAttribute("adminVO", adminVO);
				String url = "/back-end/admin/listOneAdmin.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/index-admin.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
