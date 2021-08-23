package com.productType.model;

import java.util.List;

public interface TypeDetDAO_interface {

	public void insert(TypeDetVO typeDetVO);

//	刪除一個產品的類別標籤
	public void delete(Integer typeid, String isbn);
	
//	刪除一個產品的所有類別標籤
	public void deleteAll(String isbn);

	public TypeDetVO finaByPrimaryKey(Integer typeid, String isbn);

	public List<TypeDetVO> getAll();

}
