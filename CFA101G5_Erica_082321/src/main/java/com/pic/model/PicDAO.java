package com.pic.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemDAO_interface;
import com.mem.model.MemVO;


public class PicDAO implements MemDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/qq");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PicVO> getPicFromOneMem(Integer memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PicVO PicVO = null;
		List<PicVO> PicVOList = new ArrayList<PicVO>();

		try {

			con = ds.getConnection();
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

	@Override
	public MemVO insert(MemVO memVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update_for_status(int memId, Byte status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer memId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemVO findByPK(Integer memid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemVO getmem(String memAcc, String memPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemVO getOneUpdate(MemVO memVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatenopic(MemVO memVO) {
		// TODO Auto-generated method stub
		
	}

}
