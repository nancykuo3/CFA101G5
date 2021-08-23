package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class ListAllProductServlet
 */
@WebServlet("/product/ListAllProductServlet")
public class ListAllProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAllProductServlet() {
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
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		try {
//			取得來源的網頁路徑(/back-end/product/listAllType.jsp)
			String requestURL = request.getParameter("requestURL");

			Integer storeid = new Integer(request.getParameter("storeid"));
			JSONArray array = new JSONArray();

			ProductService prodSvc = new ProductService();
			List<ProductVO> list = prodSvc.findByStoreid(storeid);

			for (ProductVO item : list) {
				JSONObject obj = new JSONObject();
				obj.put("prodid", item.getProdId());
				obj.put("isbn", item.getIsbn());
				obj.put("storeid", item.getStoreId());
				obj.put("status", item.getStatus());
				obj.put("price", item.getPrice());
				obj.put("prodQty", item.getProdQty());
				obj.put("intro", item.getIntro());
				obj.put("regDate", item.getRegDate());
				obj.put("salesFig", item.getSalesFig());
				array.put(obj);
			}

			// 改為純文字
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			// 把JSONArray print回去
			out.write(array.toString());
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
