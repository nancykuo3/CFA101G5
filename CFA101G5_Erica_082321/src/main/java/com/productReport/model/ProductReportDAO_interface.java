package com.productReport.model;

import java.util.*;

public interface ProductReportDAO_interface {
	public void insert(ProductReportVO productReportVO);

	public void update(ProductReportVO productReportVO);

	public void delete(Integer reportno);

	public ProductReportVO findByPrimaryKey(Integer reportno);

	// 列出全部的商品檢舉
	public List<ProductReportVO> getAll();

	// 該會員的檢舉
	public Set<ProductReportVO> getReportsByMemid(Integer memid);

	// 用狀態取得檢舉報告
	public Set<ProductReportVO> getReportsByStatus(Byte status);

}
