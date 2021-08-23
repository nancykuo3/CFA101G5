package com.memberReport.model;

import java.util.*;

public interface MemReportDAO_interface {
	public void insert(MemReportVO memReportVO);

	public void update(MemReportVO memReportVO);

	public void delete(Integer reportno);

	public MemReportVO findByPrimaryKey(Integer reportno);

	public List<MemReportVO> getAll();

}
