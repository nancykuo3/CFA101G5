package com.product.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetProdPicsServlet
 */
@WebServlet("/product/GetProdPicsServlet")
public class GetProdPicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
	String USER = "root";
	String PWD = "123456";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetProdPicsServlet() {
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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();

		try {
			Integer prodid = new Integer(request.getParameter("prodId"));
			Integer picid = new Integer(request.getParameter("picId"));
			
			System.out.println(prodid);
			System.out.println(picid);

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(
					"select PROD_PIC from PRODUCT_PIC where PROD_ID = " + prodid + " and PROD_PIC_ID = " + picid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("PROD_PIC"));
				byte[] buf = new byte[4 * 1024];
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
}
