package com.order.model;

import java.util.List;

public class OrderService {
	
	private OrderDAO_interface dao;
	public OrderService() {
		dao = new OrderDAO();
	}
	//新增訂單 & 訂單明細 ＆ 扣除庫存
	public Integer insertWithOrdDet(OrderVO orderVO, List<OrdDetVO> list) {
		return dao.insertWithOrdDet(orderVO, list);
	}
	//確認訂單
	public void confirmOrder(Integer status, Integer ordId) {
		dao.confirmOrder(status, ordId);
	}
	//取消訂單
	public void cancelOrder(Integer status, Integer ordId) {
		dao.cancelOrder(status, ordId);
	}
	//更新貨運追蹤號碼 及 更新訂單狀態為已出貨
	public void updateStatus(Integer ordId, String trackNo) {
		dao.updateTrackNo(ordId, trackNo);
	}
	//用訂單編號查詢訂單
	public OrderVO findByPrimaryKey(Integer ordId) {
		return dao.findByPrimaryKey(ordId);
	}
	//列出某店家的所有訂單
	public List<OrderVO> listAllByStoreId(Integer storeId){
		return dao.listAllByStoreId(storeId);
	} 
	//列出某會員的所有訂單
	public List<OrderVO> listAllByMemId(Integer memId){
		return dao.listAllByMemId(memId);
	} 
	//依訂單狀態列出某店家的訂單
	public List<OrderVO> listOrdsByStatus(Integer storeId, String status) {
		return dao.listOrdsByStatus(storeId, status);
	}
	//用訂單編號查詢訂單明細
	public List<OrdDetVO> getOrdDetsByOrdId(Integer ordId){
		return dao.getOrdDetsByOrdId(ordId);
	}
}
