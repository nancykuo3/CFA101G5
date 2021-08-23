package com.adminfunclist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.function.model.FunctionVO;

public class AdminFuncListDAO implements AdminFuncListDAO_interface {
//	String DRIVER = "com.mysql.cj.jdbc.Driver";
//	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String USER = "root";
//	String PWD = "123456";

	// insert
	private static final String INSERT_STMT = "insert into ADMIN_FUNC_LIST (ADMIN_ID, FUNC_ID) values (?, ?)";

	// query
	private static final String GET_ALL_STMT = "select * from ADMIN_FUNC_LIST";
	private static final String GET_ONE_STMT = "select * from ADMIN_FUNC_LIST where ADMIN_ID = ? and FUNC_ID = ?";

	// delete
	private static final String DELETE_ADMIN_FUNC_LIST = "delete from ADMIN_FUNC_LIST where ADMIN_ID = ?";

	// update (有點奇怪的東西，實際上應該不會用到...)
//	private static final String UPDATE_BY_ADMIN = "update ADMIN_FUNC_LIST set ADMIN_ID = ?, FUNC_ID = ? where ADMIN_ID = ? and FUNC_ID = ?;";

	@Override
	public void insert(AdminFuncListVO adminFuncListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, adminFuncListVO.getAdminid());
			pstmt.setInt(2, adminFuncListVO.getFuncid());

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
	public void delete(Integer adminid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_ADMIN_FUNC_LIST);
			pstmt.setInt(1, adminid);

			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AdminFuncListVO findByPK(Integer adminid, Integer funcid) {
		AdminFuncListVO adminFuncListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adminid);
			pstmt.setInt(2, funcid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminFuncListVO = new AdminFuncListVO();
				adminFuncListVO.setAdminid(rs.getInt("admin_id"));
				adminFuncListVO.setFuncid(rs.getInt("func_id"));
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

		return adminFuncListVO;
	}

	@Override
	public List<AdminFuncListVO> getAll() {
		List<AdminFuncListVO> list = new ArrayList<AdminFuncListVO>();

		AdminFuncListVO adminFuncListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminFuncListVO = new AdminFuncListVO();
				adminFuncListVO.setAdminid(rs.getInt("admin_id"));
				adminFuncListVO.setFuncid(rs.getInt("func_id"));

				list.add(adminFuncListVO);
				// Handle any driver errors
			}
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
		return list;
	}
}

//	@Override
//	public List<AdminFuncListVO> findFuncsByAdminid(Integer adminid) {
//		List<AdminFuncListVO> list = new ArrayList<AdminFuncListVO>();
//
//		AdminFuncListVO adminFuncListVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PWD);
//			pstmt = con.prepareStatement(？？？);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				adminFuncListVO = new AdminFuncListVO();
//				adminFuncListVO.setAdminid(rs.getInt("admin_id"));
//				adminFuncListVO.setFuncid(rs.getInt("func_id"));
//
//				list.add(adminFuncListVO);
//				// Handle any driver errors
//			}
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//}
