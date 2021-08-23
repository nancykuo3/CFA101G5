package com.product.model;

import java.util.List;

public interface ProdPicDAO_interface {
	public void insert(ProdPicVO prodPicVO);

	public void update(ProdPicVO prodPicVO);

	public void delete(Integer prodid);

	public ProdPicVO findByPrimaryKey(Integer picId);

	public List<ProdPicVO> getAll();

	// 同時新增商品圖片與商品資料
	public void insertWithProduct(ProdPicVO picVO, java.sql.Connection con);
	
	public List<ProdPicVO> findByProdid(Integer prodid);
}
