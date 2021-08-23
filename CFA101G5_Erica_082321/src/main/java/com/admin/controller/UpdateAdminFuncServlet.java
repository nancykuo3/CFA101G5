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
import com.adminfunclist.model.AdminFuncListService;
import com.adminfunclist.model.AdminFuncListVO;
import com.function.model.FunctionVO;

/**
 * Servlet implementation class UpdateAdminFuncServlet
 */
@WebServlet("/admin/UpdateAdminFuncServlet")
public class UpdateAdminFuncServlet extends HttpServlet {
	private static final long serialVersionUID = 5L;

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

		if ("UpdateAdminFunc".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer adminid = new Integer(request.getParameter("adminid"));
				System.out.println(adminid);

				AdministratorService adminSvc = new AdministratorService();
				AdministratorVO adminVO = adminSvc.getOneAdmin(adminid);

				request.setAttribute("adminVO", adminVO);

				String url = "/back-end/function/listAdminWithFuncs.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("無法取得欲修改之資料：" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/admin/listAllAdmin.jsp");
				failureView.forward(request, response);
			}
		}

		if ("update_AdminFunc".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer adminid = new Integer(request.getParameter("adminid"));
				String[] funcs = request.getParameterValues("funcid");
				AdminFuncListService funcListSvc = new AdminFuncListService();
				funcListSvc.deleteAdminFuncList(adminid);
				
				AdminFuncListVO funcListVO = null;
				for(String func : funcs) {
					Integer funcID = new Integer(func);
					funcListVO = new AdminFuncListVO();
					funcListVO = funcListSvc.addAdminFuncList(adminid, funcID);
				}
				
				AdministratorService adminSvc = new AdministratorService();
				request.setAttribute("listFuncsByAdminid", adminSvc.getFuncsByAdminid(adminid));
				String url = "/back-end/admin/listAllAdmin.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/function/listAdminWithFuncs.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
