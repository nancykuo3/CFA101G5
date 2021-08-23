package com.productReport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductReportDAO implements ProductReportDAO_interface {
//	String DRIVER = "com.mysql.cj.jdbc.Driver";
//	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String USER = "root";
//	String PWD = "123456";

	// insert
	private static final String INSERT_STMT = "insert into PRODUCT_REPORT (MEM_ID, PROD_ID, RP_TIME, RP_CONTENT, ADMIN_ID, RP_DONE_TIME, RP_STATUS, RP_RESULT, RP_NOTE) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// query
	private static final String GET_ALL_STMT = "select * from PRODUCT_REPORT";
	private static final String GET_ONE_STMT = "select * from PRODUCT_REPORT where REPORT_NO = ?";

	// query (後台，用處理狀態列出檢舉清單)
	private static final String GET_Reports_By_Status = "select * from PRODUCT_REPORT where RP_STATUS = ?";
	// query (前台，列出送出的商品檢舉清單)
	private static final String GET_Reports_By_Memid = "select * from PRODUCT_REPORT where MEM_ID = ?";

	// delete
	private static final String DELETE = "delete from PRODUCT_REPORT where REPORT_NO = ?";

	// update
	private static final String UPDATE = "update PRODUCT_REPORT set MEM_ID = ?, PROD_ID = ?, RP_TIME = ?, RP_CONTENT = ?, ADMIN_ID = ?, RP_DONE_TIME = ?, RP_STATUS = ?, RP_RESULT = ?, RP_NOTE = ? where REPORT_NO = ?";

	@Override
	public void insert(ProductReportVO productReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productReportVO.getMemid());
			pstmt.setInt(2, productReportVO.getProductid());
			pstmt.setTimestamp(3, productReportVO.getReportTime());
			pstmt.setString(4, productReportVO.getContent());
			pstmt.setInt(5, productReportVO.getAdminid());
			pstmt.setTimestamp(6, productReportVO.getDoneTime());
			pstmt.setByte(7, productReportVO.getStatus());
			pstmt.setByte(8, productReportVO.getResult());
			pstmt.setString(9, productReportVO.getNote());

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
	public void update(ProductReportVO productReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productReportVO.getMemid());
			pstmt.setInt(2, productReportVO.getProductid());
			pstmt.setTimestamp(3, productReportVO.getReportTime());
			pstmt.setString(4, productReportVO.getContent());
			pstmt.setInt(5, productReportVO.getAdminid());
			pstmt.setTimestamp(6, productReportVO.getDoneTime());
			pstmt.setByte(7, productReportVO.getStatus());
			pstmt.setByte(8, productReportVO.getResult());
			pstmt.setString(9, productReportVO.getNote());
			pstmt.setInt(10, productReportVO.getReportno());

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
	public ProductReportVO findByPrimaryKey(Integer reportno) {

		ProductReportVO repVO = null;
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
				repVO = new ProductReportVO();

				repVO.setReportno(rs.getInt("REPORT_NO"));
				repVO.setMemid(rs.getInt("MEM_ID"));
				repVO.setProductid(rs.getInt("PROD_ID"));
				repVO.setReportTime(rs.getTimestamp("RP_TIME"));
				repVO.setContent(rs.getString("RP_CONTENT"));
				repVO.setAdminid(rs.getInt("ADMIN_ID"));
				repVO.setDoneTime(rs.getTimestamp("RP_DONE_TIME"));
				repVO.setStatus(rs.getByte("RP_STATUS"));
				repVO.setResult(rs.getByte("RP_RESULT"));
				repVO.setNote(rs.getString("RP_NOTE"));
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

		return repVO;
	}

	@Override
	public List<ProductReportVO> getAll() {

		List<ProductReportVO> list = new ArrayList<ProductReportVO>();
		ProductReportVO repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repVO = new ProductReportVO();

				repVO.setReportno(rs.getInt("REPORT_NO"));
				repVO.setMemid(rs.getInt("MEM_ID"));
				repVO.setProductid(rs.getInt("PROD_ID"));
				repVO.setReportTime(rs.getTimestamp("RP_TIME"));
				repVO.setContent(rs.getString("RP_CONTENT"));
				repVO.setAdminid(rs.getInt("ADMIN_ID"));
				repVO.setDoneTime(rs.getTimestamp("RP_DONE_TIME"));
				repVO.setStatus(rs.getByte("RP_STATUS"));
				repVO.setResult(rs.getByte("RP_RESULT"));
				repVO.setNote(rs.getString("RP_NOTE"));

				list.add(repVO);
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

	@Override
	public Set<ProductReportVO> getReportsByStatus(Byte status) {
		Set<ProductReportVO> set = new LinkedHashSet<ProductReportVO>();
		ProductReportVO repVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_Reports_By_Status);
			pstmt.setByte(1, status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repVO = new ProductReportVO();

				repVO.setReportno(rs.getInt("REPORT_NO"));
				repVO.setMemid(rs.getInt("MEM_ID"));
				repVO.setProductid(rs.getInt("PROD_ID"));
				repVO.setReportTime(rs.getTimestamp("RP_TIME"));
				repVO.setContent(rs.getString("RP_CONTENT"));
				repVO.setAdminid(rs.getInt("ADMIN_ID"));
				repVO.setDoneTime(rs.getTimestamp("RP_DONE_TIME"));
				repVO.setStatus(rs.getByte("RP_STATUS"));
				repVO.setResult(rs.getByte("RP_RESULT"));
				repVO.setNote(rs.getString("RP_NOTE"));

				set.add(repVO);
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

		return set;
	}

	@Override
	public Set<ProductReportVO> getReportsByMemid(Integer memid) {
		Set<ProductReportVO> set = new LinkedHashSet<ProductReportVO>();
		ProductReportVO repVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_Reports_By_Memid);
			pstmt.setInt(1, memid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repVO = new ProductReportVO();

				repVO.setReportno(rs.getInt("REPORT_NO"));
				repVO.setMemid(rs.getInt("MEM_ID"));
				repVO.setProductid(rs.getInt("PROD_ID"));
				repVO.setReportTime(rs.getTimestamp("RP_TIME"));
				repVO.setContent(rs.getString("RP_CONTENT"));
				repVO.setAdminid(rs.getInt("ADMIN_ID"));
				repVO.setDoneTime(rs.getTimestamp("RP_DONE_TIME"));
				repVO.setStatus(rs.getByte("RP_STATUS"));
				repVO.setResult(rs.getByte("RP_RESULT"));
				repVO.setNote(rs.getString("RP_NOTE"));

				set.add(repVO);
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

		return set;
	}
}
