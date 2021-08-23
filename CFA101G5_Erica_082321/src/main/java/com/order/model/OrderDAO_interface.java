package com.order.model;

import java.util.List;


public interface OrderDAO_interface {
	
	//新增訂單 & 訂單明細 ＆ 扣除庫存
	public Integer insertWithOrdDet(OrderVO orderVO, List<OrdDetVO> list);
	
	//確認訂單
	public void confirmOrder(Integer status, Integer ordId);
	
	//取消訂單＆加回庫存量
	public void cancelOrder(Integer status, Integer ordId);
	
	//更新貨運追蹤號碼 並更改訂單狀態為已出貨
	public void updateTrackNo(Integer ordId, String trackNo);
	
	//用訂單編號查訂單
	public OrderVO findByPrimaryKey(Integer ordId);

	//列出某店家的所有訂單
	public List<OrderVO> listAllByStoreId(Integer storeId);
	
	//列出某會員的所有訂單
	public List<OrderVO> listAllByMemId(Integer memId);
	
	//依訂單狀態列出某店家的訂單
	public List<OrderVO> listOrdsByStatus(Integer storeId, String status);

	//依訂單編號查訂單明細
	public List<OrdDetVO> getOrdDetsByOrdId(Integer ordId);
	
}
