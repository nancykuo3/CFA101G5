package com.productInfo.model;

import java.util.*;

import com.product.model.ProductVO;
import com.productType.model.TypeVO;

public interface ProdInfoDAO_interface {
	public void insert(ProdInfoVO prodInfoVO);

	public void update(ProdInfoVO prodInfoVO);

//	public void delete(String isbn);

	public ProdInfoVO findByPrimaryKey(String isbn);

	public List<ProdInfoVO> getAll();

	// 查詢某ISBN於商城的所有商品(一對多) (往商城商品)
	public List<ProductVO> getProdsByISBN(String isbn);

	// 查詢某ISBN的所有類型(標籤)(一對多) (往商品類型明細至商品類型(一層子查詢))
	public Set<TypeVO> getTypesByISBN(String isbn);
	
	// composite query
	public List<ProdInfoVO> getAll(Map<String, String[]> map);
}
