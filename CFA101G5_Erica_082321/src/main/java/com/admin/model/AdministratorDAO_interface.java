package com.admin.model;

import java.util.*;

import com.function.model.FunctionVO;

public interface AdministratorDAO_interface {

	public void insert(AdministratorVO administratorVO);

	public void update(AdministratorVO administratorVO);
	
	// 因為管理員的帳號狀態分為有效跟失效，所以寫了這個方法
	public void updateAdminStatus(AdministratorVO administratorVO);

	public void delete(Integer adminid);

	public AdministratorVO findByPrimaryKey(Integer adminid);

	public List<AdministratorVO> getAll();

	// 查詢某管理員的權限 (一對多，回傳 List)
	public List<FunctionVO> getFuncsByAdminid(Integer adminid);
	
	// 管理員登入(後台)
	public AdministratorVO getAdmin(String admin_acc,String admin_pwd);
}
