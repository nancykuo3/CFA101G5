package com.product.model;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

public interface ProductDAO_interface {

	// 新增商品 (前台)
	public void insert(ProductVO productVO);

	// 修改 商品價格、商品數量、商品簡介(前/後台)
	public void update(ProductVO productVO);

	// 修改商品首圖
	public void updateFirstPic(ProductVO productVO);
	
	// 修改商品狀態 (前/後台)
	public void updateStatus(ProductVO productVO);

	// 找單一商品
	public ProductVO findByPrimaryKey(Integer prodId);

	// 用店家ID取得他的所有商品
	public List<ProductVO> findByStoreid(Integer storeid);

	// 以店家為基準，以商品狀態列出符合商品 (前台)
	public List<ProductVO> findByStoreid_and_Status(Integer storeid, Byte status);

	// 用商品狀態取得符合的商品 (後台)
	public List<ProductVO> findByStatus(Byte status);

	// 取得所有商品(後台)
	public List<ProductVO> getAll();

	// 用商品流水號取得圖片 (一對多)
	public Set<ProdPicVO> getPicsByProdid(Integer prodId);

	// 新增商品時，同時新增商品圖片
	public Integer insertWithPics(ProductVO productVO, List<ProdPicVO> list);
	
//	public InputStream getFirstPic(Integer prodId) throws SQLException; 
	

}
