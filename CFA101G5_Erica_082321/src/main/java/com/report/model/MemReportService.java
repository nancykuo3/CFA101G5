package com.report.model;

import java.util.List;



public class MemReportService {
public MemReportDAO_interface dao;
	
	public MemReportService() {
		dao = new MemReportJDBCDAO();
	}
	
	public MemReportVO addMemReport(Integer memid,Integer memidReported, String content) {
		
		MemReportVO memReportVO = new MemReportVO();
		

		memReportVO.setMemid(memid);
		memReportVO.setMemidReported(memidReported);
		memReportVO.setContent(content);


		dao.insert(memReportVO);

		return memReportVO;
	}
	
	public MemReportVO updateMemReport(Byte result,String note , Integer reportno) {
		
		MemReportVO memReportVO = new MemReportVO();

		memReportVO.setResult(result);
		memReportVO.setNote(note);
		memReportVO.setReportno(reportno);
		dao.update(memReportVO);
		
		return memReportVO;
	}
	

	public void deleteMemReport(Integer reportno) {
	dao.delete(reportno);
	}
		
	public List<MemReportVO> getAll() {
		return dao.getAll();
	}

	public  MemReportVO getOneMemReport(Integer reportno) {
		return dao.findByPK(reportno);
	}
	
	public List<MemReportVO> getMemReportByMemId(Integer memId) {
	return dao.getMemReportByMemId(memId);
	}
}

