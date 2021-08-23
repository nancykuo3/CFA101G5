package com.productReport.model;

import java.util.*;

public class ProductReportService {

	private ProductReportDAO_interface dao;

	public ProductReportService() {
		dao = new ProductReportDAO();
	}

	public ProductReportVO addProdRep(Integer memid, Integer prodid, java.sql.Timestamp reportTime, String content,
			Integer adminid, java.sql.Timestamp doneTime, Byte status, Byte result, String note) {

		ProductReportVO repVO = new ProductReportVO();

		repVO.setMemid(memid);
		repVO.setProductid(prodid);
		repVO.setReportTime(reportTime);
		repVO.setContent(content);
		repVO.setAdminid(adminid);
		repVO.setDoneTime(doneTime);
		repVO.setStatus(status);
		repVO.setResult(result);
		repVO.setNote(note);
		dao.insert(repVO);

		return repVO;
	}

	public ProductReportVO updateProdRep(Integer reportno, Integer memid, Integer prodid, java.sql.Timestamp reportTime,
			String content, Integer adminid, java.sql.Timestamp doneTime, Byte status, Byte result, String note) {
		ProductReportVO repVO = new ProductReportVO();

		repVO.setReportno(reportno);
		repVO.setMemid(memid);
		repVO.setProductid(prodid);
		repVO.setReportTime(reportTime);
		repVO.setContent(content);
		repVO.setAdminid(adminid);
		repVO.setDoneTime(doneTime);
		repVO.setStatus(status);
		repVO.setResult(result);
		repVO.setNote(note);
		dao.update(repVO);

		return repVO;
	}

	public void deleteProdRep(Integer reportno) {
		dao.delete(reportno);
	}

	public ProductReportVO getOneProdRep(Integer reportno) {
		return dao.findByPrimaryKey(reportno);
	}

	public List<ProductReportVO> getAll() {
		return dao.getAll();
	}

	public Set<ProductReportVO> getReportsByMemid(Integer memid) {
		return dao.getReportsByMemid(memid);
	}

	public Set<ProductReportVO> getReportsByStatus(Byte status) {
		return dao.getReportsByStatus(status);
	}
}
