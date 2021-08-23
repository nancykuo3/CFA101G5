package com.order.model;

import java.sql.Connection;

public interface OrdDetDAO_interface {
	//新增訂單明細
	public void insert(OrdDetVO ordDetVO, Connection con);
	
}
