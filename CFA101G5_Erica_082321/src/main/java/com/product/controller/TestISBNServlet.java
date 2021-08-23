package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.productInfo.model.*;

/**
 * Servlet implementation class TestISBNServlet
 */
@WebServlet("/product/TestISBNServlet")
public class TestISBNServlet extends HttpServlet {
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
		System.out.println("已收到");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		String inputISBN = request.getParameter("inputISBN");
		System.out.println(inputISBN);

		List<ProdInfoVO> list = new ArrayList<ProdInfoVO>();
		ProdInfoService prodInfoSvc = new ProdInfoService();
		list = prodInfoSvc.getAll();
		JSONObject obj = new JSONObject();
		String result = null;

		boolean test = true;
		try {

			for (ProdInfoVO item : list) {
				if (test) {
					System.out.println("資料庫拿到的ISBN = " + item.getIsbn());
					if (inputISBN.equals(item.getIsbn())) {
						System.out.println("迴圈進來了");
						result = "success";
						obj.put("result", result);
						session.setAttribute("isbn", inputISBN);
						test = false;
					}
				}
			}
			if (test == true) {
				result = "fail";
				obj.put("result", result);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(obj.toString());

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();

	}

}
