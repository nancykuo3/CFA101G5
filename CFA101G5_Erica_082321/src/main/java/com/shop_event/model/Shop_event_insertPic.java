package com.shop_event.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Shop_event_insertPic {

	private static final String INSERT_STMT = "insert into ARTICLES(MEM_ID,AC_TYPE_ID,AC_TIME,AC_TITLE,AC_CONTEXT,AC_IMG,AC_STATE) values (?,?,?,?,?,?,?)";

	private static final String UPDATE = "update ARTICLES set MEM_ID=?,AC_TYPE_ID=?,AC_TIME=?,AC_TITLE=?,AC_CONTEXT=?,AC_IMG=?,AC_STATE=? where AC_ID=?";
//	private static final String UPDATE_STMT="update MEM set MEM_PIC=? where MEM_ID=?";

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;

	}

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, 10005);
			pstmt.setInt(2, 4);
			pstmt.setDate(3, java.sql.Date.valueOf("2020-07-28"));
			byte[] pic = getPictureByteArray(
					"C:\\CFA101_WebApp\\eclipse_WTP_workspace1\\CFA101G5\\WebContent\\image\\sixflags.jpg");
			pstmt.setString(5, "艾勒桌遊買桌遊有機會抽到機票!!");
			pstmt.setString(4, "來去一探加州最刺激樂園!!!");
			pstmt.setBytes(6, pic);
			pstmt.setByte(7, (byte) 1);

			pstmt.executeUpdate();
//			pstmt.setInt(1, 10005);
//			pstmt.setInt(2, 2);
//			pstmt.setDate(3,java.sql.Date.valueOf("1981-01-09"));
//			pstmt.setString(4, "標題3");
//			pstmt.setString(5, "Someone saw this man at a bar killing three people with firearms with a PHUCKING PENCIL!!!!!");
//			byte[] pic = getPictureByteArray("C:\\CFA101_WebApp\\eclipse_WTP_workspace1\\CFA101G5\\WebContent\\image\\johnwick.jpg");
//			pstmt.setBytes(6, pic);
//			pstmt.setByte(7, (byte)1);
//			pstmt.setInt(8, 5);
//			pstmt.executeUpdate();

			System.out.println("新增成功");
		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

}
