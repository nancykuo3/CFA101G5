package com.adminfunclist.model;

import java.util.*;

public interface AdminFuncListDAO_interface {

	public void insert(AdminFuncListVO adminFuncListVO);

	public void delete(Integer funcid);

	public AdminFuncListVO findByPK(Integer adminid, Integer funcid);
	
	// 列出該管理員的所有權限
//	public List<AdminFuncListVO> findFuncsByAdminid(Integer adminid);

	public List<AdminFuncListVO> getAll();

}
