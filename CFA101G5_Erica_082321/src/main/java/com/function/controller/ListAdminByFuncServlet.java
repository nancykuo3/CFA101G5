package com.function.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.AdministratorVO;
import com.function.model.FunctionService;

/**
 * Servlet implementation class ListAdminByFuncServlet
 */
@WebServlet("/function/ListAdminByFuncServlet")
public class ListAdminByFuncServlet extends HttpServlet {
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

		if ("listAdminsByFunc_B".equals(action) || "listAdminsByFunc_A".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer funcid = new Integer(request.getParameter("funcid"));

				FunctionService funcSvc = new FunctionService();
				Set<AdministratorVO> set = funcSvc.getAdminsByFuncid(funcid);

				request.setAttribute("listAdminsByFuncid", set);

				String url = null;
				if ("listAdminsByFunc_B".equals(action)) {
					url = "/back-end/function/listAllFunction.jsp";
				} else if("listAdminsByFunc_A".equals(action)) {
					url = "/back-end/index-admin.jsp";
				}

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

}
