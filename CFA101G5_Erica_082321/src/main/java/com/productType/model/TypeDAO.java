package com.productType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.admin.model.AdministratorVO;
import com.productInfo.model.ProdInfoVO;
import com.productType.model.TypeDetVO;

public class TypeDAO implements TypeDAO_interface {

//	String DRIVER = "com.mysql.cj.jdbc.Driver";
//	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String USER = "root";
//	String PWD = "123456";

	// insert
	private static final String INSERT_STMT = "insert into PRODUCT_TYPE (PROD_TYPE_NAME, PROD_TYPE_CLASS) values (?, ?)";

	// query
	private static final String GET_ALL_STMT = "select * from PRODUCT_TYPE";
	private static final String GET_ONE_STMT = "select * from PRODUCT_TYPE where PROD_TYPE_ID = ?";

	// query 列出不重複標籤
	private static final String GET_CLASS_LIST = "select distinct PROD_TYPE_CLASS from PRODUCT_TYPE";
	
	// query 列出該類型的所有產品 (一對多)
	private static final String GET_ProdInfo_By_Typeid = "select * from PRODUCT_INFO where ISBN in (select ISBN from PRODUCT_TYPE_DETAILS where PROD_TYPE_ID = ?) order by ISBN";

	// update
	private static final String UPDATE = "update PRODUCT_TYPE set PROD_TYPE_NAME = ?, PROD_TYPE_CLASS = ? where PROD_TYPE_ID = ?";

	@Override
	public void insert(TypeVO typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, typeVO.getTypeName());
			pstmt.setString(2, typeVO.getTypeClass());

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
	public void update(TypeVO typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, typeVO.getTypeName());
			pstmt.setString(2, typeVO.getTypeClass());
			pstmt.setInt(3, typeVO.getTypeid());

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
	public TypeVO findByPrimaryKey(Integer typeid) {
		TypeVO typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, typeid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				typeVO = new TypeVO();
				typeVO.setTypeid(rs.getInt("PROD_TYPE_ID"));
				typeVO.setTypeName(rs.getString("PROD_TYPE_NAME"));
				typeVO.setTypeClass(rs.getString("PROD_TYPE_CLASS"));
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

		return typeVO;
	}

	@Override
	public List<TypeVO> getAll() {
		List<TypeVO> list = new ArrayList<TypeVO>();

		TypeVO typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				typeVO = new TypeVO();
				typeVO.setTypeid(rs.getInt("PROD_TYPE_ID"));
				typeVO.setTypeName(rs.getString("PROD_TYPE_NAME"));
				typeVO.setTypeClass(rs.getString("PROD_TYPE_CLASS"));

				list.add(typeVO);
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

	// 列出該類型的所有產品 (一對多)
	@Override
	public Set<ProdInfoVO> getProdInfoByTypeId(Integer typeid) {
		Set<ProdInfoVO> set = new LinkedHashSet<ProdInfoVO>();
		ProdInfoVO prodInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ProdInfo_By_Typeid);
			pstmt.setInt(1, typeid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodInfoVO = new ProdInfoVO();
				prodInfoVO.setIsbn(rs.getString("ISBN"));
				prodInfoVO.setProdName(rs.getString("PROD_NAME"));
				prodInfoVO.setProdLang(rs.getString("PROD_LANG"));
				prodInfoVO.setProdVer(rs.getString("PROD_VER"));

				set.add(prodInfoVO);
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

	// 取得不重複標籤
	@Override
	public List<TypeVO> getTypeClass() {
		List<TypeVO> list = new ArrayList<TypeVO>();

		TypeVO typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_CLASS_LIST);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				typeVO = new TypeVO();
				typeVO.setTypeClass(rs.getString("PROD_TYPE_CLASS"));

				list.add(typeVO);
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

}
