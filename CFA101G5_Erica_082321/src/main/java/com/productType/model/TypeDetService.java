package com.productType.model;

import java.util.List;

public class TypeDetService {

	private TypeDetDAO_interface dao;

	public TypeDetService() {
		dao = new TypeDetDAO();
	}

	public TypeDetVO addTypeDet(Integer typeid, String isbn) {

		TypeDetVO typeDetVO = new TypeDetVO();

		typeDetVO.setTypeid(typeid);
		typeDetVO.setIsbn(isbn);

		dao.insert(typeDetVO);

		return typeDetVO;
	}

	public void deleteOneDet(Integer typeid, String isbn) {
		dao.delete(typeid, isbn);
	}

//	刪除一個產品的所有類別標籤
	public void deleteDetByISBN(String isbn) {
		dao.deleteAll(isbn);
	}

	public List<TypeDetVO> getAll() {
		return dao.getAll();
	}

	public TypeDetVO getOneDet(Integer typeid, String isbn) {
		return dao.finaByPrimaryKey(typeid, isbn);
	}
}
