package com.function.model;

import java.util.*;
import java.sql.*;

import com.admin.model.AdministratorVO;

public class FunctionDAO implements FunctionDAO_interface {
//	String DRIVER = "com.mysql.cj.jdbc.Driver";
//	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String USER = "root";
//	String PWD = "123456";

	// insert
	private static final String INSERT_STMT = "insert into `FUNCTION` (FUNC_ID, FUNC_NAME, FUNC_DES) values (?, ?, ?)";

	// query
	private static final String GET_ALL_STMT = "select * from `FUNCTION`";
	private static final String GET_ONE_STMT = "select * from `FUNCTION` where FUNC_ID = ?";
	// 查詢擁有此權限的管理員
	private static final String GET_Admins_ByFuncid_STMT = "SELECT * FROM ADMINISTRATOR WHERE ADMIN_ID in (SELECT ADMIN_ID FROM ADMIN_FUNC_LIST WHERE FUNC_ID = ?) ORDER BY ADMIN_ID";

	// delete
	private static final String DELETE_FUNC = "delete from `FUNCTION` where FUNC_ID = ?";
	private static final String DELETE_ADMIN_FUNC_LIST = "delete from ADMIN_FUNC_LIST where FUNC_ID = ?";

	// update
	private static final String UPDATE = "update `FUNCTION` set FUNC_NAME=?, FUNC_DES=? where FUNC_ID = ?";

	@Override
	public void insert(FunctionVO functionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, functionVO.getFuncid());
			pstmt.setString(2, functionVO.getFuncName());
			pstmt.setString(3, functionVO.getFuncdes());

			pstmt.executeUpdate();
			// Hanble any driver errors
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
	public void update(FunctionVO functionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, functionVO.getFuncName());
			pstmt.setString(2, functionVO.getFuncdes());
			pstmt.setInt(3, functionVO.getFuncid());

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
	public void delete(Integer funcid) {
		int updatedCount_AdminFuncList = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_ADMIN_FUNC_LIST);
			pstmt.setInt(1, funcid);
			updatedCount_AdminFuncList = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE_FUNC);
			pstmt.setInt(1, funcid);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

			System.out.println("刪除功能代碼：" + funcid + "，共有 " + updatedCount_AdminFuncList + " 名管理員被影響。");

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
	public FunctionVO findByPrimaryKey(Integer funcid) {
		FunctionVO funcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, funcid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				funcVO = new FunctionVO();
				funcVO.setFuncid(rs.getInt("func_id"));
				funcVO.setFuncName(rs.getString("func_name"));
				funcVO.setFuncdes(rs.getString("func_des"));
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

		return funcVO;
	}

	@Override
	public List<FunctionVO> getAll() {
		List<FunctionVO> list = new ArrayList<FunctionVO>();

		FunctionVO funcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				funcVO = new FunctionVO();
				funcVO.setFuncid(rs.getInt("func_id"));
				funcVO.setFuncName(rs.getString("func_name"));
				funcVO.setFuncdes(rs.getString("func_des"));

				list.add(funcVO);
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

	// 查詢擁有此權限的管理員
	@Override
	public Set<AdministratorVO> getAdminsByFuncid(Integer funcid) {
		Set<AdministratorVO> set = new LinkedHashSet<AdministratorVO>();
		AdministratorVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_Admins_ByFuncid_STMT);
			pstmt.setInt(1, funcid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdministratorVO();

				adminVO.setAdminid(rs.getInt("admin_id"));
				adminVO.setAdminacc(rs.getString("admin_acc"));
				adminVO.setAdminpwd(rs.getString("admin_pwd"));
				adminVO.setAdminStatus(rs.getByte("admin_isdeleted"));
				adminVO.setAdminName(rs.getString("admin_name"));

				set.add(adminVO);
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
