package com.mem.model;

import java.util.*;

public class MemManagementService {

	private MemDAO_interface dao;
	
	public MemManagementService() {
		dao = new MemJDBCDAO();
	}
	public MemVO addMem(String memAcc,String memPwd,
			String memName,String memGender,String memEmail,String memMobile,
			String memAddr,byte[] memPic) {
		System.out.println("addMem00000000");
		
		MemVO memVO = new MemVO();
		memVO.setMemAcc(memAcc);
		memVO.setMemPwd(memPwd);
		memVO.setMemName(memName);
		memVO.setMemGender(memGender);
		memVO.setMemEmail(memEmail);
		memVO.setMemMobile(memMobile);
		memVO.setMemAddr(memAddr);
		memVO.setMemPic(memPic);

		memVO = dao.insert(memVO);

		return memVO;
	}
		
	public void updateMem(MemVO memVO) {
		dao.update(memVO);
	}
	public void updateMem(int memId , Byte status) {
		dao.update_for_status(memId, status);
	}

	public void deleteMem(Integer memId) {
		dao.delete(memId);
	}

	public MemVO getOneMem(Integer memId) {
		return dao.findByPK(memId);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public MemVO getmem(String memAcc,String memPwd) {
		return dao.getmem(memAcc, memPwd);

	}
	public MemVO getOneMemUpdate(Integer memId, String memName, byte[] memPic) {

		MemVO memVO = new MemVO();
		memVO.setMemId(memId);
		memVO.setMemName(memName);
		memVO.setMemPic(memPic);

		dao.getOneUpdate(memVO);

		return memVO;

	}


	}
