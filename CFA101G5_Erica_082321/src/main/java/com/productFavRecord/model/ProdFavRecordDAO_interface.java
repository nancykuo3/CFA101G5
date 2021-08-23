package com.productFavRecord.model;

import java.util.List;
import java.util.Set;

public interface ProdFavRecordDAO_interface {
	public void insert(ProdFavRecordVO prodFavRecordVO);

	public void delete(Integer memId, Integer prodId);

	// 單獨一筆商品收藏資料，不確定是否會用到
	public ProdFavRecordVO findByPrimaryKey(Integer memId, Integer prodId);

//	// 列出"所有"的商品收藏紀錄，應該不會用到
//	public List<ProdFavRecordVO> getAll();

	// 列出該會員收藏的所有商品
	public Set<ProdFavRecordVO> getRecsBymemId(Integer memId);
}
