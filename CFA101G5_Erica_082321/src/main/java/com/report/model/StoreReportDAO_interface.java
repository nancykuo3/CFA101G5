package com.report.model;

import java.util.*;

public interface StoreReportDAO_interface {
	public void insert(StoreReportVO storeReportVO);

	public void update(StoreReportVO storeReportVO);

	public void delete(Integer reportno);

	public StoreReportVO findByPK(Integer reportno);

	public List<StoreReportVO> getAll();

	public List<StoreReportVO> getStoreReportByStoreId(Integer storeId);
}
