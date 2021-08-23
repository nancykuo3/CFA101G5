package com.admin.model;

import java.util.*;

import com.function.model.FunctionVO;

public class AdministratorService {

	private AdministratorDAO_interface dao;

	public AdministratorService() {
		dao = new AdministratorDAO();
	}

	public AdministratorVO addAdmin(Integer adminid, String adminacc, String adminpwd, Byte adminStatus,
			String adminName) {
		AdministratorVO adminVO = new AdministratorVO();

		adminVO.setAdminid(adminid);
		adminVO.setAdminacc(adminacc);
		adminVO.setAdminpwd(adminpwd);
		adminVO.setAdminStatus(adminStatus);
		adminVO.setAdminName(adminName);

		dao.insert(adminVO);

		return adminVO;
	}

	public AdministratorVO updateAdmin(Integer adminid, String adminacc, String adminpwd, Byte adminStatus,
			String adminName) {
		AdministratorVO adminVO = new AdministratorVO();

		adminVO.setAdminid(adminid);
		adminVO.setAdminacc(adminacc);
		adminVO.setAdminpwd(adminpwd);
		adminVO.setAdminStatus(adminStatus);
		adminVO.setAdminName(adminName);

		dao.update(adminVO);

		return adminVO;
	}

	public AdministratorVO updateAdminStatus(Integer adminid, String adminacc, String adminpwd, Byte adminStatus,
			String adminName) {
		AdministratorVO adminVO = new AdministratorVO();

		adminVO.setAdminStatus(adminStatus);
		adminVO.setAdminid(adminid);

		dao.update(adminVO);

		return adminVO;
	}

	public void deleteAdmin(Integer adminid) {
		dao.delete(adminid);
	}

	public List<AdministratorVO> getAll() {
		return dao.getAll();
	}

	public AdministratorVO getOneAdmin(Integer adminid) {
		return dao.findByPrimaryKey(adminid);
	}

	public List<FunctionVO> getFuncsByAdminid(Integer adminid) {
		return dao.getFuncsByAdminid(adminid);
	}

	public AdministratorVO getAdmin(String admin_acc, String admin_pwd) {
		// TODO Auto-generated method stub
		return dao.getAdmin(admin_acc, admin_pwd);
	}
}
