package com.admin.model;

import java.util.*;
import java.sql.*;

import com.function.model.FunctionVO;

public class AdministratorDAO implements AdministratorDAO_interface {
//	String DRIVER = "com.mysql.cj.jdbc.Driver";
//	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String USER = "root";
//	String PWD = "123456";

	// insert
	private static final String INSERT_STMT = "insert into ADMINISTRATOR (ADMIN_ID, ADMIN_ACC, ADMIN_PWD, ADMIN_ISDELETED, ADMIN_NAME) values (?, ?, ?, ?, ?)";
//	ADMIN_ID, ADMIN_ACC, ADMIN_PWD, ADMIN_ISDELETED, ADMIN_NAME
	// query
	private static final String GET_ALL_STMT = "select * from ADMINISTRATOR";
	private static final String GET_ONE_STMT = "select * from ADMINISTRATOR where ADMIN_ID = ?";
	// 查詢該管理員有哪些權限
	private static final String GET_Funcs_ByAdminid_STMT = "select * from `FUNCTION` where FUNC_ID in (select FUNC_ID from ADMIN_FUNC_LIST where ADMIN_ID = ?) order by FUNC_ID;";

	// delete
	private static final String DELETE_ADMIN = "delete from ADMINISTRATOR where ADMIN_ID = ?";
	private static final String DELETE_ADMIN_FUNC_LIST = "delete from ADMIN_FUNC_LIST where ADMIN_ID = ?";

	// update
	private static final String UPDATE = "update ADMINISTRATOR set ADMIN_ACC = ?, ADMIN_PWD = ?, ADMIN_ISDELETED = ?, ADMIN_NAME = ? where ADMIN_ID = ?";
	// 管理員失效/有效
	private static final String UPDATE_STATUS = "update ADMINISTRATOR set ADMIN_ISDELETED = ? where ADMIN_ID = ?;";

	// 管理員登入(後台)
	private static final String Get_ONEADMIN = "SELECT * FROM ADMINISTRATOR wHERE admin_acc=? and  admin_pwd=?";

	@Override
	public void insert(AdministratorVO administratorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, administratorVO.getAdminid());
			pstmt.setString(2, administratorVO.getAdminacc());
			pstmt.setString(3, administratorVO.getAdminpwd());
			pstmt.setByte(4, administratorVO.getAdminStatus());
			pstmt.setString(5, administratorVO.getAdminName());

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
	public void update(AdministratorVO administratorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administratorVO.getAdminacc());
			pstmt.setString(2, administratorVO.getAdminpwd());
			pstmt.setByte(3, administratorVO.getAdminStatus());
			pstmt.setString(4, administratorVO.getAdminName());
			pstmt.setInt(5, administratorVO.getAdminid());

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
	public void updateAdminStatus(AdministratorVO administratorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setByte(1, administratorVO.getAdminStatus());
			pstmt.setInt(2, administratorVO.getAdminid());

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

			pstmt = con.prepareStatement(DELETE_ADMIN);
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
	public AdministratorVO findByPrimaryKey(Integer adminid) {
		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adminid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdminid(rs.getInt("admin_id"));
				administratorVO.setAdminacc(rs.getString("admin_acc"));
				administratorVO.setAdminpwd(rs.getString("admin_pwd"));
				administratorVO.setAdminStatus(rs.getByte("admin_isdeleted"));
				administratorVO.setAdminName(rs.getString("admin_name"));
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

		return administratorVO;
	}

	@Override
	public List<AdministratorVO> getAll() {
		List<AdministratorVO> list = new ArrayList<AdministratorVO>();

		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdminid(rs.getInt("admin_id"));
				administratorVO.setAdminacc(rs.getString("admin_acc"));
				administratorVO.setAdminpwd(rs.getString("admin_pwd"));
				administratorVO.setAdminStatus(rs.getByte("admin_isdeleted"));
				administratorVO.setAdminName(rs.getString("admin_name"));

				list.add(administratorVO);
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

	// 查詢該管理員有哪些權限
	@Override
	public List<FunctionVO> getFuncsByAdminid(Integer adminid) {
		List<FunctionVO> list = new ArrayList<FunctionVO>();
		FunctionVO funcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_Funcs_ByAdminid_STMT);
			pstmt.setInt(1, adminid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				funcVO = new FunctionVO();

				funcVO.setFuncid(rs.getInt("FUNC_ID"));
				funcVO.setFuncName(rs.getString("FUNC_NAME"));
				funcVO.setFuncdes(rs.getString("FUNC_DES"));

				list.add(funcVO);
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
		return list;
	}

	@Override
	public AdministratorVO getAdmin(String admin_acc, String admin_pwd) {
		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(Get_ONEADMIN);

			pstmt.setString(1, admin_acc);
			pstmt.setString(2, admin_pwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				administratorVO = new AdministratorVO();
				administratorVO.setAdminid(rs.getInt("admin_id"));
				administratorVO.setAdminacc(rs.getString("admin_acc"));
				administratorVO.setAdminpwd(rs.getString("admin_pwd"));
				administratorVO.setAdminStatus(rs.getByte("admin_isdeleted"));
				administratorVO.setAdminName(rs.getString("admin_name"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法載入資料庫錯誤" + e.getMessage());
			// 處理任何SQL錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
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
		return administratorVO;
	}
}
