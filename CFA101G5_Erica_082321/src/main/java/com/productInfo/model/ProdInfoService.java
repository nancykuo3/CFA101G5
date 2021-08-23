package com.productInfo.model;

import java.util.*;

import com.product.model.ProductVO;
import com.productType.model.TypeVO;

public class ProdInfoService {

	private ProdInfoDAO_interface dao;

	public ProdInfoService() {
		dao = new ProdInfoDAO();
	}

	public ProdInfoVO addProdInfo(String isbn, String prodName, String prodLang, String prodVer) {
		ProdInfoVO prodInfoVO = new ProdInfoVO();

		prodInfoVO.setIsbn(isbn);
		prodInfoVO.setProdName(prodName);
		prodInfoVO.setProdLang(prodLang);
		prodInfoVO.setProdVer(prodVer);

		dao.insert(prodInfoVO);

		return prodInfoVO;
	}

	public ProdInfoVO updateProdInfo(String prodName, String prodLang, String prodVer, String isbn) {
		ProdInfoVO prodInfoVO = new ProdInfoVO();

		prodInfoVO.setProdName(prodName);
		prodInfoVO.setProdLang(prodLang);
		prodInfoVO.setProdVer(prodVer);
		prodInfoVO.setIsbn(isbn);

		dao.update(prodInfoVO);

		return prodInfoVO;
	}

	public List<ProdInfoVO> getAll() {
		return dao.getAll();
	}

	public List<ProdInfoVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public ProdInfoVO getOneProdInfo(String isbn) {
		return dao.findByPrimaryKey(isbn);
	}

	// 查詢某ISBN於商城的所有商品(一對多) (往商城商品)
	public List<ProductVO> getProdsByISBN(String isbn) {
		return dao.getProdsByISBN(isbn);
	}

	// 查詢某ISBN的所有類型(標籤)(一對多) (往商品類型明細至商品類型(一層子查詢))
	public Set<TypeVO> getTypesByISBN(String isbn) {
		return dao.getTypesByISBN(isbn);
	}
}
