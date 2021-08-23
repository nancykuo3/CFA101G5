package com.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.product.model.ProductVO;
import com.productType.model.TypeDAO;
import com.productType.model.TypeVO;
import com.store.model.StoreVO;

public class ShopService {

	ShopDAO dao = new ShopDAO();
	
	public byte[] getFirstPic(Integer prodId) {
		return dao.getFirstPic(prodId);
	}
	
	public byte[] showProdPic(Integer prodPicId) {
		return dao.showProdPic(prodPicId);
	}
	
	public List<ProductVO> getStoreProd(Integer storeId){
		return dao.getStoreProd(storeId);
	}
	
	public List<ProductVO> getProdByType(Integer typeId){
		return dao.getProdByType(typeId);
	}
	
	public ProductVO getProdInfo(Integer prodId) {
		return dao.getProdInfo(prodId);
	}
	
	public List<ProductVO> listTopProd(){
		return dao.listTopProd();
	}
	
	public List<ProductVO> latestProd(){
		return dao.latestProd();
	}
	
	public List<StoreVO> getTopThreeStore(){
		return dao.getTopThreeStore();
	}
	
	public Map<String, List<TypeVO>> listType(){
		Map<String, List<TypeVO>> map = new HashMap<String, List<TypeVO>>();
		TypeDAO t_dao = new TypeDAO();
		List<TypeVO> list = t_dao.getAll();
		List<TypeVO> typeVO_A = new ArrayList<TypeVO>();
		List<TypeVO> typeVO_B = new ArrayList<TypeVO>();
		List<TypeVO> typeVO_C = new ArrayList<TypeVO>();
		for (TypeVO typeVO: list) {
			if (typeVO.getTypeClass().equals("標籤")){
				typeVO_C.add(typeVO);
			} else if (typeVO.getTypeClass().equals("適合人數")){
				typeVO_B.add(typeVO);
			} else if (typeVO.getTypeClass().equals("適合對象")) {
				typeVO_A.add(typeVO);
			}
		}
		map.put("listA", typeVO_A);
		map.put("listB", typeVO_B);
		map.put("listC", typeVO_C);
		return map;
	}
	
	public List<TypeVO> getProdType(String isbn){
		return dao.getProdType(isbn);
	}
	
	public String getTypeName(Integer typeId) {
		return dao.getTypeName(typeId);
	}
	
	public ProductVO getProdDetail(Integer prodId) {
		return dao.getProdDetail(prodId);
	}
	
	public List<ProductVO> getFavProd(Integer memId){
		return dao.getFavProd(memId);
	}
	
	public List<ProductVO> getShipment(Integer storeId){
		return dao.getShipment(storeId);
	}
	
	public List<ProductVO> searchProd(String keyword){
		return dao.searchProd(keyword);
	}
	
	public List<Integer> getProdPics(Integer prodId){
		return dao.getProdPics(prodId);
	}
}
