package com.store.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Util;

public class StoreJDBCDAO implements StoreDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "123456";

	private static final String GET_ALL_STMT = "SELECT * FROM STORE";
	private static final String GET_ONE_STMT = "SELECT * FROM STORE WHERE STORE_ID = ?";
	private static final String DELETE = "DELETE FROM STORE WHERE STORE_ID = ?";
	private static final String Get_ONESTORE = "SELECT * FROM STORE wHERE STORE_ACC=? and STORE_PWD=?";

	private static final String INSERT_STMT = "INSERT INTO STORE (STORE_ACC,STORE_PWD,ACC_STATUS,STORE_NAME,STORE_GUI,STORE_PIC,STORE_TEL,"
			+ "STORE_FAX,STORE_ADD,STORE_POC,STORE_CON_PHONE,STORE_CON_ADD,STORE_EMAIL,BANK_ACCOUNT,CAPACITY,DAY_OFF,STORE_REG_DATE,OPENING_HOURS)"
			+ "VALUES ( ?, ?, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,now(),concat(repeat('0', ?), repeat('1' , ? - ?), repeat('0', 24 - ?)))";

	@Override
	public StoreVO insert(StoreVO storeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, storeVO.getStoreAcc());
			pstmt.setString(2, storeVO.getStorePwd());
			pstmt.setString(3, storeVO.getStoreName());
			pstmt.setInt(4, storeVO.getStoreGui());
			pstmt.setString(5, storeVO.getStorePic());
			pstmt.setString(6, storeVO.getStoreTel());
			pstmt.setString(7, storeVO.getStoreFax());
			pstmt.setString(8, storeVO.getStoreAdd());
			pstmt.setString(9, storeVO.getStorePoc());
			pstmt.setString(10, storeVO.getStoreConPhone());
			pstmt.setString(11, storeVO.getStoreConAdd());
			pstmt.setString(12, storeVO.getStoreEmail());
			pstmt.setString(13, storeVO.getBankAccount());
			pstmt.setInt(14, storeVO.getCapacity());
			pstmt.setByte(15, storeVO.getDayOff());
			pstmt.setInt(16, storeVO.getStartTime());
			pstmt.setInt(17, storeVO.getEndTime());
			pstmt.setInt(18, storeVO.getStartTime());
			pstmt.setInt(19, storeVO.getEndTime());

			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					storeVO.setStoreId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
			return storeVO;
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

	private static final String UPDATE = "UPDATE STORE SET STORE_PWD=?, STORE_NAME=?, STORE_GUI=?, STORE_PIC=?,STORE_TEL=?,STORE_FAX=?,STORE_ADD=?,STORE_POC=?,STORE_CON_PHONE=?,"
			+ "STORE_CON_ADD=?,STORE_EMAIL=?,BANK_ACCOUNT=?,CAPACITY=?,DAY_OFF=?,"
			// 15 16 17 18
			+ "OPENING_HOURS=concat(repeat('0', ?), repeat('1' , ? - ?), repeat('0', 24 - ?)) " + "WHERE STORE_ID=?";

	@Override
	public void update(StoreVO storeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, storeVO.getStorePwd());
			pstmt.setString(2, storeVO.getStoreName());
			pstmt.setInt(3, storeVO.getStoreGui());
			pstmt.setString(4, storeVO.getStorePic());
			pstmt.setString(5, storeVO.getStoreTel());
			pstmt.setString(6, storeVO.getStoreFax());
			pstmt.setString(7, storeVO.getStoreAdd());
			pstmt.setString(8, storeVO.getStorePoc());
			pstmt.setString(9, storeVO.getStoreConPhone());
			pstmt.setString(10, storeVO.getStoreConAdd());
			pstmt.setString(11, storeVO.getStoreEmail());
			pstmt.setString(12, storeVO.getBankAccount());
			pstmt.setInt(13, storeVO.getCapacity());
			pstmt.setByte(14, storeVO.getDayOff());
			pstmt.setInt(15, storeVO.getStartTime());
			pstmt.setInt(16, storeVO.getEndTime());
			pstmt.setInt(17, storeVO.getStartTime());
			pstmt.setInt(18, storeVO.getEndTime());
			pstmt.setInt(19, storeVO.getStoreId());

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

	private static final String UPDATE_FOR_STATUS = "UPDATE STORE SET ACC_STATUS=? WHERE STORE_ID=?";

	@Override
	public void update_for_status(int storeId, Byte status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		System.out.println(storeId);
		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_FOR_STATUS);

			pstmt.setByte(1, status);
			pstmt.setInt(2, storeId);

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
	public void delete(Integer storeId) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, storeId);
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
	public StoreVO findByPrimaryKey(Integer storeId) {
		// TODO Auto-generated method stub

		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, storeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				storeVO = new StoreVO();
				storeVO.setStoreId(rs.getInt("STORE_ID"));
				storeVO.setStoreAcc(rs.getString("STORE_ACC"));
				storeVO.setStorePwd(rs.getString("STORE_PWD"));
				storeVO.setAccStatus(rs.getByte("ACC_STATUS"));
				storeVO.setStoreName(rs.getString("STORE_NAME"));
				storeVO.setStoreGui(rs.getInt("STORE_GUI"));
				storeVO.setStorePic(rs.getString("STORE_PIC"));
				storeVO.setStoreTel(rs.getString("STORE_TEL"));
				storeVO.setStoreFax(rs.getString("STORE_FAX"));
				storeVO.setStoreAdd(rs.getString("STORE_ADD"));
				storeVO.setStorePoc(rs.getString("STORE_POC"));
				storeVO.setStoreConPhone(rs.getString("STORE_CON_PHONE"));
				storeVO.setStoreConAdd(rs.getString("STORE_CON_ADD"));
				storeVO.setStoreEmail(rs.getString("STORE_EMAIL"));
				storeVO.setStoreStatus(rs.getByte("STORE_STATUS"));
				storeVO.setStoreSettingDate(rs.getDate("STORE_SETTING_DATE"));
				storeVO.setStoreChangeDate(rs.getDate("STORE_CHANGE_DATE"));
				storeVO.setStoreCapital(rs.getInt("STORE_CAPITAL"));
				storeVO.setStoreRegDate(rs.getDate("STORE_REG_DATE"));
				storeVO.setBankAccount(rs.getString("BANK_ACCOUNT"));
				storeVO.setStoreShopRatingScore(rs.getInt("STORE_SHOP_RATING_SCORE"));
				storeVO.setStoreShopRatingCount(rs.getInt("STORE_SHOP_RATING_COUNT"));
				storeVO.setStoreRsvnRatingScore(rs.getInt("STORE_RSVN_RATING_SCORE"));
				storeVO.setStoreRsvnRatingCount(rs.getInt("STORE_RSVN_RATING_COUNT"));
				storeVO.setStoreReportCount(rs.getByte("STORE_REPORT_COUNT"));
				storeVO.setCapacity(rs.getInt("CAPACITY"));
				storeVO.setDayOff(rs.getByte("DAY_OFF"));
				storeVO.setOpeningHours(rs.getString("OPENING_HOURS"));
				System.out.println("OPENING_HOURS:" + rs.getString("OPENING_HOURS"));
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

		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		// TODO Auto-generated method stub
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// storeVO 也稱為 Domain objects

				storeVO = new StoreVO();
				storeVO.setStoreId(rs.getInt("STORE_ID"));
				storeVO.setStoreAcc(rs.getString("STORE_ACC"));
				storeVO.setStorePwd(rs.getString("STORE_PWD"));
				storeVO.setAccStatus(rs.getByte("ACC_STATUS"));
				storeVO.setStoreName(rs.getString("STORE_NAME"));
				storeVO.setStoreGui(rs.getInt("STORE_GUI"));
				storeVO.setStorePic(rs.getString("STORE_GUI"));
				storeVO.setStoreTel(rs.getString("STORE_TEL"));
				storeVO.setStoreFax(rs.getString("STORE_FAX"));
				storeVO.setStoreAdd(rs.getString("STORE_ADD"));
				storeVO.setStorePoc(rs.getString("STORE_POC"));
				storeVO.setStoreConPhone(rs.getString("STORE_CON_PHONE"));
				storeVO.setStoreConAdd(rs.getString("STORE_CON_ADD"));
				storeVO.setStoreEmail(rs.getString("STORE_EMAIL"));
				storeVO.setStoreSettingDate(rs.getDate("STORE_SETTING_DATE"));
				storeVO.setStoreChangeDate(rs.getDate("STORE_CHANGE_DATE"));
				storeVO.setStoreCapital(rs.getInt("STORE_CAPITAL"));
				storeVO.setStoreRegDate(rs.getDate("STORE_REG_DATE"));
				storeVO.setBankAccount(rs.getString("BANK_ACCOUNT"));
				storeVO.setStoreShopRatingScore(rs.getInt("STORE_SHOP_RATING_SCORE"));
				storeVO.setStoreShopRatingCount(rs.getInt("STORE_SHOP_RATING_COUNT"));
				storeVO.setStoreRsvnRatingScore(rs.getInt("STORE_RSVN_RATING_SCORE"));
				storeVO.setStoreRsvnRatingCount(rs.getInt("STORE_RSVN_RATING_COUNT"));
				storeVO.setStoreReportCount(rs.getByte("STORE_REPORT_COUNT"));
				storeVO.setStoreCapital(rs.getInt("CAPACITY"));
				storeVO.setDayOff(rs.getByte("DAY_OFF"));
				storeVO.setOpeningHours(rs.getString("OPENING_HOURS"));
				list.add(storeVO); // Store the row in the list
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
	public StoreVO getstore(String storeAcc, String storePwd) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(Get_ONESTORE);

			pstmt.setString(1, storeAcc);
			pstmt.setString(2, storePwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				storeVO = new StoreVO();
				storeVO.setStoreId(rs.getInt("STORE_ID"));
				storeVO.setStoreAcc(rs.getString("STORE_ACC"));
				storeVO.setStorePwd(rs.getString("STORE_PWD"));
				storeVO.setAccStatus(rs.getByte("ACC_STATUS"));
				storeVO.setStoreName(rs.getString("STORE_NAME"));
				storeVO.setStoreGui(rs.getInt("STORE_GUI"));
				storeVO.setStorePic(rs.getString("STORE_GUI"));
				storeVO.setStoreTel(rs.getString("STORE_TEL"));
				storeVO.setStoreFax(rs.getString("STORE_FAX"));
				storeVO.setStoreAdd(rs.getString("STORE_ADD"));
				storeVO.setStorePoc(rs.getString("STORE_POC"));
				storeVO.setStoreConPhone(rs.getString("STORE_CON_PHONE"));
				storeVO.setStoreConAdd(rs.getString("STORE_CON_ADD"));
				storeVO.setStoreEmail(rs.getString("STORE_EMAIL"));
				storeVO.setStoreSettingDate(rs.getDate("STORE_SETTING_DATE"));
				storeVO.setStoreChangeDate(rs.getDate("STORE_CHANGE_DATE"));
				storeVO.setStoreCapital(rs.getInt("STORE_CAPITAL"));
				storeVO.setStoreRegDate(rs.getDate("STORE_REG_DATE"));
				storeVO.setBankAccount(rs.getString("BANK_ACCOUNT"));
				storeVO.setStoreShopRatingScore(rs.getInt("STORE_SHOP_RATING_SCORE"));
				storeVO.setStoreShopRatingCount(rs.getInt("STORE_SHOP_RATING_COUNT"));
				storeVO.setStoreRsvnRatingScore(rs.getInt("STORE_RSVN_RATING_SCORE"));
				storeVO.setStoreRsvnRatingCount(rs.getInt("STORE_RSVN_RATING_COUNT"));
				storeVO.setStoreReportCount(rs.getByte("STORE_REPORT_COUNT"));
				storeVO.setStoreCapital(rs.getInt("CAPACITY"));
				storeVO.setDayOff(rs.getByte("DAY_OFF"));
				storeVO.setOpeningHours(rs.getString("OPENING_HOURS"));
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
		return storeVO;
	}

	public static void main(String[] args) {
		StoreJDBCDAO dao = new StoreJDBCDAO();

		// 查詢帳號密碼有無此資料
//			StoreVO storeVO5 = dao.getstore("GaiYa4202","Heyaie2024");
//			System.out.println("姓名"+storeVO5.getStoreName());

		// 新增會員資料
//			StoreVO storeVO1 = new StoreVO();
//			storeVO1.setStoreAcc("Auno305");
//			storeVO1.setStorePwd("DROwn308");
//			storeVO1.setAccStatus((byte)0);
//			storeVO1.setStoreName("茱諾");
//			storeVO1.setStoreGui(42653256);
//			storeVO1.setStorePic("茱小姐");
//			storeVO1.setStoreTel("032585225");
//			storeVO1.setStoreFax("032585225");
//			storeVO1.setStoreAdd("桃園市中壢區中平路59號");
//			storeVO1.setStorePoc("茱大姐");
//			storeVO1.setStoreConPhone("032585225");
//			storeVO1.setStoreConAdd("桃園市中壢區中平路59號");
//			storeVO1.setStoreEmail("SOtus5@gmail.com");
//			storeVO1.setStoreSettingDate("2021-02-16");
//			storeVO1.setStoreChangeDate("2021-11-16");
//			storeVO1.setStoreCapital(2000000);
//			storeVO1.setStoreRegDate("2021-03-16");
//			storeVO1.setStoreStatus((byte)0);
//			storeVO1.setBankAccount("09652445478962");
//			storeVO1.setStoreShopRatingScore(2);
//			storeVO1.setStoreShopRatingCount(300);
//			storeVO1.setStoreRsvnRatingScore(3);
//			storeVO1.setStoreRsvnRatingCount(250);
//			storeVO1.setStoreReportCount((byte)2);
//			storeVO1.setCapacity(20);
//			storeVO1.setDayOff((byte)1);
//			storeVO1.setOpeningHours("000000000111111111111110");
//			dao.insert(storeVO1);

		// 修改會員資料
//			StoreVO storeVO2 = new StoreVO();
//			storeVO2.setStoreAcc("Auno305");
//			storeVO2.setStorePwd("DROwn308");
//			storeVO2.setAccStatus((byte)0);
//			storeVO2.setStoreName("茱諾");
//			storeVO2.setStoreGui(42653256);
//			storeVO2.setStorePic("茱小姐");
//			storeVO2.setStoreTel("000085225");
//			storeVO2.setStoreFax("032585225");
//			storeVO2.setStoreAdd("桃園市中壢區中平路59號");
//			storeVO2.setStorePoc("茱大姐");
//			storeVO2.setStoreConPhone("032585225");
//			storeVO2.setStoreConAdd("桃園市中壢區中平路59號");
//			storeVO2.setStoreEmail("SOtus5@gmail.com");
//			storeVO2.setStoreSettingDate("2021-02-16");
//			storeVO2.setStoreChangeDate("2021-11-16");
//			storeVO2.setStoreCapital(2000000);
//			storeVO2.setStoreRegDate("2021-03-16");
//			storeVO2.setStoreStatus((byte)0);
//			storeVO2.setBankAccount("09652445478962");
//			storeVO2.setStoreShopRatingScore(2);
//			storeVO2.setStoreShopRatingCount(300);
//			storeVO2.setStoreRsvnRatingScore(3);
//			storeVO2.setStoreRsvnRatingCount(250);
//			storeVO2.setStoreReportCount((byte)2);
//			storeVO2.setCapacity(20);
//			storeVO2.setDayOff((byte)1);
//			storeVO2.setOpeningHours("000000000111111111111110");
//			storeVO2.setStoreId(30006);
//			dao.update(storeVO2);

		// 刪除會員資料
//			dao.delete(30006);

		// 查詢ALL
//			List<StoreVO> storeVO3 = dao.getAll();
//			for (StoreVO storeVO : storeVO3) {
//			System.out.println(storeVO.getStoreName());
//		}
//			
		// 單筆查詢
		StoreVO storeVO4 = dao.findByPrimaryKey(30003);
		System.out.println("會員編號:" + storeVO4.getStoreId());
		System.out.println("帳號:" + storeVO4.getStoreAcc());
		System.out.println("密碼:" + storeVO4.getStorePwd());
	}

	@Override
	public StoreVO findByPrimaryKey(String storeAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	// 以下新增
	@Override
	public int findStoreCount(Map<String, String[]> condition) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("count");
		String sql = "select count(*) from STORE where 1 = 1";
		StringBuilder sb = new StringBuilder(sql);
		// 遍歷map
		Set<String> keySet = condition.keySet();
		for (String key : keySet) {
			// 排除分頁條件參數
			if ("currentPage".equals(key) || "rows".equals(key) || "action".equals(key)) {
				continue;
			}
			// 獲取value
			String value = condition.get(key)[0];
			System.out.println(key + ":" + value);
			// 判斷value是否有值
			if ("ACC_STATUS".equals(key) && !"".equals(value)) {
				sb.append(" and " + key + " = ");
				sb.append(value);// 條件的值
				continue;
			}
			if ("STORE_NAME".equals(key) && value != null && !"".equals(value)) {
				// 有值
				sb.append(" and " + key + " like ");
				sb.append("\"%" + value + "%\"");// ?條件的值
			}
		}
		int totalCount = 0;
		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalCount = rs.getInt("count(*)");
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return totalCount;
	}

	@Override
	public List<StoreVO> findStoreByPage(int start, int rows, Map<String, String[]> condition) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from STORE where 1 = 1";
		StringBuilder sb = new StringBuilder(sql);
		// 遍歷map
		Set<String> keySet = condition.keySet();
		for (String key : keySet) {
			// 排除分頁條件參數
			if ("currentPage".equals(key) || "rows".equals(key) || "action".equals(key)) {
				continue;
			}
			// 獲取value
			String value = condition.get(key)[0];
			System.out.println(key + ":" + value);
			// 判斷value是否有值
			if ("ACC_STATUS".equals(key) && !"".equals(value)) {
				System.out.println(value);
				sb.append(" and " + key + " = ");
				sb.append(value);// 條件的值
				continue;
			}
			if ("STORE_NAME".equals(key) && value != null && !"".equals(value)) {
				// 有值
				sb.append(" and " + key + " like ");
				sb.append("\"%" + value + "%\"");// ?條件的值
				continue;
			}
			// 判斷value是否有值,字串
			if (value != null && !"".equals(value)) {
				// 有值
				sb.append(" and " + key + " like ");
				sb.append("\"%" + value + "%\"");// ?條件的值
			}
		}
		sb.append(" limit ?, ? ");
		StoreVO storeVO = null;
		List<StoreVO> list = new ArrayList<StoreVO>();
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, rows);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStoreId(rs.getInt("STORE_ID"));
				storeVO.setStoreAcc(rs.getString("STORE_ACC"));
				storeVO.setStorePwd(rs.getString("STORE_PWD"));
				storeVO.setAccStatus(rs.getByte("ACC_STATUS"));
				storeVO.setStoreName(rs.getString("STORE_NAME"));
				storeVO.setStoreGui(rs.getInt("STORE_GUI"));
				storeVO.setStorePic(rs.getString("STORE_GUI"));
				storeVO.setStoreTel(rs.getString("STORE_TEL"));
				storeVO.setStoreFax(rs.getString("STORE_FAX"));
				storeVO.setStoreAdd(rs.getString("STORE_ADD"));
				storeVO.setStorePoc(rs.getString("STORE_POC"));
				storeVO.setStoreConPhone(rs.getString("STORE_CON_PHONE"));
				storeVO.setStoreConAdd(rs.getString("STORE_CON_ADD"));
				storeVO.setStoreEmail(rs.getString("STORE_EMAIL"));
				storeVO.setStoreSettingDate(rs.getDate("STORE_SETTING_DATE"));
				storeVO.setStoreChangeDate(rs.getDate("STORE_CHANGE_DATE"));
				storeVO.setStoreCapital(rs.getInt("STORE_CAPITAL"));
				storeVO.setStoreRegDate(rs.getDate("STORE_REG_DATE"));
				storeVO.setStoreStatus(rs.getByte("STORE_STATUS"));
				storeVO.setBankAccount(rs.getString("BANK_ACCOUNT"));
				storeVO.setStoreShopRatingScore(rs.getInt("STORE_SHOP_RATING_SCORE"));
				storeVO.setStoreShopRatingCount(rs.getInt("STORE_SHOP_RATING_COUNT"));
				storeVO.setStoreRsvnRatingScore(rs.getInt("STORE_RSVN_RATING_SCORE"));
				storeVO.setStoreRsvnRatingCount(rs.getInt("STORE_RSVN_RATING_COUNT"));
				storeVO.setStoreReportCount(rs.getByte("STORE_REPORT_COUNT"));
				storeVO.setCapacity(rs.getInt("CAPACITY"));
				storeVO.setDayOff(rs.getByte("DAY_OFF"));
				storeVO.setOpeningHours(rs.getString("OPENING_HOURS"));
				list.add(storeVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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