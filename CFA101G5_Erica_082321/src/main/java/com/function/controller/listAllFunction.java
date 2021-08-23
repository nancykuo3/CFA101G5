package com.function.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.function.model.FunctionService;
import com.function.model.FunctionVO;

/**
 * Servlet implementation class listAllFunction
 */
@WebServlet("/function/listAllFunction")
public class listAllFunction extends HttpServlet {
	private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String listAllFuncName = request.getParameter("listAllFuncName");

		if ("listAllFuncName".equals(listAllFuncName)) {
			FunctionService funcSvc = new FunctionService();
			List<FunctionVO> list = funcSvc.getAll();

			String funcNameList = new JSONArray(list).toString();

			request.setAttribute("funcNameList", funcNameList);
			String url = "CFA101G5/admin/addAdmin.html";

			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(request, response);
		}
	}

}
