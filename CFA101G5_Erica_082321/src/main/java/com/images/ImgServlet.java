package com.images;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images")
public class ImgServlet extends HttpServlet {

	private static final long serialVersionUID = -2855559001798524887L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ImgTransUtil imgTransUtil = new ImgTransUtil();
		InputStream in = null;

		// 建立讀取串流
		BufferedInputStream bi = null;

		// 從res拿到輸出串流
		ServletOutputStream out = res.getOutputStream();

		// 從req拿到必要資料
		String tableName = req.getParameter("tableName");
		String colName = req.getParameter("colName");
		String queryId = req.getParameter("queryId");
		String queryImg = req.getParameter("queryImg");
		System.out.println("tableName: " + tableName + " ,colName: " + colName + " ,queryId: " + queryId
				+ " ,queryImg: " + queryImg);
		res.setContentType("image/gif");

		// System.out.println("ImgServlet tableName: " + tableName);
		// System.out.println("ImgServlet queryImg: " + queryImg);

		try {
			// 設定緩衝大小
			byte[] picBuffer = null;
			picBuffer = imgTransUtil.getBytesFromDB(tableName, colName, queryImg, queryId);
			System.out.println("ImgServlet picBuffer: " + picBuffer);

			// 拿到圖片byte
			in = new ByteArrayInputStream(picBuffer);

			// 將檔案讀取到緩衝區中
			bi = new BufferedInputStream(in);

			int len;
			while ((len = bi.read(picBuffer)) != -1) { // 依設定的緩衝大小讀取檔案
				out.write(picBuffer, 0, len); // 將資料寫入緩衝
			}

			// System.out.println("Successfully find " + colName + " : " + queryId + " from DB!");
		} catch (SQLException e) {
			System.out.println("Can't find pictures by query statement...try again by typing something else:)");
		} catch (NullPointerException e) {
			e.printStackTrace();
			in = getServletContext().getResourceAsStream("/images/nullImg.gif");
			bi = new BufferedInputStream(in);
			byte[] picBuffer = new byte[in.available()];
			int len;
			while ((len = bi.read(picBuffer)) != -1) {
				out.write(picBuffer, 0, len);
			}
		} finally {
			if (bi != null)
				bi.close();
			if (in != null)
				in.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
