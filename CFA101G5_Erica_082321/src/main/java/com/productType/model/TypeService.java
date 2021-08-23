package com.productType.model;

import java.util.*;

import com.productInfo.model.ProdInfoVO;

public class TypeService {

	private TypeDAO_interface dao;

	public TypeService() {
		dao = new TypeDAO();
	}

	public TypeVO addType(String typeName, String typeClass) {
		TypeVO typeVO = new TypeVO();

		typeVO.setTypeName(typeName);
		typeVO.setTypeClass(typeClass);

		dao.insert(typeVO);

		return typeVO;
	}

	public TypeVO updateType(String typeName, String typeClass, Integer typeid) {
		TypeVO typeVO = new TypeVO();

		typeVO.setTypeName(typeName);
		typeVO.setTypeClass(typeClass);
		typeVO.setTypeid(typeid);

		dao.update(typeVO);

		return typeVO;
	}

	public List<TypeVO> getAll() {
		return dao.getAll();
	}

	// 取得不重複標籤
	public List<TypeVO> getClassList() {
		return dao.getTypeClass();
	}

	public TypeVO getOneType(Integer typeid) {
		return dao.findByPrimaryKey(typeid);
	}

	// 列出該類型的所有產品 (一對多)
	public Set<ProdInfoVO> getProdInfoByTypeId(Integer typeid) {
		return dao.getProdInfoByTypeId(typeid);
	}

}
