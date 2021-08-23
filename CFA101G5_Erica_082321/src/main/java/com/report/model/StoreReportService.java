package com.report.model;

import java.util.List;

public class StoreReportService {

	public StoreReportDAO_interface dao;
	
	public StoreReportService() {
		dao = new StoreReportJDBCDAO();
	}
	
	public StoreReportVO addStoreReport(Integer memid,Integer storeid , String note) {
		System.out.println("addStoreReport00000000");
		StoreReportVO storeReportVO = new StoreReportVO();
		

		storeReportVO.setMemid(memid);
		storeReportVO.setStoreid(storeid);
		storeReportVO.setNote(note);
		
		dao.insert(storeReportVO);
		return storeReportVO;
	}
	
	public StoreReportVO updateStoreReport(Byte result,String note,Integer reportno) {
		
		StoreReportVO storeReportVO = new StoreReportVO();

		storeReportVO.setResult(result);
		storeReportVO.setNote(note);
		storeReportVO.setReportno(reportno);
		
		dao.update(storeReportVO);
		return storeReportVO;
	}
	

	public void deleteStoreReport(Integer reportno) {
	dao.delete(reportno);
	}
		
	public List<StoreReportVO> getAll() {
		return dao.getAll();
	}

	public  StoreReportVO getOneStoreReport(Integer reportno) {
		return dao.findByPK(reportno);
	}
	public List<StoreReportVO> getStoreReportByStoreId(Integer storeId) {
		return dao.getStoreReportByStoreId(storeId);
		}
	}

