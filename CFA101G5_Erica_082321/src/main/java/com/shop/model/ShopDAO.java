package com.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductVO;
import com.productType.model.TypeDetVO;
import com.productType.model.TypeVO;
import com.store.model.StoreVO;

public class ShopDAO {
	//用商品編號查首圖
	//用商品類型查商品
	//用isbn查商品
	//查商品類型

	//用商品編號找所有圖 --用ProdcutDAO
	//用商品編號找一張圖
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/CFA101G5?serverTimezone=Asia/Taipei";
//	private static final String URL = "jdbc:mysql://localhost:3306/CFA101G5?serverTimezone=Asia/Taipei";
	private static final String USER = "David";
	private static final String PASSWORD = "123456";
	
	
	//查店家商品(上架中)(join商品名稱
	private static final String GET_STORE_PROD = 
			"select p.PROD_ID, i.PROD_NAME, p.STORE_ID, p.PROD_PRICE, p.PROD_SALES_FIG " +
			"from SHOP_PRODUCT p join PRODUCT_INFO i on p.ISBN = i.ISBN where STORE_ID = ? " +
			"and PROD_STATUS = 1 and p.ISBN not between 1001 and 1005 order by PROD_SALES_FIG desc";
	
	//用分類商品 (join商品名稱 join店家名稱
	private static final String GET_PROD_BY_TYPE =
			"select p.PROD_ID, i.PROD_NAME,  p.STORE_ID, s.STORE_NAME, p.PROD_PRICE, p.PROD_SALES_FIG " +  
			"from SHOP_PRODUCT p join PRODUCT_INFO i on p.ISBN = i.ISBN " +
			"join STORE s on p.STORE_ID = s.STORE_ID where PROD_STATUS = 1 " + 
			"and p.ISBN in (select ISBN from PRODUCT_TYPE_DETAILS where PROD_TYPE_ID = ?) " +
			"order by p.PROD_SALES_FIG desc";
	
	//查商品包含店家名稱
	private static final String GET_PROD_INFO = 
			"select p.PROD_ID, p.ISBN, i.PROD_NAME, s.STORE_ID, s.STORE_NAME, " + 
			"p.PROD_PRICE, p.PROD_QTY from SHOP_PRODUCT p join PRODUCT_INFO i " +
			"on p.ISBN = i.ISBN join STORE s on p.STORE_ID = s.STORE_ID where PROD_ID = ?";
	
	//查首圖
	private static final String GET_FIRST_PIC = 
			"select PROD_FIRST_PIC from SHOP_PRODUCT where PROD_ID = ?";
	
	//查商品圖ID
	private static final String GET_PROD_PICS =
			"select PROD_PIC_ID from PRODUCT_PIC where PROD_ID = ?";
	
	//取得商品圖
	private static final String SHOW_PROD_PIC =
			"select PROD_PIC from PRODUCT_PIC where PROD_PIC_ID = ?";
	
	// TOP 8 PRODUCT
	private static final String LIST_TOP_EIGHT =
			"select p.PROD_ID, i.PROD_NAME, p.STORE_ID, s.STORE_NAME, p.PROD_PRICE, PROD_SALES_FIG "
			+ "from SHOP_PRODUCT p join PRODUCT_INFO i on p.ISBN = i.ISBN "
			+ "join STORE s on p.STORE_ID = s.STORE_ID where p.PROD_STATUS = 1 "
			+ "and p.ISBN not between 1001 and 1005 order by PROD_SALES_FIG desc limit 10";
	
	// LATEST 8 PROD 
	private static final String LATEST_PROD=
			"select p.PROD_ID, i.PROD_NAME, p.STORE_ID, s.STORE_NAME, p.PROD_PRICE from SHOP_PRODUCT p " + 
			"join PRODUCT_INFO i on p.ISBN = i.ISBN join STORE s on p.STORE_ID = s.STORE_ID " + 
			"where p.ISBN not between 1001 and 1005 and PROD_STATUS = 1 " + 
			"order by PROD_REG_DATE desc limit 8;";
	//查總銷售量前三名的店家
	private static final String TOP_THREE_STORE =
			"select p.STORE_ID, s.STORE_NAME, sum(PROD_SALES_FIG) from SHOP_PRODUCT p " + 
			"join STORE s on p.STORE_ID = s.STORE_ID group by STORE_ID limit 3";
	
	private static final String GET_PROD_DETAIL = 
			"select p.PROD_ID, p.ISBN, i.PROD_NAME, i.PROD_VER, i.PROD_LANG,  p.STORE_ID, " + 
			"p.PROD_STATUS, p.PROD_PRICE, p.PROD_QTY, p.PROD_INTRO, p.PROD_REG_DATE, " + 
			"p.PROD_SALES_FIG from SHOP_PRODUCT p join PRODUCT_INFO i on p.ISBN = i.ISBN " +
			"where PROD_ID = ?";

	
	//查類型名稱
	private static final String GET_TYPENAME = 
			"select PROD_TYPE_NAME from PRODUCT_TYPE where PROD_TYPE_ID = ?";
	
	//查商品類型
	private static final String GET_PROD_TYPE =
			"select PROD_TYPE_ID, PROD_TYPE_NAME from PRODUCT_TYPE where PROD_TYPE_ID in " + 
			"(select PROD_TYPE_ID from PRODUCT_TYPE_DETAILS where ISBN = ?)";
	
	//查收藏商品
	private static final String GET_FAV_PROD =
			"select p.PROD_ID, p.ISBN, i.PROD_NAME, p.STORE_ID, s.STORE_NAME, p.PROD_STATUS, " + 
			"p.PROD_PRICE, p.PROD_SALES_FIG  from SHOP_PRODUCT p join PRODUCT_INFO i on p.ISBN = i.ISBN " +
			"join STORE s on p.STORE_ID = s.STORE_ID where p.PROD_ID in (select PROD_ID " + 
			"from PROD_FAV_RECORD where MEM_ID = ? order by PROD_FAV_DATE desc)";
	
//	private static final String GET_FAV_PRODID = 
//			"select PROD_ID from PROD_FAV_RECORD where MEM_ID = ? order by PROD_FAV_DATE desc";
	
	//模糊查詢
	private static final String SEARCH_STMT = 
			"select p.PROD_ID, i.PROD_NAME, p.STORE_ID, s.STORE_NAME, p.PROD_PRICE, PROD_SALES_FIG " +
			"from SHOP_PRODUCT p join PRODUCT_INFO i on p.ISBN = i.ISBN " +
			"join STORE s on p.STORE_ID = s.STORE_ID where PROD_STATUS = 1 and p.ISBN not between 1001 and 1005 " + 
			"and i.PROD_NAME like ? order by PROD_SALES_FIG desc;";
	
	
	//用店家查商品 --用ProductDAO
	private static final String FIND_BY_STOREID = "select * from PRODUCT where STORE_ID = ?";
	
	//查運送方式和運費
	private static final String FIND_SHIPMENT = "select ISBN, PROD_PRICE from SHOP_PRODUCT where ISBN between 1001 and 1005 "
												+ "and STORE_ID = ? order by ISBN";
	
	
	// get store products
	public List<ProductVO> getStoreProd(Integer storeId){
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_STORE_PROD);
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(rs.getInt(1));
				prodVO.setProdName(rs.getString(2));
				prodVO.setStoreId(rs.getInt(3));
				prodVO.setPrice(rs.getInt(4));
				prodVO.setSalesFig(rs.getInt(5));
				list.add(prodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public List<ProductVO> getProdByType(Integer typeId){
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_BY_TYPE);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(rs.getInt(1));
				prodVO.setProdName(rs.getString(2));
				prodVO.setStoreId(rs.getInt(3));
				prodVO.setStoreName(rs.getString(4));
				prodVO.setPrice(rs.getInt(5));
				prodVO.setSalesFig(rs.getInt(6));
				list.add(prodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	// get product info by prodId
	public ProductVO getProdInfo(Integer prodId){
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		ProductVO prodVO = new ProductVO();
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_INFO);
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO.setProdId(rs.getInt(1));
				prodVO.setIsbn(rs.getString(2));
				prodVO.setProdName(rs.getString(3));
				prodVO.setStoreId(rs.getInt(4));
				prodVO.setStoreName(rs.getString(5));
				prodVO.setPrice(rs.getInt(6));
				prodVO.setProdQty(rs.getInt(7));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return prodVO;
	}
	
	// Latest 8 Product
	public List<ProductVO> latestProd(){
		List<ProductVO> list = new ArrayList<ProductVO>();
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(LATEST_PROD);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(rs.getInt(1));
				prodVO.setProdName(rs.getString(2));
				prodVO.setStoreId(rs.getInt(3));
				prodVO.setStoreName(rs.getString(4));
				prodVO.setPrice(rs.getInt(5));
				list.add(prodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	// 查收藏商品 
	public List<ProductVO> getFavProd(Integer memId){
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_FAV_PROD);
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(rs.getInt(1));
				prodVO.setIsbn(rs.getString(2));
				prodVO.setProdName(rs.getString(3));
				prodVO.setStoreId(rs.getInt(4));
				prodVO.setStoreName(rs.getString(5));
				prodVO.setStatus(rs.getByte(6));
				prodVO.setPrice(rs.getInt(7));
				prodVO.setSalesFig(rs.getInt(8));
				list.add(prodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	// List Top 8 Product
	public List<ProductVO> listTopProd(){
		List<ProductVO> list = new ArrayList<ProductVO>();
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(LIST_TOP_EIGHT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(rs.getInt(1));
				prodVO.setProdName(rs.getString(2));
				prodVO.setStoreId(rs.getInt(3));
				prodVO.setStoreName(rs.getString(4));
				prodVO.setPrice(rs.getInt(5));
				prodVO.setSalesFig(rs.getInt(6));
				list.add(prodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	//查首圖
	public byte[] getFirstPic(Integer prodId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] pic = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_FIRST_PIC);
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pic = rs.getBytes(1);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("couldn't load batabase driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred, " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return pic;
	}

	//查圖片ID
	public List<Integer> getProdPics(Integer prodId) {
		 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				
			List<Integer> picsId = new ArrayList<Integer>();
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(GET_PROD_PICS);
				pstmt.setInt(1, prodId);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Integer i = (rs.getInt(1));
					picsId.add(i);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occurred. " + se.getMessage());
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
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}
			return picsId;	 
	}
	
	//取得圖片
	public byte[] showProdPic(Integer prodPicId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] pic = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SHOW_PROD_PIC);
			pstmt.setInt(1, prodPicId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pic = rs.getBytes(1);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("couldn't load batabase driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred, " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return pic;
	}

	//列出銷售量前三名的店家
	public List<StoreVO> getTopThreeStore(){
		List<StoreVO> list = new ArrayList<StoreVO>();
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(TOP_THREE_STORE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreVO storeVO = new StoreVO();
				storeVO.setStoreId(rs.getInt(1));
				storeVO.setStoreName(rs.getString(2));
				list.add(storeVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public ProductVO getProdDetail(Integer prodId){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		ProductVO prodVO = new ProductVO();
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_DETAIL);
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO.setProdId(rs.getInt(1));
				prodVO.setIsbn(rs.getString(2));
				prodVO.setProdName(rs.getString(3));
				prodVO.setProdVer(rs.getString(4));
				prodVO.setProdLang(rs.getString(5));
				prodVO.setStoreId(rs.getInt(6));
				prodVO.setStatus(rs.getByte(7));
				prodVO.setPrice(rs.getInt(8));
				prodVO.setProdQty(rs.getInt(9));
				prodVO.setIntro(rs.getString(10));
				prodVO.setRegDate(rs.getDate(11));
				prodVO.setSalesFig(rs.getInt(12));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return prodVO;
	}
	
	public List<TypeVO> getProdType(String isbn){
		List<TypeVO> list = new ArrayList<TypeVO>();
			System.out.println(isbn);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_TYPE);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TypeVO typeVO = new TypeVO();
				typeVO.setTypeid(rs.getInt(1));
				typeVO.setTypeName(rs.getString(2));
				list.add(typeVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public String getTypeName(Integer typeId){
		String typeName = null;
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_TYPENAME);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				typeName = (rs.getString(1));
				System.out.println(typeName);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return typeName;
	}
			
	
	public List<ProductVO> getShipment(Integer storeId){
		List <ProductVO> list = new ArrayList<ProductVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_SHIPMENT);
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductVO prodVO = new ProductVO();
				prodVO.setIsbn(rs.getString(1));
				prodVO.setPrice(rs.getInt(2));
				list.add(prodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Coundn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	//模糊查詢
	public List<ProductVO> searchProd(String keyword){
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SEARCH_STMT);
			keyword = "%" + keyword + "%";
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ProductVO prodVO = new ProductVO();
				prodVO.setProdId(rs.getInt(1));
				prodVO.setProdName(rs.getString(2));
				prodVO.setStoreId(rs.getInt(3));
				prodVO.setStoreName(rs.getString(4));
				prodVO.setPrice(rs.getInt(5));
				prodVO.setSalesFig(rs.getInt(6));
				list.add(prodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	
//好像沒用到 先註解 沒事就可以刪掉	
//	public List<ProductVO> getProdsByStoreId(Integer storeId){
//		List <ProductVO> list = new ArrayList<ProductVO>();
//		ProductVO prodVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try{
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(FIND_BY_STOREID);
//			pstmt.setInt(1, storeId);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next())
//				prodVO = new ProductVO();
//				prodVO.setProdId(rs.getInt(1));;
//				prodVO.setIsbn(rs.getString(2));
//				prodVO.setStoreId(rs.getInt(3));
//				prodVO.setStatus(rs.getByte(4));
//				prodVO.setPrice(rs.getInt(5));
//				prodVO.setProdQty(rs.getInt(6));
//				prodVO.setIntro(rs.getString(7));
//				prodVO.setRegDate(rs.getDate(8));
//				list.add(prodVO);
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occurred. " + se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//			return list;
//	}

//	public List<ProductVO> get
}
