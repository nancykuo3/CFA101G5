package com.report.model;

import java.sql.*;
import java.util.*;

public class StoreReportJDBCDAO implements StoreReportDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO STORE_REPORT (MEM_ID,STORE_ID,RP_TIME,RP_CONTENT,ADMIN_ID,RP_DONE_TIME,RP_STATUS,RP_RESULT,RP_NOTE) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM STORE_REPORT";
	private static final String GET_ONE_STMT = "SELECT * FROM STORE_REPORT WHERE REPORT_NO = ?";
	private static final String GET_ONE_STMT_BY_STOREID = "SELECT * FROM STORE_REPORT WHERE STORE_ID = ?";
	private static final String DELETE = "DELETE FROM STORE_REPORT WHERE REPORT_NO = ?";
	private static final String UPDATE = "UPDATE STORE_REPORT SET RP_RESULT=?,RP_NOTE=?, RP_STATUS=1, RP_DONE_TIME=now() WHERE REPORT_NO=?";

	@Override
	public void insert(StoreReportVO storeReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, storeReportVO.getMemid());
			pstmt.setInt(2, storeReportVO.getStoreid());
			pstmt.setString(3, storeReportVO.getReportTime());
			pstmt.setString(4, storeReportVO.getContent());
			pstmt.setInt(5, storeReportVO.getAdminid());
			pstmt.setString(6, storeReportVO.getDoneTime());
			pstmt.setByte(7, storeReportVO.getStatus());
			pstmt.setByte(8, storeReportVO.getResult());
			pstmt.setString(9, storeReportVO.getNote());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(StoreReportVO storeReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, storeReportVO.getResult());
			pstmt.setString(2, storeReportVO.getNote());
			pstmt.setInt(3, storeReportVO.getReportno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer reportno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reportno);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public StoreReportVO findByPK(Integer reportno) {

		StoreReportVO storeReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportno);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				storeReportVO = new StoreReportVO();
				storeReportVO.setReportno(rs.getInt("REPORT_NO"));
				storeReportVO.setMemid(rs.getInt("MEM_ID"));
				storeReportVO.setStoreid(rs.getInt("STORE_ID"));
				storeReportVO.setReportTime(rs.getString("RP_TIME"));
				storeReportVO.setContent(rs.getString("RP_CONTENT"));
				storeReportVO.setAdminid(rs.getInt("ADMIN_ID"));
				storeReportVO.setDoneTime(rs.getString("RP_DONE_TIME"));
				storeReportVO.setStatus(rs.getByte("RP_STATUS"));
				storeReportVO.setResult(rs.getByte("RP_RESULT"));
				storeReportVO.setNote(rs.getString("RP_NOTE"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("�L�k���J��Ʈw���~" + e.getMessage());
			// �B�z����SQL���~
		} catch (SQLException se) {
			throw new RuntimeException("��Ʈw�o�Ϳ��~" + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

		return storeReportVO;
	}

	@Override
	public List<StoreReportVO> getAll() {

		List<StoreReportVO> list = new ArrayList<StoreReportVO>();
		StoreReportVO storeReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// storeReportVO �]�٬� Domain objects
				storeReportVO = new StoreReportVO();
				storeReportVO.setReportno(rs.getInt("REPORT_NO"));
				storeReportVO.setMemid(rs.getInt("MEM_ID"));
				storeReportVO.setStoreid(rs.getInt("STORE_ID"));
				storeReportVO.setReportTime(rs.getString("RP_TIME"));
				storeReportVO.setContent(rs.getString("RP_CONTENT"));
				storeReportVO.setAdminid(rs.getInt("ADMIN_ID"));
				storeReportVO.setDoneTime(rs.getString("RP_DONE_TIME"));
				storeReportVO.setStatus(rs.getByte("RP_STATUS"));
				storeReportVO.setResult(rs.getByte("RP_RESULT"));
				storeReportVO.setNote(rs.getString("RP_NOTE"));
				list.add(storeReportVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {

		StoreReportJDBCDAO dao = new StoreReportJDBCDAO();

		// �s�W
		StoreReportVO storeReportVO1 = new StoreReportVO();
		storeReportVO1.setMemid(10002);
		storeReportVO1.setStoreid(10004);
		storeReportVO1.setReportTime("2021-06-23 18:42:30");
		storeReportVO1.setContent("xxx");
		storeReportVO1.setAdminid(10);
		storeReportVO1.setDoneTime("2021-05-06 15:05:32");
		storeReportVO1.setStatus((byte) 0);
		storeReportVO1.setResult((byte) 1);
		storeReportVO1.setNote("NN");
		dao.insert(storeReportVO1);

//				 �ק�
//
//				StoreReportVO storeReportVO2 = new StoreReportVO();
//				storeReportVO2.setMemid(88888);
//				storeReportVO2.setStoreid(45005);
//				storeReportVO2.setReportTime("2021-05-06 20:42:30");
//				storeReportVO2.setContent("xxx");
//				storeReportVO2.setAdminid(362);
//				storeReportVO2.setDoneTime("2021-05-06 16:01:31");
//				storeReportVO2.setStatus((byte)1);
//				storeReportVO2.setResult((byte)1);
//				storeReportVO2.setNote("NN");
//				storeReportVO2.setReportno(543502);
//				dao.update(storeReportVO2);

		// �R��
//				dao.delete(543504);

		// �d��All

//				StoreReportVO storeReportVO3 = dao.findByPK(4567);
//				System.out.print(storeReportVO3.getReportno() + ",");
//				System.out.print(storeReportVO3.getMemid() + ",");
//				System.out.print(storeReportVO3.getStoreid() + ",");
//				System.out.print(storeReportVO3.getReportTime() + ",");
//				System.out.print(storeReportVO3.getContent() + ",");
//				System.out.print(storeReportVO3.getAdminid() + ",");
//				System.out.print(storeReportVO3.getDoneTime() + ",");
//				System.out.println(storeReportVO3.getStatus() + ",");
//				System.out.print(storeReportVO3.getResult() + ",");
//				System.out.println(storeReportVO3.getNote() + ",");
//				System.out.println("---------------------");

		// �浧�d��
//				StoreReportVO storeReportVO4 = dao.findByPK(4567);
//				System.out.println("���|�渹: "+storeReportVO4.getReportno());
//				System.out.println("�|���s��: "+storeReportVO4.getMemid());
//				System.out.println("���a�|���s��: "+storeReportVO4.getStoreid());
//				System.out.println("���|�ɶ�: "+storeReportVO4.getReportTime());
//				System.out.println("���|��r���e: "+storeReportVO4.getContent());
//				System.out.println("�޲z���s��: "+storeReportVO4.getAdminid());
//				System.out.println("�B�z�����ɶ�: "+storeReportVO4.getDoneTime());
//				System.out.println("�B�z���A: "+storeReportVO4.getStatus());
//				System.out.println("�B�z���G: "+storeReportVO4.getResult());
//				System.out.println("�B�z���O: "+storeReportVO4.getNote());

	}

	@Override
	public List<StoreReportVO> getStoreReportByStoreId(Integer storeId) {
		// TODO Auto-generated method stub
		List<StoreReportVO> StoreReportVOcavList = new ArrayList<StoreReportVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_STOREID);

			pstmt.setInt(1, storeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				StoreReportVO storeReportVO = new StoreReportVO();
				storeReportVO.setReportno(rs.getInt("REPORT_NO"));
				storeReportVO.setMemid(rs.getInt("MEM_ID"));
				storeReportVO.setStoreid(rs.getInt("STORE_ID"));
				storeReportVO.setReportTime(rs.getString("RP_TIME"));
				storeReportVO.setContent(rs.getString("RP_CONTENT"));
				storeReportVO.setAdminid(rs.getInt("ADMIN_ID"));
				storeReportVO.setDoneTime(rs.getString("RP_DONE_TIME"));
				storeReportVO.setStatus(rs.getByte("RP_STATUS"));
				storeReportVO.setResult(rs.getByte("RP_RESULT"));
				storeReportVO.setNote(rs.getString("RP_NOTE"));

				StoreReportVOcavList.add(storeReportVO);

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return StoreReportVOcavList;
	}
}
