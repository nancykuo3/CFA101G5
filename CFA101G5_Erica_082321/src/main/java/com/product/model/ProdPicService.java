package com.product.model;

import java.util.List;

public class ProdPicService {
	private ProdPicDAO_interface dao;

	public ProdPicService() {
		dao = new ProdPicDAO();
	}

	public ProdPicVO addProdPic(Integer prodId, byte[] prodPic) {

		ProdPicVO picVO = new ProdPicVO();

		picVO.setProdId(prodId);
		picVO.setPic(prodPic);
		dao.insert(picVO);

		return picVO;
	}

	public ProdPicVO updateProdPic(Integer picId, Integer prodId, byte[] prodPic) {

		ProdPicVO picVO = new ProdPicVO();

		picVO.setProdId(prodId);
		picVO.setPic(prodPic);
		picVO.setPicId(picId);
		dao.update(picVO);

		return picVO;
	}

	public void deleteProdPic(Integer prodid) {
		dao.delete(prodid);
	}

	public List<ProdPicVO> getAll() {
		return dao.getAll();
	}

	public ProdPicVO getOneProdPic(Integer prodId) {
		return dao.findByPrimaryKey(prodId);
	}

	public List<ProdPicVO> getByProdId(Integer prodid) {
		return dao.findByProdid(prodid);
	}

}
