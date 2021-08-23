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
 * Servlet implementation class UpdateAdminServlet
 */
@WebServlet("/admin/UpdateAdminServlet")
public class UpdateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// request from listAllAdmin.jsp
		if ("updateOneAdmin".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMags", errorMsgs);

			try {
				Integer adminid = new Integer(request.getParameter("adminid"));

				AdministratorService adminSvc = new AdministratorService();
				AdministratorVO adminVO = adminSvc.getOneAdmin(adminid);

				request.setAttribute("adminVO", adminVO);
				String url = "/back-end/admin/updateOneAdmin.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得欲修改之資料：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/admin/listAllAdmin.jsp");
				failureView.forward(request, response);
			}
		}

		// request from updateOneAdmin.jsp
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
//				input form checking
				Integer adminid = new Integer(request.getParameter("adminid"));

				String adminacc = request.getParameter("adminacc").trim();
				if (adminacc == null || adminacc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白！");
				}

				String adminpwd = request.getParameter("adminpwd").trim();
				if (adminpwd == null || adminpwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白！");
				}

				String adminName = request.getParameter("adminName").trim();
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白！");
				}

				Byte adminStatus = new Byte(request.getParameter("adminStatus"));

				AdministratorVO adminVO = new AdministratorVO();
				adminVO.setAdminid(adminid);
				adminVO.setAdminacc(adminacc);
				adminVO.setAdminpwd(adminpwd);
				adminVO.setAdminName(adminName);
				adminVO.setAdminStatus(adminStatus);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("adminVO", adminVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/admin/updateOneAdmin.jsp");
					failureView.forward(request, response);
					return;
				}

				AdministratorService adminSvc = new AdministratorService();
				adminVO = adminSvc.updateAdmin(adminid, adminacc, adminpwd, adminStatus, adminName);

				request.setAttribute("adminVO", adminVO);
				String url = "/back-end/admin/listOneAdmin.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("資料修改失敗：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/admin/updateOneAdmin.jsp");
				failureView.forward(request, response);
			}

		}
	}

}
