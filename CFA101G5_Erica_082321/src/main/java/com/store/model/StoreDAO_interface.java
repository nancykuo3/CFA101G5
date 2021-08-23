package com.store.model;

import java.util.List;
import java.util.Map;

public interface StoreDAO_interface {
	public StoreVO insert(StoreVO storeVO);

	public void update(StoreVO storeVO);

	public void update_for_status(int storeId, Byte status);

	public void delete(Integer storeId);

	public StoreVO findByPrimaryKey(String storeAcc);

	public List<StoreVO> getAll();

	public StoreVO getstore(String storeAcc, String storePwd);

	StoreVO findByPrimaryKey(Integer storeId);
	
	
	// 預約新增方法
	public int findStoreCount(Map<String, String[]> condition);

	public List<StoreVO> findStoreByPage(int start, int rows, Map<String, String[]> condition);

}
