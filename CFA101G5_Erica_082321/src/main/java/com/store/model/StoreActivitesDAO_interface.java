package com.store.model;

import java.util.List;

public interface StoreActivitesDAO_interface {	
	public int insert(StoreActivitesVO storeActivitesVO);
	public int update(StoreActivitesVO storeActivitesVO);
	public int delete(Integer storeactNo);
	public StoreActivitesVO findByPrimaryKey(Integer storeactNo);
	public List<StoreActivitesVO> getAll();
}



