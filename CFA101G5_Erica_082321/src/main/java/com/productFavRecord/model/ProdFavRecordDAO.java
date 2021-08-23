package com.productFavRecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProdFavRecordDAO implements ProdFavRecordDAO_interface {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
	String USER = "root";
	String PWD = "123456";

	// insert
	private static final String INSERT_STMT = "insert into PROD_FAV_RECORD (MEM_ID, PROD_ID, PROD_FAV_DATE) values (?, ?, NOW())";

	// query
	private static final String GET_ONE_STMT = "select * from PROD_FAV_RECORD where MEM_ID = ? and PROD_ID = ?";
	private static final String GET_RECS_BY_MEMID = "select * from PROD_FAV_RECORD where MEM_ID = ?";

	// delete
	private static final String DELETE_PROD_FAV_RECORD = "delete from PROD_FAV_RECORD where MEM_ID = ? and PROD_ID = ?";

	@Override
	public void insert(ProdFavRecordVO prodFavRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prodFavRecordVO.getMemId());
			pstmt.setInt(2, prodFavRecordVO.getProdId());
//			pstmt.setDate(3, prodFavRecordVO.getProdFavDate());

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
	public void delete(Integer memId, Integer prodId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PWD);

			pstmt = con.prepareStatement(DELETE_PROD_FAV_RECORD);
			pstmt.setInt(1, memId);
			pstmt.setInt(2, prodId);
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
	public ProdFavRecordVO findByPrimaryKey(Integer memId, Integer prodId) {
		ProdFavRecordVO recVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memId);
			pstmt.setInt(2, prodId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				recVO = new ProdFavRecordVO();
				recVO.setMemId(rs.getInt("MEM_ID"));
				recVO.setProdId(rs.getInt("PROD_ID"));
				recVO.setProdFavDate(rs.getDate("PROD_FAV_DATE"));
			}

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

		return recVO;
	}

	@Override
	public Set<ProdFavRecordVO> getRecsBymemId(Integer memId) {
		Set<ProdFavRecordVO> set = new LinkedHashSet<ProdFavRecordVO>();
		ProdFavRecordVO recVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(GET_RECS_BY_MEMID);
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recVO = new ProdFavRecordVO();
				recVO.setProdId(rs.getInt("PROD_ID"));
				recVO.setProdFavDate(rs.getDate("PROD_FAV_DATE"));
				recVO.setMemId(rs.getInt("MEM_ID"));

				set.add(recVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
