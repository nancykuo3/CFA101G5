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

/**
 * Servlet implementation class DeleteAdminServlet
 */
@WebServlet("/admin/DeleteAdminServlet")
public class DeleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("deleteOneAdmin".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer adminid = new Integer(request.getParameter("adminid"));

				AdministratorService adminSvc = new AdministratorService();
				adminSvc.deleteAdmin(adminid);

				String url = "/back-end/admin/listAllAdmin.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗：" + e.getMessage());

				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/admin/listAllAdmin.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
