package com.function.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.function.model.FunctionService;

/**
 * Servlet implementation class DeleteFuncServlet
 */
@WebServlet("/function/DeleteFuncServlet")
public class DeleteFuncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		if ("deleteOneFunc".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer funcid = new Integer(request.getParameter("funcid"));

				FunctionService funcSvc = new FunctionService();
				funcSvc.deleteFunction(funcid);

				String url = "/back-end/function/listAllFunction.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("權限刪除失敗：" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/function/listAllFunction.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
