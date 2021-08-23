package com.productFavRecord.model;

import java.util.Set;

public class ProdFavRecordService {

	private ProdFavRecordDAO_interface dao;

	public ProdFavRecordService() {
		dao = new ProdFavRecordDAO();
	}

	public ProdFavRecordVO addProdFavRec(Integer memId, Integer prodId) {
		ProdFavRecordVO recVO = new ProdFavRecordVO();

		recVO.setMemId(memId);
		recVO.setProdId(prodId);
		dao.insert(recVO);

		return recVO;
	}

	public void deleteProdFavRec(Integer memId, Integer prodId) {
		dao.delete(memId, prodId);
	}

	public ProdFavRecordVO getOneProdFavRec(Integer memId, Integer prodId) {
		return dao.findByPrimaryKey(memId, prodId);
	}

	public Set<ProdFavRecordVO> getRecsByMemId(Integer memId) {
		return dao.getRecsBymemId(memId);
	}
}
