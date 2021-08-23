package com.product.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ProductDAO implements ProductDAO_interface {

//	String DRIVER = "com.mysql.cj.jdbc.Driver";
//	String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String USER = "root";
//	String PWD = "123456";

	// 新增商品(前台)
	private static final String INSERT_STMT = "insert into SHOP_PRODUCT (ISBN, STORE_ID, PROD_STATUS, PROD_PRICE, PROD_QTY, PROD_INTRO, PROD_REG_DATE, PROD_SALES_FIG, PROD_FIRST_PIC) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// 修改 商品價格、商品數量、商品簡介(前/後台)
	private static final String UPDATE = "update SHOP_PRODUCT set PROD_PRICE = ?, PROD_QTY = ?, PROD_INTRO = ?where PROD_ID = ?";
	// 修改商品狀態 (前/後台)
	private static final String UPDATE_STATUS = "update SHOP_PRODUCT set PROD_STATUS = ? where PROD_ID = ?";
	// 修改商品首圖
	private static final String UPDATE_FIRSTPIC = "update SHOP_PRODUCT set PROD_FIRST_PIC = ? where PROD_ID = ?";

	// 找單一商品
	private static final String GET_ONE_STMT = "select * from SHOP_PRODUCT where PROD_ID = ?";
	// 用店家ID取得他的所有商品
	private static final String GET_PROD_BY_STOREID = "select * from SHOP_PRODUCT where STORE_ID = ? and LENGTH(ISBN) != 4";
	// 以店家為基準，以商品狀態列出符合商品 (前台)
	private static final String GET_PROD_BY_STOREID_AND_STATUS = "select * from SHOP_PRODUCT where STORE_ID = ? and PROD_STATUS = ? and LENGTH(ISBN) != 4";
	// 用商品狀態取得符合的商品 (後台)
	private static final String GET_PROD_BY_STATUS = "select * from SHOP_PRODUCT where PROD_STATUS = ? and LENGTH(ISBN) != 4";
	// 取得所有商品(後台)
	private static final String GET_ALL_STMT = "select * from SHOP_PRODUCT where LENGTH(ISBN) != 4";
	// 用商品流水號取得圖片 (一對多)
	private static final String GET_PICS_BY_PRODID = "select * from PRODUCT_PIC where PROD_ID = ? order by PROD_PIC_ID";
	// 複合查詢

	// 抓首圖
	private static final String GET_FIRST_PIC = "select PROD_FIRST_PIC from SHOP_PRODUCT where PROD_ID = ?";

//	// delete
//	private static final String DELETE_PICS = "delete from PRODUCT_PIC where PROD_ID = ?";
//	private static final String DELETE_PRODUCT = "delete from SHOP_PRODUCT where PROD_ID = ?";

	@Override
	public void insert(ProductVO ProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ProductVO.getIsbn());
			pstmt.setInt(2, ProductVO.getStoreId());
			pstmt.setByte(3, ProductVO.getStatus());
			pstmt.setInt(4, ProductVO.getPrice());
			pstmt.setInt(5, ProductVO.getProdQty());
			pstmt.setString(6, ProductVO.getIntro());
			pstmt.setDate(7, ProductVO.getRegDate());
			pstmt.setInt(8, ProductVO.getSalesFig());
			pstmt.setBytes(9, ProductVO.getFirstPic());

			// 商品流水號自 1000001 開始 (自增主鍵值)
			pstmt.executeUpdate("set auto_increment_offset=1000001;");

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

	// 修改 商品價格、商品數量、商品簡介(前/後台)
	@Override
	public void update(ProductVO ProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ProductVO.getPrice());
			pstmt.setInt(2, ProductVO.getProdQty());
			pstmt.setString(3, ProductVO.getIntro());
			pstmt.setInt(4, ProductVO.getProdId());

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

//	@Override
//	public void delete(Integer prodId) {
//		int updateCount_PICS = 0;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PWD);
//
//			con.setAutoCommit(false);
//
//			// 先刪除商品圖片
//			pstmt = con.prepareStatement(DELETE_PICS);
//			pstmt.setInt(1, prodId);
//			updateCount_PICS = pstmt.executeUpdate();
//
//			// 再刪除商品
//			pstmt = con.prepareStatement(DELETE_PRODUCT);
//			pstmt.setInt(1, prodId);
//			pstmt.executeUpdate();
//
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("刪除商品編號：" + prodId + "時，共有" + updateCount_PICS + "張商品圖片被刪除。");
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
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
//
//	}

	@Override
	public ProductVO findByPrimaryKey(Integer prodId) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prodId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProdId(rs.getInt("PROD_ID"));
				productVO.setIsbn(rs.getString("ISBN"));
				productVO.setStoreId(rs.getInt("STORE_ID"));
				productVO.setStatus(rs.getByte("PROD_STATUS"));
				productVO.setPrice(rs.getInt("PROD_PRICE"));
				productVO.setProdQty(rs.getInt("PROD_QTY"));
				productVO.setIntro(rs.getString("PROD_INTRO"));
				productVO.setRegDate(rs.getDate("PROD_REG_DATE"));
				productVO.setSalesFig(rs.getInt("PROD_SALES_FIG"));
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

		return productVO;
	}

	@Override
	public List<ProductVO> findByStoreid(Integer storeid) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_BY_STOREID);

			pstmt.setInt(1, storeid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProdId(rs.getInt("PROD_ID"));
				productVO.setIsbn(rs.getString("ISBN"));
				productVO.setStoreId(rs.getInt("STORE_ID"));
				productVO.setStatus(rs.getByte("PROD_STATUS"));
				productVO.setPrice(rs.getInt("PROD_PRICE"));
				productVO.setProdQty(rs.getInt("PROD_QTY"));
				productVO.setIntro(rs.getString("PROD_INTRO"));
				productVO.setRegDate(rs.getDate("PROD_REG_DATE"));
				productVO.setSalesFig(rs.getInt("PROD_SALES_FIG"));
				productVO.setFirstPic(rs.getBytes("PROD_FIRST_PIC"));
				list.add(productVO);
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
	public List<ProductVO> findByStatus(Byte status) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_BY_STATUS);

			pstmt.setByte(1, status);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProdId(rs.getInt("PROD_ID"));
				productVO.setIsbn(rs.getString("ISBN"));
				productVO.setStoreId(rs.getInt("STORE_ID"));
				productVO.setStatus(rs.getByte("PROD_STATUS"));
				productVO.setPrice(rs.getInt("PROD_PRICE"));
				productVO.setProdQty(rs.getInt("PROD_QTY"));
				productVO.setIntro(rs.getString("PROD_INTRO"));
				productVO.setRegDate(rs.getDate("PROD_REG_DATE"));
				productVO.setSalesFig(rs.getInt("PROD_SALES_FIG"));
				productVO.setFirstPic(rs.getBytes("PROD_FIRST_PIC"));
				list.add(productVO);
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
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProdId(rs.getInt("PROD_ID"));
				productVO.setIsbn(rs.getString("ISBN"));
				productVO.setStoreId(rs.getInt("STORE_ID"));
				productVO.setStatus(rs.getByte("PROD_STATUS"));
				productVO.setPrice(rs.getInt("PROD_PRICE"));
				productVO.setProdQty(rs.getInt("PROD_QTY"));
				productVO.setIntro(rs.getString("PROD_INTRO"));
				productVO.setRegDate(rs.getDate("PROD_REG_DATE"));
				productVO.setSalesFig(rs.getInt("PROD_SALES_FIG"));
				productVO.setFirstPic(rs.getBytes("PROD_FIRST_PIC"));
				list.add(productVO);
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
	public Set<ProdPicVO> getPicsByProdid(Integer prodId) {
		Set<ProdPicVO> set = new LinkedHashSet<ProdPicVO>();
		ProdPicVO picVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_PICS_BY_PRODID);
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				picVO = new ProdPicVO();
				picVO.setPicId(rs.getInt("PROD_PIC_ID"));
				picVO.setProdId(rs.getInt("PROD_ID"));
				picVO.setPic(rs.getBytes("PROD_PIC"));
				set.add(picVO);
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

	// 新增商品時，同時新增商品圖片
	@Override
	public Integer insertWithPics(ProductVO productVO, List<ProdPicVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;
		// 掘取對應的自增主鍵值
		Integer next_prodId = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);

			con.setAutoCommit(false);

			String[] cols = { "PROD_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, productVO.getIsbn());
			pstmt.setInt(2, productVO.getStoreId());
			pstmt.setByte(3, productVO.getStatus());
			pstmt.setInt(4, productVO.getPrice());
			pstmt.setInt(5, productVO.getProdQty());
			pstmt.setString(6, productVO.getIntro());
			pstmt.setDate(7, productVO.getRegDate());
			pstmt.setInt(8, productVO.getSalesFig());
			pstmt.setBytes(9, productVO.getFirstPic());

			Statement stmt = con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1000001;");
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_prodId = rs.getInt(1);
				System.out.println("自增主鍵值：" + next_prodId + " (剛新增成功的商品流水號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			// 再新增商品圖片
			ProdPicDAO dao = new ProdPicDAO();
			System.out.println("list.size()-A = " + list.size());
			for (ProdPicVO aPic : list) {
				aPic.setProdId(next_prodId);
				dao.insertWithProduct(aPic, con);
			}

			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B = " + list.size());
			System.out.println("新增商品(編號)" + next_prodId + "時，共有" + list.size() + "張照片同時被新增");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back：由 product");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		return next_prodId;
	}

//	@Override
//	public List<ProductVO> getAll(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<ProductVO> findByStoreid_and_Status(Integer storeid, Byte status) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_BY_STOREID_AND_STATUS);

			pstmt.setInt(1, storeid);
			pstmt.setByte(2, status);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProdId(rs.getInt("PROD_ID"));
				productVO.setIsbn(rs.getString("ISBN"));
				productVO.setStoreId(rs.getInt("STORE_ID"));
				productVO.setStatus(rs.getByte("PROD_STATUS"));
				productVO.setPrice(rs.getInt("PROD_PRICE"));
				productVO.setProdQty(rs.getInt("PROD_QTY"));
				productVO.setIntro(rs.getString("PROD_INTRO"));
				productVO.setRegDate(rs.getDate("PROD_REG_DATE"));
				productVO.setSalesFig(rs.getInt("PROD_SALES_FIG"));
				productVO.setFirstPic(rs.getBytes("PROD_FIRST_PIC"));
				list.add(productVO);
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
	public void updateStatus(ProductVO ProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setByte(1, ProductVO.getStatus());
			pstmt.setInt(2, ProductVO.getProdId());

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
	public void updateFirstPic(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_FIRSTPIC);

			pstmt.setBytes(1, productVO.getFirstPic());
			pstmt.setInt(2, productVO.getProdId());

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

//	@Override
//	public InputStream getFirstPic(Integer prodId) throws SQLException {
//		InputStream in = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PWD);
//			pstmt = con.prepareStatement(GET_FIRST_PIC);
//
//			pstmt.setInt(1, prodId);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				in = rs.getBinaryStream("PROD_FIRST_PIC");
//			}
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
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
//
//		return in;
//	}
}
