package com.productInfo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.admin.model.AdministratorVO;
import com.product.model.ProductVO;
import com.productInfo.controller.compositeQuery_ProdInfo;
import com.productType.model.TypeVO;

public class ProdInfoDAO implements ProdInfoDAO_interface {

//	String DRIVER = "com.mysql.cj.jdbc.Driver";
//	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String USER = "root";
//	String PWD = "123456";

	// insert
	private static final String INSERT_STMT = "insert into PRODUCT_INFO (ISBN, PROD_NAME, PROD_LANG, PROD_VER) values (?, ?, ?, ?)";

	// query
	private static final String GET_ALL_STMT = "select * from PRODUCT_INFO where ISBN not in (1001, 1002, 1003, 1004, 1005)";
	private static final String GET_ONE_STMT = "select * from PRODUCT_INFO where ISBN = ?";

	// 查詢某ISBN於商城的所有商品(一對多) (往商城商品)
	private static final String GET_PRODS_BY_ISBN = "select * from SHOP_PRODUCT where ISBN = ?";

	// 查詢某ISBN的所有類型(標籤)(一對多) (往商品類型明細至商品類型(一層子查詢))
	private static final String GET_TYPES_BY_ISBN = "select * from PRODUCT_TYPE where PROD_TYPE_ID in (select PROD_TYPE_ID from PRODUCT_TYPE_DETAILS where ISBN = ?) order by PROD_TYPE_ID";

	// update
	private static final String UPDATE = "update PRODUCT_INFO set PROD_NAME = ?, PROD_LANG = ?, PROD_VER = ? where ISBN = ?";

	@Override
	public void insert(ProdInfoVO prodInfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, prodInfoVO.getIsbn());
			pstmt.setString(2, prodInfoVO.getProdName());
			pstmt.setString(3, prodInfoVO.getProdLang());
			pstmt.setString(4, prodInfoVO.getProdVer());

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
	public void update(ProdInfoVO prodInfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, prodInfoVO.getProdName());
			pstmt.setString(2, prodInfoVO.getProdLang());
			pstmt.setString(3, prodInfoVO.getProdVer());
			pstmt.setString(4, prodInfoVO.getIsbn());

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
	public ProdInfoVO findByPrimaryKey(String isbn) {
		ProdInfoVO prodInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodInfoVO = new ProdInfoVO();
				prodInfoVO.setIsbn(rs.getString("ISBN"));
				prodInfoVO.setProdName(rs.getString("PROD_NAME"));
				prodInfoVO.setProdLang(rs.getString("PROD_LANG"));
				prodInfoVO.setProdVer(rs.getString("PROD_VER"));
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

		return prodInfoVO;
	}

	@Override
	public List<ProdInfoVO> getAll() {

		List<ProdInfoVO> list = new ArrayList<ProdInfoVO>();

		ProdInfoVO prodInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodInfoVO = new ProdInfoVO();
				prodInfoVO.setIsbn(rs.getString("ISBN"));
				prodInfoVO.setProdName(rs.getString("PROD_NAME"));
				prodInfoVO.setProdLang(rs.getString("PROD_LANG"));
				prodInfoVO.setProdVer(rs.getString("PROD_VER"));

				list.add(prodInfoVO);
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
		return list;
	}

	// 查詢某ISBN於商城的所有商品(一對多) (往商城商品)
	@Override
	public List<ProductVO> getProdsByISBN(String isbn) {

		List<ProductVO> list = new LinkedList<ProductVO>();
		ProductVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_PRODS_BY_ISBN);
			pstmt.setString(1, isbn);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProductVO();
				prodVO.setProdId(rs.getInt("PROD_ID"));
				prodVO.setIsbn(rs.getString("ISBN"));
				prodVO.setStoreId(rs.getInt("STORE_ID"));
				prodVO.setStatus(rs.getByte("PROD_STATUS"));
				prodVO.setPrice(rs.getInt("PROD_PRICE"));
				prodVO.setProdQty(rs.getInt("PROD_QTY"));
				prodVO.setIntro(rs.getString("PROD_INTRO"));
				prodVO.setRegDate(rs.getDate("PROD_REG_DATE"));
				prodVO.setSalesFig(rs.getInt("PROD_SALES_FIG"));
				prodVO.setFirstPic(rs.getBytes("PROD_FIRST_PIC"));

				list.add(prodVO);
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

	// 查詢某ISBN的所有類型(標籤)(一對多) (往商品類型明細至商品類型(一層子查詢))
	@Override
	public Set<TypeVO> getTypesByISBN(String isbn) {
		Set<TypeVO> set = new LinkedHashSet<TypeVO>();
		TypeVO typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_TYPES_BY_ISBN);
			pstmt.setString(1, isbn);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				typeVO = new TypeVO();

				typeVO.setTypeid(rs.getInt("PROD_TYPE_ID"));
				typeVO.setTypeName(rs.getString("PROD_TYPE_NAME"));
				typeVO.setTypeClass(rs.getString("PROD_TYPE_CLASS"));

				set.add(typeVO);
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

	// composite query
	@Override
	public List<ProdInfoVO> getAll(Map<String, String[]> map) {
		List<ProdInfoVO> list = new ArrayList<ProdInfoVO>();
		ProdInfoVO prodInfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);

			String finalSQL = "select * from PRODUCT_INFO " + compositeQuery_ProdInfo.get_WhereCondition(map)
					+ "order by ISBN";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("複合查詢指令為：" + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodInfoVO = new ProdInfoVO();
				prodInfoVO.setIsbn(rs.getString("ISBN"));
				prodInfoVO.setProdName(rs.getString("PROD_NAME"));
				prodInfoVO.setProdLang(rs.getString("PROD_LANG"));
				prodInfoVO.setProdVer(rs.getString("PROD_VER"));

				list.add(prodInfoVO);
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
