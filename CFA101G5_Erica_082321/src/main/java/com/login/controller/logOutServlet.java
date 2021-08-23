package com.login.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdministratorService;
import com.admin.model.AdministratorVO;
import com.mem.model.MemManagementService;
import com.mem.model.MemVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/login/logOutServlet")
public class logOutServlet extends HttpServlet {

	private static final long serialVersionUID = -2446821516777883745L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if ("mem".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
//				session.removeAttribute("memVO");
			System.out.println("session刪除成功");

			System.out.println("sassion=" + session);
			System.out.println("登出成功");
			String url = "/front-end/index-front.jsp";
			response.sendRedirect(request.getContextPath() + url);
		}

		if ("store".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
//			session.removeAttribute("StoreVO");
			System.out.println("session刪除成功");

			System.out.println("sassion=" + session);
			System.out.println("登出成功");
			String url = "/front-end/index-front.jsp";
			response.sendRedirect(request.getContextPath() + url);
		}
		if ("admin".equals(action)) {
		HttpSession session = request.getSession();
			session.removeAttribute("administratorVO");
			session.invalidate();
			System.out.println("session刪除成功");
			
			System.out.println("sassion=" + session);
			System.out.println("登出成功");
			response.sendRedirect(request.getContextPath() + "/back-end/adminLogin.jsp");
		}
	}
}