package com.report.model;

import java.sql.*;
import java.util.*;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;



public class MemReportJDBCDAO implements MemReportDAO_interface{
	

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String GET_ALL_STMT = 
		"SELECT * FROM MEMBER_REPORT";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM MEMBER_REPORT WHERE REPORT_NO = ?";
	
	private static final String GET_ONE_STMT_BY_MEMID = 
			"SELECT * FROM MEMBER_REPORT WHERE MEM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MEMBER_REPORT WHERE REPORT_NO = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER_REPORT SET RP_RESULT=?,RP_NOTE=?,RP_STATUS=1,RP_DONE_TIME=now() WHERE REPORT_NO=?";

	private static final String INSERT_STMT = 
			"INSERT INTO MEMBER_REPORT (MEM_ID,MEM_ID_REPORTED,RP_TIME,RP_CONTENT,ADMIN_ID,RP_STATUS) "
			+ "VALUES (?, ?, now(), ?, ?, ?)";
	@Override
	public void insert(MemReportVO memReportVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, memReportVO.getMemid());
				pstmt.setInt(2, memReportVO.getMemidReported());
				pstmt.setString(3, memReportVO.getContent());
				pstmt.setInt(4, 1001);
				pstmt.setByte(5, new Byte((byte) 0));
				
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public void update(MemReportVO memReportVO) {
		System.out.println(this.getClass().getName()+"_update");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setByte(1, memReportVO.getResult());
			pstmt.setString(2, memReportVO.getNote());
			pstmt.setInt(3, memReportVO.getReportno());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reportno);
			pstmt.executeUpdate();


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public MemReportVO findByPK(Integer reportno) {

		MemReportVO memReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportno);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memReportVO = new MemReportVO();
				memReportVO.setReportno(rs.getInt("REPORT_NO"));
				memReportVO.setMemid(rs.getInt("MEM_ID"));
				memReportVO.setMemidReported(rs.getInt("MEM_ID_REPORTED"));
				memReportVO.setReportTime(rs.getString("RP_TIME"));
				memReportVO.setContent(rs.getString("RP_CONTENT"));
				memReportVO.setAdminid(rs.getInt("ADMIN_ID"));
				memReportVO.setDoneTime(rs.getString("RP_DONE_TIME"));
				memReportVO.setStatus(rs.getByte("RP_STATUS"));
				memReportVO.setResult(rs.getByte("RP_RESULT"));
				memReportVO.setNote(rs.getString("RP_NOTE"));

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

		return memReportVO;
	}

	@Override
	public List<MemReportVO> getAll() {
			List<MemReportVO> list = new ArrayList<MemReportVO>();
			MemReportVO memreportVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// memreportVO �]�٬� Domain objects
					memreportVO = new MemReportVO();
					memreportVO.setReportno(rs.getInt("REPORT_NO"));
					memreportVO.setMemid(rs.getInt("MEM_ID"));
					memreportVO.setMemidReported(rs.getInt("MEM_ID_REPORTED"));
					memreportVO.setReportTime(rs.getString("RP_TIME"));
					memreportVO.setContent(rs.getString("RP_CONTENT"));
					memreportVO.setAdminid(rs.getInt("ADMIN_ID"));
					memreportVO.setDoneTime(rs.getString("RP_DONE_TIME"));
					memreportVO.setStatus(rs.getByte("RP_STATUS"));
					memreportVO.setResult(rs.getByte("RP_RESULT"));
					memreportVO.setNote(rs.getString("RP_NOTE"));
					list.add(memreportVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

	MemReportJDBCDAO dao = new MemReportJDBCDAO();

	// �s�W
	MemReportVO memreportVO1 = new MemReportVO();
	
	memreportVO1.setMemid(10002);
	memreportVO1.setMemidReported(10004);
	memreportVO1.setReportTime("2021-06-23 18:42:30");
	memreportVO1.setContent("xxx");
	memreportVO1.setAdminid(10);
	memreportVO1.setDoneTime("2021-05-06 15:05:32");
	memreportVO1.setStatus((byte)0);
	memreportVO1.setResult((byte)1);
	memreportVO1.setNote("NN");
	dao.insert(memreportVO1);

//	 �ק�

//	MemReportVO memreportVO2= new MemReportVO();
//	memreportVO2.setMemid(9555);
//	memreportVO2.setMemidReported(11452);
//	memreportVO2.setReportTime("2021-05-06 20:42:30");
//	memreportVO2.setContent("xxx");
//	memreportVO2.setAdminid(362);
//	memreportVO2.setDoneTime("2021-05-06 16:01:31");
//	memreportVO2.setStatus((byte)1);
//	memreportVO2.setResult((byte)1);
//	memreportVO2.setNote("RRRRRTTT");
//	memreportVO2.setReportno(10002);
//	dao.update(memreportVO2);
	

	// �R��
//	dao.delete(10002);

	// �d��All
//	MemReportVO memreportVO3 = dao.findByPK(10002);
//	System.out.print(memreportVO3.getReportno() + ",");
//	System.out.print(memreportVO3.getMemid() + ",");
//	System.out.print(memreportVO3.getMemidReported() + ",");
//	System.out.print(memreportVO3.getReportTime() + ",");
//	System.out.print(memreportVO3.getContent() + ",");
//	System.out.print(memreportVO3.getAdminid() + ",");
//	System.out.print(memreportVO3.getDoneTime() + ",");
//	System.out.println(memreportVO3.getStatus() + ",");
//	System.out.print(memreportVO3.getResult() + ",");
//	System.out.println(memreportVO3.getNote() + ",");
//	System.out.println("---------------------");
	
	// �浧�d��
//	MemReportVO memreportVO4 = dao.findByPK(10002);
//	System.out.println("���|�渹: "+memreportVO4.getReportno());
//	System.out.println("�|���s��: "+memreportVO4.getMemid());
//	System.out.println("�Q���|�|���s��: "+memreportVO4.getMemidReported());
//	System.out.println("���|�ɶ�: "+memreportVO4.getReportTime());
//	System.out.println("���|��r���e: "+memreportVO4.getContent());
//	System.out.println("�޲z���s��: "+memreportVO4.getAdminid());
//	System.out.println("�B�z�����ɶ�: "+memreportVO4.getDoneTime());
//	System.out.println("�B�z���A: "+memreportVO4.getStatus());
//	System.out.println("�B�z���G: "+memreportVO4.getResult());
//	System.out.println("�B�z���O: "+memreportVO4.getNote());
	
}


	@Override
	public List<MemReportVO> getMemReportByMemId(Integer memId) {
		// TODO Auto-generated method stub
		List <MemReportVO> MemReportVOFavList = new ArrayList<MemReportVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_MEMID);
	
			pstmt.setInt(1, memId);
	
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				MemReportVO memReportVO = new MemReportVO();
				memReportVO.setReportno(rs.getInt("REPORT_NO"));
				memReportVO.setMemid(rs.getInt("MEM_ID"));
				memReportVO.setMemidReported(rs.getInt("MEM_ID_REPORTED"));
				memReportVO.setReportTime(rs.getString("RP_TIME"));
				memReportVO.setContent(rs.getString("RP_CONTENT"));
				memReportVO.setAdminid(rs.getInt("ADMIN_ID"));
				memReportVO.setDoneTime(rs.getString("RP_DONE_TIME"));
				memReportVO.setStatus(rs.getByte("RP_STATUS"));
				memReportVO.setResult(rs.getByte("RP_RESULT"));
				memReportVO.setNote(rs.getString("RP_NOTE"));
				
				MemReportVOFavList.add(memReportVO);
				
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return MemReportVOFavList;
	}
}
