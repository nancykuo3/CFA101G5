package com.adminfunclist.model;

import java.util.*;

public class AdminFuncListService {

	private AdminFuncListDAO_interface dao;

	public AdminFuncListService() {
		dao = new AdminFuncListDAO();
	}

	public AdminFuncListVO addAdminFuncList(Integer adminid, Integer funcid) {
		AdminFuncListVO adminFuncList = new AdminFuncListVO();

		adminFuncList.setAdminid(adminid);
		adminFuncList.setFuncid(funcid);

		dao.insert(adminFuncList);

		return adminFuncList;

	}
	
	public void deleteAdminFuncList(Integer adminid) {
		dao.delete(adminid);
	}

	public List<AdminFuncListVO> getAll() {
		return dao.getAll();
	}

	public AdminFuncListVO getOneAdminFuncList(Integer adminid, Integer funcid) {
		return dao.findByPK(funcid, adminid);
	}

}
