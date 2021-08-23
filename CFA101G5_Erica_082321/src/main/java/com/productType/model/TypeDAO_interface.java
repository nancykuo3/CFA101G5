package com.productType.model;

import java.util.List;
import java.util.Set;

import com.productInfo.model.ProdInfoVO;

public interface TypeDAO_interface {
	public void insert(TypeVO typeVO);

	public void update(TypeVO typeVO);

//	// 不會有刪除
//	public void delete(Integer typeId);

	public TypeVO findByPrimaryKey(Integer typeid);

	public List<TypeVO> getAll();
	
	// 取得不重複標籤
	public List<TypeVO> getTypeClass();

	// 列出該類型的所有產品 (一對多) (往商品類型明細至商品詳細資料(一層子查詢))
	public Set<ProdInfoVO> getProdInfoByTypeId(Integer typeid);
}
