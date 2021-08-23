package com.favrecode.model;

import java.util.List;

public interface ProdFavRecordDAO_interface {
	public void insert(ProdFavRecordVO prodFavRecordVO);
	public void update(ProdFavRecordVO prodFavRecordVO);
	public void delete(Integer memId);
	public ProdFavRecordVO findByPrimaryKey(Integer memId);
	public List<ProdFavRecordVO> getAll();
}
