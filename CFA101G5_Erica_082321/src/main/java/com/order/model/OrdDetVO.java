package com.order.model;

public class OrdDetVO implements java.io.Serializable{
	private Integer ordId;
	private Integer prodId;
	private String prodName;
	private Integer ordQty;
	private Integer ordPrice;
	
	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(Integer ordQty) {
		this.ordQty = ordQty;
	}
	public Integer getOrdPrice() {
		return ordPrice;
	}
	public void setOrdPrice(Integer ordPrice) {
		this.ordPrice = ordPrice;
	}
}
