package com.mem.model;



	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;

	public class MemInsertPic {
		public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
		
		// MySQL 8.0.13以後只需保留serverTimezone設定即可
		public static final String URL = 
				"jdbc:mysql://127.0.0.1:3306/EXAMPLE?serverTimezone=Asia/Taipei"
//				+ "useSSL=false&"                   // 不使用加密連線 (需有憑證才行)
//				+ "rewriteBatchedStatements=true&"  // 批次更新需要此資訊
				+ "serverTimezone=Asia/Taipei";     // 設定時區資訊
//				+ "allowPublicKeyRetrieval=true&"   // 配合MySQL 8以後版本對密碼儲存機制的設定
//				+ "useUnicode=true&"                // 使用Unicode編碼 (中文才不會亂碼)
//				+ "characterEncoding=utf-8";        // 字元採用UTF-8設定
		
		public static final String USER = "Topic";
		public static final String PASSWORD = "123456";
		private static final String INSERT_STMT="insert into MEM(MEM_ID,MEM_ACC,MEM_PWD,ACC_STATUS,MEM_NAME,MEM_GENDER,MEM_EMAIL,MEM_MOBILE,MEM_CITY,MEM_DIST,MEM_ADDR,MEM_REG_DATE,MEM_PIC,MEM_RATING_SCORE,MEM_RATING_COUNT,MEM_REPORT_COUNT) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		public static byte[] getPictureByteArray(String path) throws IOException{
			FileInputStream fis = new FileInputStream(path);
			byte[] buffer= new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			return buffer;
			
		}
		
		public static void main(String[] args) {
			
			Connection con=null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(DRIVER);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				pstmt=con.prepareStatement(INSERT_STMT);
				
//				pstmt.setInt(1, 30001);
//				pstmt.setByte(2, (byte)2);
//				pstmt.setString(3,"卡牌遊戲特價八折!!!");
//				byte[] pic = getPictureByteArray("C:\\Users\\Tibame\\Desktop\\tables\\20percentoff.jpg");
//				pstmt.setBytes(4, pic);
//				pstmt.setString(5, "愛樂桌遊所有卡牌遊戲全館打八折，活動持續兩周");
//				pstmt.setDate(6, java.sql.Date.valueOf("2018-01-01"));
//				pstmt.setDate(7, java.sql.Date.valueOf("2018-01-14"));
//				pstmt.executeUpdate();
				pstmt.setInt(1, 30002);
				pstmt.setByte(2, (byte)2);
				pstmt.setString(3,"戰略遊戲特價六折!!!");
				byte[] pic = getPictureByteArray("C:\\Users\\Tibame\\Desktop\\tables\\40percentoff.jpg");
				pstmt.setBytes(4, pic);
				pstmt.setString(5, "概雅桌遊所有戰略遊戲全館打六折，活動持續兩周");
				pstmt.setDate(6, java.sql.Date.valueOf("2018-03-01"));
				pstmt.setDate(7, java.sql.Date.valueOf("2018-03-14"));
				pstmt.executeUpdate();
				
				System.out.println("新增成功");
			}
			catch (ClassNotFoundException ce) {
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
	