package com.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdDetDAO implements OrdDetDAO_interface {
	//新增訂單明細
	private static final String INSERT_STMT = 
			"insert into ORDER_DETAILS (ORDER_ID, PROD_ID, ORDER_QTY, ORDER_UNIT_PRICE) "
					+ "values (?, ?, ?, ?)";
	
	//新增訂單明細
	@Override
	public void insert(OrdDetVO ordDetVO, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, ordDetVO.getOrdId());
			pstmt.setInt(2, ordDetVO.getProdId());
			pstmt.setInt(3, ordDetVO.getOrdQty());
			pstmt.setInt(4, ordDetVO.getOrdPrice());
		
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rolled back-由-order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
						+excep.getMessage());
				}
			}
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
		}
	}
}
