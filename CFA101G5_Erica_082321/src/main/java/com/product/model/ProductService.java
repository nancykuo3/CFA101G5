package com.product.model;

import java.util.*;

public class ProductService {
	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	// 新增商品 (前台)
	public ProductVO addProduct(String isbn, Integer prodid, Integer storeId, Byte status, Integer price,
			Integer prodQty, String intro, byte[] firstPic) {

		ProductVO productVO = new ProductVO();

		productVO.setIsbn(isbn);
		productVO.setStoreId(storeId);
		productVO.setStatus(status);
		productVO.setPrice(price);
		productVO.setProdQty(prodQty);
		productVO.setIntro(intro);

		long miliseconds = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(miliseconds);
		productVO.setRegDate(date);
		productVO.setSalesFig(0);
		productVO.setFirstPic(firstPic);
		dao.insert(productVO);

		return productVO;
	}

	// 修改商品資訊(前台)
	public ProductVO updateProduct(Integer prodId, Integer price, Integer prodQty, String intro) {
		ProductVO productVO = new ProductVO();

		productVO.setPrice(price);
		productVO.setProdQty(prodQty);
		productVO.setIntro(intro);
		productVO.setProdId(prodId);
		dao.update(productVO);

		return productVO;
	}

	// 修改商品狀態(前/後台)
	public ProductVO updateStatus(Integer prodId, Byte status) {
		ProductVO productVO = new ProductVO();

		productVO.setProdId(prodId);
		productVO.setStatus(status);
		dao.updateStatus(productVO);

		return productVO;
	}

	public ProductVO updateFirstPic(byte[] firstPic, Integer prodid) {
		ProductVO productVO = new ProductVO();

		productVO.setFirstPic(firstPic);
		productVO.setProdId(prodid);
		dao.updateFirstPic(productVO);

		return productVO;
	}

	// 用店家ID取得他的所有商品
	public List<ProductVO> findByStoreid(Integer storeid) {
		return dao.findByStoreid(storeid);
	}

	// 以店家為基準，以商品狀態列出符合商品 (前台)
	public List<ProductVO> findByStoreid_and_Status(Integer storeid, Byte status) {
		return dao.findByStoreid_and_Status(storeid, status);
	}

	// 用商品狀態取得符合的商品 (後台)
	public List<ProductVO> findByStatus(Byte status) {
		return dao.findByStatus(status);
	}

	// 取得所有商品(後台)
	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	// 找單一商品
	public ProductVO getOneProduct(Integer prodId) {
		return dao.findByPrimaryKey(prodId);
	}

	// 用商品流水號取得圖片 (一對多)
	public Set<ProdPicVO> getPicsByProdid(Integer prodId) {
		return dao.getPicsByProdid(prodId);
	}

	public Integer insertWithPics(String isbn, Integer storeId, Byte status, Integer price,
			Integer prodQty, String intro, byte[] firstPic, List<ProdPicVO> list) {
		ProductVO productVO = new ProductVO();

		productVO.setIsbn(isbn);
		productVO.setStoreId(storeId);
		productVO.setStatus(status);
		productVO.setPrice(price);
		productVO.setProdQty(prodQty);
		productVO.setIntro(intro);

		long miliseconds = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(miliseconds);
		productVO.setRegDate(date);
		productVO.setSalesFig(0);
		productVO.setFirstPic(firstPic);

		return dao.insertWithPics(productVO, list);

	}
}
