package com.images;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ImgTransUtil {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	public byte[] getBytesFromDB(String tableName, String colName, String queryImg, String queryId)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer stmt = new StringBuffer();
		byte[] buffer = null;
		
		String queryStmt = "";
		if (tableName.equals("MEM")) {
			queryStmt = "SELECT MEM_PIC FROM MEM WHERE MEM_ID = ?";
			queryImg = "MEM_PIC";
		}

		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(queryStmt);
			pstmt.setString(1, queryId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				buffer = rs.getBytes(queryImg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can't find pictures by query statement...try again by typing something else:)");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return buffer;
	}
}
