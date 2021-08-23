package com.mem.model;

import java.sql.*;
import java.util.*;

import com.pic.model.PicVO;

public class MemJDBCDAO implements MemDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://34.81.38.113:3306/CFA101-G5?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "123456";

	private static final String GET_ALL_STMT = "SELECT * FROM MEM";
	private static final String GET_ONE_STMT = "SELECT * FROM MEM WHERE MEM_ID = ?";
	private static final String DELETE = "DELETE FROM MEM WHERE MEM_ID = ?";
	private static final String Get_ONEMEM = "SELECT * FROM MEM wHERE MEM_ACC=? and MEM_PWD=?";

	private static final String INSERT_STMT = "INSERT INTO MEM (MEM_ACC,MEM_PWD,ACC_STATUS,MEM_NAME,MEM_GENDER,MEM_EMAIL,MEM_MOBILE,MEM_CITY,MEM_DIST,MEM_ADDR,MEM_REG_DATE,MEM_PIC) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ? )";

	@Override
	public MemVO insert(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, memVO.getMemAcc());
			pstmt.setString(2, memVO.getMemPwd());
			pstmt.setByte(3, (byte) 0);
			pstmt.setString(4, memVO.getMemName());
			pstmt.setString(5, memVO.getMemGender());
			pstmt.setString(6, memVO.getMemEmail());
			pstmt.setString(7, memVO.getMemMobile());
			pstmt.setString(8, memVO.getMemCity());
			pstmt.setString(9, memVO.getMemDist());
			pstmt.setString(10, memVO.getMemAddr());
			pstmt.setBytes(11, memVO.getMemPic());

			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					memVO.setMemId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
			return memVO;
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

	private static final String UPDATE = "UPDATE MEM SET MEM_PWD=?,  MEM_NAME=?, MEM_GENDER=?, MEM_EMAIL=?, "
			+ "MEM_MOBILE=?, MEM_ADDR=?, MEM_PIC=? WHERE MEM_ID=?";

	@Override
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		System.out.println("dao.update= " + memVO);
		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMemPwd());
			pstmt.setString(2, memVO.getMemName());
			pstmt.setString(3, memVO.getMemGender());
			pstmt.setString(4, memVO.getMemEmail());
			pstmt.setString(5, memVO.getMemMobile());
			pstmt.setString(6, memVO.getMemAddr());
			pstmt.setBytes(7, memVO.getMemPic());
			pstmt.setInt(8, memVO.getMemId());

			System.out.println("dao.pstmt= " + pstmt);
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

	private static final String UPDATE_FOR_STATUS = "UPDATE MEM SET ACC_STATUS=? WHERE MEM_ID=?";

	@Override
	public void update_for_status(int memId, Byte status) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		System.out.println(memId);
		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_FOR_STATUS);

			pstmt.setByte(1, status);
			pstmt.setInt(2, memId);

			pstmt.executeUpdate();

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
	public void delete(Integer memId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memId);
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
	public MemVO findByPK(Integer memid) {
		// TODO Auto-generated method stub
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemGender(rs.getString("MEM_GENDER"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemMobile(rs.getString("MEM_MOBILE"));
				memVO.setMemCity(rs.getString("MEM_CITY"));
				memVO.setMemDist(rs.getString("MEM_DIST"));
				memVO.setMemAddr(rs.getString("MEM_ADDR"));
				memVO.setMemRegDate(rs.getString("MEM_REG_DATE"));
				memVO.setMemPic(rs.getBytes("MEM_PIC"));
				memVO.setMemRatingScore(rs.getInt("MEM_RATING_SCORE"));
				memVO.setMemRatingCount(rs.getInt("MEM_RATING_COUNT"));
				memVO.setMemReportCount(rs.getByte("MEM_REPORT_COUNT"));
				memVO.setMemPicSrc("/images?tableName=MEM&queryId=" + memVO.getMemId());

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

		return memVO;

	}

	@Override
	public List<MemVO> getAll() {
		// TODO Auto-generated method stub
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects

				memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemGender(rs.getString("MEM_GENDER"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemMobile(rs.getString("MEM_MOBILE"));
				memVO.setMemCity(rs.getString("MEM_CITY"));
				memVO.setMemDist(rs.getString("MEM_DIST"));
				memVO.setMemAddr(rs.getString("MEM_ADDR"));
				memVO.setMemRegDate(rs.getString("MEM_REG_DATE"));
				memVO.setMemPic(rs.getBytes("MEM_PIC"));
				memVO.setMemRatingScore(rs.getInt("MEM_RATING_SCORE"));
				memVO.setMemReportCount(rs.getByte("MEM_REPORT_COUNT"));
				memVO.setMemPicSrc("/images?tableName=MEM&queryId=" + memVO.getMemId());
				list.add(memVO); // Store the row in the list
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
	public MemVO getmem(String memAcc, String memPwd) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(Get_ONEMEM);

			pstmt.setString(1, memAcc);
			pstmt.setString(2, memPwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemGender(rs.getString("MEM_GENDER"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemMobile(rs.getString("MEM_MOBILE"));
				memVO.setMemCity(rs.getString("MEM_CITY"));
				memVO.setMemDist(rs.getString("MEM_DIST"));
				memVO.setMemAddr(rs.getString("MEM_ADDR"));
				memVO.setMemRegDate(rs.getString("MEM_REG_DATE"));
				memVO.setMemPic(rs.getBytes("MEM_PIC"));
				memVO.setMemRatingScore(rs.getInt("MEM_RATING_SCORE"));
				memVO.setMemRatingCount(rs.getInt("MEM_RATING_COUNT"));
				memVO.setMemReportCount(rs.getByte("MEM_REPORT_COUNT"));
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
		return memVO;
	}

	@Override
	public MemVO getOneUpdate(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement("UPDATE mem SET MemPic = ? WHERE MemId = ? ");

			pstmt.setBytes(1, memVO.getMemPic());
			pstmt.setInt(2, memVO.getMemId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

		return memVO;

	}

	public static void main(String[] args) {

		MemJDBCDAO dao = new MemJDBCDAO();

		// 查詢帳號密碼有無此資料
		MemVO memVO5 = dao.getmem("Zeus101", "kinG102");
		System.out.println("姓名" + memVO5.getMemName());

		// 新增

//	MemVO memVO1 = new MemVO();
//	memVO1.setMemAcc("Juno205");
//	memVO1.setMemPwd("cROwn208");
//	memVO1.setAccStatus((byte)1);
//	memVO1.setMemName("茱諾");
//	memVO1.setMemGender("2");
//	memVO1.setMemEmail("LOtus6@gmail.com");
//	memVO1.setMemMobile("0906110694");
//	memVO1.setMemCity("新北市");
//	memVO1.setMemDist("樹林區");
//	memVO1.setMemAddr("新北市樹林區長壽街15-9號");
//	memVO1.setMemRegDate("2021-02-16");
//	memVO1.setMemPic(new byte[(0)]);
//	memVO1.setMemRatingScore(5);
//	memVO1.setMemRatingCount(9);	
//	memVO1.setMemReportCount((byte)0);	
//	dao.insert(memVO1);

//	 修改

//		MemVO memVO2 = new MemVO();
//		memVO2.setMemAcc("Juno205");
//		memVO2.setMemPwd("cROwn208");
//		memVO2.setAccStatus((byte) 1);
//		memVO2.setMemName("茱諾");
//		memVO2.setMemGender("2");
//		memVO2.setMemEmail("LOtus6@gmail.com");
//		memVO2.setMemMobile("0000000000");
//		memVO2.setMemCity("新北市");
//		memVO2.setMemDist("樹林區");
//		memVO2.setMemAddr("新北市樹林區長壽街15-9號");
//		memVO2.setMemRegDate("2021-02-16");
//		memVO2.setMemPic(new byte[(0)]);
//		memVO2.setMemRatingScore(5);
//		memVO2.setMemRatingCount(9);
//		memVO2.setMemReportCount((byte) 0);
//		memVO2.setMemId(10008);
//		dao.update(memVO2);
//	

		// 刪除
//	dao.delete(10002);

		// 查詢ALL
//		List<MemVO> memVO3 = dao.getAll();
//		for (MemVO memVO : memVO3) {
//			System.out.println(memVO.getMemAcc());
//			System.out.println(memVO.getMemCity());
//			System.out.println(memVO.getMemDist());
//			System.out.println(memVO.getMemEmail());
//			System.out.println(memVO.getMemGender());
//			System.out.println(memVO.getMemMobile());
//			System.out.println(memVO.getMemName());
//			System.out.println(memVO.getMemPwd());
//			System.out.println(memVO.getMemRegDate());
//			System.out.println(memVO.getAccStatus());
//			System.out.println(memVO.getMemId());
//			System.out.println(memVO.getMemPic());
//			System.out.println(memVO.getMemRatingCount());
//			System.out.println(memVO.getMemRatingScore());
//			System.out.println(memVO.getMemReportCount());
//			System.out.println(memVO.getMemAddr());
//			System.out.println("-----------------------");
//		}

		// 單筆查詢
//	MemVO memVO4 = dao.findByPK(10003);
//	System.out.println("會員編號:"+memVO4.getMemId());
//	System.out.println("帳號:"+memVO4.getMemAcc());
//	System.out.println("密碼:"+memVO4.getMemPwd());
//	System.out.println("帳號狀態:"+memVO4.getAccStatus());
//	System.out.println("姓名:"+memVO4.getMemName());
//	System.out.println("性別:"+memVO4.getMemGender());
//	System.out.println("信箱:"+memVO4.getMemEmail());
//	System.out.println("手機:"+memVO4.getMemMobile());
//	System.out.println("地址_縣市:"+memVO4.getMemCity());
//	System.out.println("地址_區域:"+memVO4.getMemDist());
//	System.out.println("地址:"+memVO4.getMemAddr());
//	System.out.println("加入時間:"+memVO4.getMemRegDate());
//	System.out.println("會員照片:"+memVO4.getMemPic());
//	System.out.println("總分數:"+memVO4.getMemRatingScore());
//	System.out.println("總評價數:"+memVO4.getMemRatingCount());
//	System.out.println("被檢舉記點:"+memVO4.getMemReportCount());

	}

	@Override
	public List<PicVO> getPicFromOneMem(Integer memId) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PicVO PicVO = null;
		List<PicVO> PicVOList = new ArrayList<PicVO>();

		try {
			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement("SELECT * FROM MEM_PIC WHERE MEM_ID = ? ");

			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();

			// while迴圈跑查詢出來的資料
			while (rs.next()) {
				PicVO = new PicVO();

				// 先把資料塞到PicVO
				PicVO.setPicId(rs.getString("PicId"));

				// 再把資料塞到PicVOList
				PicVOList.add(PicVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

		return PicVOList;
	}

	private static final String UPDATE_NO_PIC = "UPDATE MEM SET MEM_PWD=?,  MEM_NAME=?, MEM_GENDER=?, MEM_EMAIL=?, MEM_MOBILE=?, MEM_ADDR=? WHERE MEM_ID=?";

	@Override
	public void updatenopic(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		System.out.println("dao.update= " + memVO);
		try {

			Class.forName(util.Util.DRIVER);
			con = DriverManager.getConnection(util.Util.URL, util.Util.USER, util.Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_NO_PIC);

			pstmt.setString(1, memVO.getMemPwd());
			pstmt.setString(2, memVO.getMemName());
			pstmt.setString(3, memVO.getMemGender());
			pstmt.setString(4, memVO.getMemEmail());
			pstmt.setString(5, memVO.getMemMobile());
			pstmt.setString(6, memVO.getMemAddr());
			pstmt.setInt(7, memVO.getMemId());

			System.out.println("dao.pstmt= " + pstmt);
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

}
