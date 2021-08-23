package com.function.model;

import java.util.*;

import com.admin.model.AdministratorVO;

public interface FunctionDAO_interface {
	public void insert(FunctionVO functionVO);

	public void update(FunctionVO functionVO);

	public void delete(Integer funcid);

	public FunctionVO findByPrimaryKey(Integer funcid);

	public List<FunctionVO> getAll();
	
	// 查詢該功能有多少管理員 (一對多，回傳 Set)
	public Set<AdministratorVO> getAdminsByFuncid(Integer funcid);

	// 新增權限同時，也一起新增管理員（應該不用，先擱置）
}
