package com.function.model;

import java.util.*;

import com.admin.model.AdministratorVO;

public class FunctionService {

	private FunctionDAO_interface dao;

	public FunctionService() {
		dao = new FunctionDAO();
	}

	public FunctionVO addFunction(Integer funcid, String funcName, String funcdes) {
		FunctionVO functionVO = new FunctionVO();

		functionVO.setFuncid(funcid);
		functionVO.setFuncName(funcName);
		functionVO.setFuncdes(funcdes);

		dao.insert(functionVO);

		return functionVO;
	}

	public FunctionVO updateFunction(Integer funcid, String funcName, String funcdes) {
		FunctionVO functionVO = new FunctionVO();

		functionVO.setFuncid(funcid);
		functionVO.setFuncName(funcName);
		functionVO.setFuncdes(funcdes);

		dao.update(functionVO);

		return functionVO;
	}

	public void deleteFunction(Integer funcid) {
		dao.delete(funcid);
	}

	public List<FunctionVO> getAll() {
		return dao.getAll();
	}

	public FunctionVO getOneFunction(Integer funcid) {
		return dao.findByPrimaryKey(funcid);
	}

	public Set<AdministratorVO> getAdminsByFuncid(Integer funcid) {
		return dao.getAdminsByFuncid(funcid);
	}

}
