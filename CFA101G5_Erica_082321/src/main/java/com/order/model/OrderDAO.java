package com.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements OrderDAO_interface {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	private static final String URL = "jdbc:mysql://localhost:3306/CFA101G5?serverTimezone=Asia/Taipei";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	//新增訂單
	private static final String INSERT_STMT = 
			"insert into ORDERS (MEM_ID, STORE_ID, ORDER_DATE, ORDER_SHIPMENT, ORDER_NAME, ORDER_MOBILE, "
			+ "ORDER_ADDR, ORDER_SHIP_FEE, ORDER_AMOUNT, ORDER_PAYMENT, ORDER_MEMO, ORDER_STATUS) "
			+ "values (?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	//更新訂單狀態 (確認或取消訂單)
	private static final String UPDATE_STATUS_STMT = 
			"update ORDERS set ORDER_STATUS = ? where ORDER_ID = ?";
	//更新貨運追蹤號碼 及 更新訂單狀態為已出貨
	private static final String UPDATE_SHIPMENT_STMT = 
			"update ORDERS set ORDER_STATUS = 4, ORDER_SHIP_DATE = NOW(), ORDER_TRACK_NO = ? "
			+ "where ORDER_ID = ? and ORDER_STATUS = 3";
	//用訂單編號查詢訂單
	private static final String FIND_BY_PK_STMT =
			"select MEM_ID, STORE_ID, ORDER_DATE, ORDER_SHIPMENT, ORDER_NAME, ORDER_MOBILE, ORDER_ADDR, ORDER_SHIP_FEE, "
			+ "ORDER_AMOUNT, ORDER_PAYMENT, ORDER_MEMO, ORDER_SHIP_DATE, ORDER_TRACK_NO, ORDER_STATUS "
			+ "from ORDERS where ORDER_ID = ?";
	//列出某店家的所有訂單
	private static final String LISTALL_STMT = 
			"select * from ORDERS where STORE_ID = ? order by ORDER_ID desc";
	//列出某會員的所有訂單
	private static final String LISTALL_BY_MEM_STMT = 
			"select o.ORDER_ID, o.MEM_ID, o.STORE_ID, s.STORE_NAME, o.ORDER_DATE, o.ORDER_SHIPMENT, " + 
			"o.ORDER_NAME, o.ORDER_MOBILE, o.ORDER_ADDR, o.ORDER_SHIP_FEE, o.ORDER_AMOUNT, " + 
			"o.ORDER_PAYMENT, o.ORDER_MEMO, o.ORDER_SHIP_DATE, o.ORDER_TRACK_NO, o.ORDER_STATUS " + 
			"from ORDERS o join STORE s on o.STORE_ID = s.STORE_ID where MEM_ID = ? order by ORDER_ID desc";
	//依訂單狀態列出某店家的訂單
	private static final String FIND_BY_STATUS_STMT = 
			"select ORDER_ID, MEM_ID, STORE_ID, ORDER_DATE, ORDER_SHIPMENT, ORDER_SHIP_FEE, ORDER_AMOUNT, "
			+ "ORDER_PAYMENT, ORDER_MEMO, ORDER_STATUS from ORDERS where STORE_ID = ? and ORDER_STATUS = ?";
	//用訂單編號查詢訂單明細 & 商品名稱
	private static final String ORD_DETAILS_STMT = 
			"select d.PROD_ID, i.PROD_NAME, d.ORDER_QTY, d.ORDER_UNIT_PRICE from ORDER_DETAILS d " + 
			"join SHOP_PRODUCT p on d.PROD_ID = p.PROD_ID join  PRODUCT_INFO i on p.ISBN = i.ISBN " + 
			"where d.ORDER_ID = ?";
	//扣除庫存 & 增加銷售量
	private static final String REDUCE_STOCK = 
			"update SHOP_PRODUCT set PROD_QTY = PROD_QTY - ?, PROD_SALES_FIG = PROD_SALES_FIG + ? " +
			"where PROD_ID = ?";
	//加回庫存 & 減少銷售量
	private static final String ADD_STOCK = 
			"update SHOP_PRODUCT set PROD_QTY = PROD_QTY + ?, PROD_SALES_FIG = PROD_SALES_FIG - ? " +
			"where PROD_ID = ?";
	
	//新增訂單 & 訂單明細 ＆ 扣除庫存
	@Override
	public Integer insertWithOrdDet(OrderVO orderVO, List<OrdDetVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt =null;
		Integer next_ordId =null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con.setAutoCommit(false);
			// 先新增訂單
			String cols[] = {"1"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setInt(1, orderVO.getMemId());
			pstmt.setInt(2, orderVO.getStoreId());
			pstmt.setByte(3, orderVO.getShipment());
			pstmt.setString(4, orderVO.getOrdName());
			pstmt.setString(5, orderVO.getOrdMobile());
			pstmt.setString(6, orderVO.getOrdAddr());
			pstmt.setInt(7, orderVO.getShipFee());
			pstmt.setInt(8, orderVO.getAmount());
			pstmt.setByte(9, orderVO.getPayment());
			pstmt.setString(10, orderVO.getMemo());
			pstmt.setByte(11, orderVO.getStatus());
			pstmt.executeUpdate();
			
			// 取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_ordId = rs.getInt(1);
				System.out.println("自增主鍵值= " + next_ordId + "剛新增成功的訂單");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			OrdDetDAO dao = new OrdDetDAO();
			System.out.println("list.size()-A="+list.size());
			for (OrdDetVO aOrdDet : list) {
				aOrdDet.setOrdId(new Integer(next_ordId));
				dao.insert(aOrdDet, con);
				//扣掉庫存
				Integer prodId = aOrdDet.getProdId();
				Integer ordQty = aOrdDet.getOrdQty();
				reduceStock(prodId, ordQty, con);
			}
			
			// 2. 設定於pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_ordId + "時, 共有" + list.size() + "項商品同時被新增");
		// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3. 設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being rolled back from dept");
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
		return next_ordId;
	}
	
	//確認訂單
	@Override
	public void confirmOrder(Integer status, Integer ordId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);
			pstmt.setInt(1, status);
			pstmt.setInt(2, ordId);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
	
	//取消訂單
	@Override
	public void cancelOrder(Integer status, Integer ordId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);
			pstmt.setInt(1, status);
			pstmt.setInt(2, ordId);
			pstmt.executeUpdate();
			
			//加回庫存
			List<OrdDetVO> list = getOrdDetsByOrdId(ordId);
			for (OrdDetVO detVO: list) {
				Integer prodId = detVO.getProdId();
				Integer ordQty = detVO.getOrdQty();
				addStock(prodId, ordQty, con);				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	}
	
	//更新貨運追蹤號碼 並更改訂單狀態為已出貨
	@Override
	public void updateTrackNo(Integer ordId, String trackNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SHIPMENT_STMT);
			pstmt.setString(1, trackNo);
			pstmt.setInt(2, ordId);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " 
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	//用訂單編號查訂單
	@Override
	public OrderVO findByPrimaryKey(Integer ordId) {
		
			OrderVO orderVO = null;
			Connection con = null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			
			try {			
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);			
				pstmt = con.prepareStatement(FIND_BY_PK_STMT);
				pstmt.setInt(1, ordId);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					orderVO = new OrderVO();
					orderVO.setOrdId(ordId);
					orderVO.setMemId(rs.getInt(1));
					orderVO.setStoreId(rs.getInt(2));
					orderVO.setOrdDate(rs.getTimestamp(3));
					orderVO.setShipment(rs.getByte(4));
					orderVO.setOrdName(rs.getString(5));
					orderVO.setOrdMobile(rs.getString(6));
					orderVO.setOrdAddr(rs.getString(7));
					orderVO.setShipFee(rs.getInt(8));
					orderVO.setAmount(rs.getInt(9));
					orderVO.setPayment(rs.getByte(10));
					orderVO.setMemo(rs.getString(11));
					orderVO.setShipDate(rs.getDate(12));
					orderVO.setTrackNo(rs.getString(13));
					orderVO.setStatus(rs.getByte(14));
				}				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. " 
						+ e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return orderVO;
		}

	//列出某店家的所有訂單
	@Override
	public List<OrderVO> listAllByStoreId(Integer storeId) {

		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);			
			pstmt = con.prepareStatement(LISTALL_STMT);
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrdId(rs.getInt(1));
				orderVO.setMemId(rs.getInt(2));
				orderVO.setStoreId(rs.getInt(3));
				orderVO.setOrdDate(rs.getTimestamp(4));
				orderVO.setShipment(rs.getByte(5));
				orderVO.setOrdName(rs.getString(6));
				orderVO.setOrdMobile(rs.getString(7));
				orderVO.setOrdAddr(rs.getString(8));
				orderVO.setShipFee(rs.getInt(9));
				orderVO.setAmount(rs.getInt(10));
				orderVO.setPayment(rs.getByte(11));
				orderVO.setMemo(rs.getString(12));
				orderVO.setShipDate(rs.getDate(13));
				orderVO.setTrackNo(rs.getString(14));
				orderVO.setStatus(rs.getByte(15));
				list.add(orderVO);
			}		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		return list;
	}
	
	//列出某會員的訂單
	@Override
	public List<OrderVO> listAllByMemId(Integer memId) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
			
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
				
			pstmt = con.prepareStatement(LISTALL_BY_MEM_STMT);
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrdId(rs.getInt(1));
				orderVO.setMemId(rs.getInt(2));
				orderVO.setStoreId(rs.getInt(3));
				orderVO.setStoreName(rs.getString(4));
				orderVO.setOrdDate(rs.getTimestamp(5));
				orderVO.setShipment(rs.getByte(6));
				orderVO.setOrdName(rs.getString(7));
				orderVO.setOrdMobile(rs.getString(8));
				orderVO.setOrdAddr(rs.getString(9));
				orderVO.setShipFee(rs.getInt(10));
				orderVO.setAmount(rs.getInt(11));
				orderVO.setPayment(rs.getByte(12));
				orderVO.setMemo(rs.getString(13));
				orderVO.setShipDate(rs.getDate(14));
				orderVO.setTrackNo(rs.getString(15));
				orderVO.setStatus(rs.getByte(16));
				list.add(orderVO);
			}		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		return list;
	}
	
	//依訂單狀態列出某店家的訂單
	@Override
	public List<OrderVO> listOrdsByStatus(Integer storeId, String status) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
	
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);	
			pstmt = con.prepareStatement(FIND_BY_STATUS_STMT);
			pstmt.setInt(1, storeId);
			pstmt.setString(2, status);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrdId(rs.getInt(1));
				orderVO.setMemId(rs.getInt(2));
				orderVO.setStoreId(rs.getInt(3));
				orderVO.setOrdDate(rs.getTimestamp(4));
				orderVO.setShipment(rs.getByte(5));
				orderVO.setShipFee(rs.getInt(6));
				orderVO.setAmount(rs.getInt(7));
				orderVO.setPayment(rs.getByte(8));
				orderVO.setMemo(rs.getString(9));
				orderVO.setStatus(rs.getByte(10));
				list.add(orderVO);
			}	
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		return list;
	}

	//依訂單編號查訂單明細
	@Override
	public List<OrdDetVO> getOrdDetsByOrdId(Integer ordId) {

		List<OrdDetVO> list = new ArrayList<OrdDetVO>();
		OrdDetVO ordDetVO = null;		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
			
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);			
			pstmt = con.prepareStatement(ORD_DETAILS_STMT);
			pstmt.setInt(1, ordId);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				ordDetVO = new OrdDetVO();
				ordDetVO.setProdId(rs.getInt(1));
				ordDetVO.setProdName(rs.getString(2));
				ordDetVO.setOrdQty(rs.getInt(3));
				ordDetVO.setOrdPrice(rs.getInt(4));
				list.add(ordDetVO);
			}	
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		return list;
	}
	
	// 方法 -- 扣掉庫存
	public void reduceStock(Integer prodId, Integer ordQty, Connection con) {
		PreparedStatement pstmt = null;
			
		try {
			pstmt = con.prepareStatement(REDUCE_STOCK);
			pstmt.setInt(1, ordQty);
			pstmt.setInt(2, ordQty);
			pstmt.setInt(3, prodId);
			pstmt.executeUpdate();	
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rolled back-由-order");
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
		}
	}
	
	// 方法 -- 加回庫存
	public void addStock(Integer prodId, Integer ordQty, Connection con) {
		PreparedStatement pstmt = null;
			
		try {
				pstmt = con.prepareStatement(ADD_STOCK);
				pstmt.setInt(1, ordQty);
				pstmt.setInt(2, ordQty);
				pstmt.setInt(3, prodId);
				pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
}
