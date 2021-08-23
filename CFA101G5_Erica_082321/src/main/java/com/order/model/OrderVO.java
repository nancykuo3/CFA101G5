package com.order.model;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderVO implements java.io.Serializable{
	private Integer ordId;
	private Integer memId;
	private Integer storeId;
	private Timestamp ordDate;
	private Byte shipment;
	private String ordName;
	private String ordMobile;
	private String ordAddr;
	private Integer shipFee;
	private Integer amount;
	private Byte payment;
	private String memo;
	private Date shipDate;
	private String trackNo;
	private Byte status;
	private String storeName;
	
	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Timestamp getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(Timestamp ordDate) {
		this.ordDate = ordDate;
	}
	public Byte getShipment() {
		return shipment;
	}
	public void setShipment(Byte shipment) {
		this.shipment = shipment;
	}
	public String getOrdName() {
		return ordName;
	}
	public void setOrdName(String ordName) {
		this.ordName = ordName;
	}
	public String getOrdMobile() {
		return ordMobile;
	}
	public void setOrdMobile(String ordMobile) {
		this.ordMobile = ordMobile;
	}
	public String getOrdAddr() {
		return ordAddr;
	}
	public void setOrdAddr(String ordAddr) {
		this.ordAddr = ordAddr;
	}
	public Integer getShipFee() {
		return shipFee;
	}
	public void setShipFee(Integer shipFee) {
		this.shipFee = shipFee;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Byte getPayment() {
		return payment;
	}
	public void setPayment(Byte payment) {
		this.payment = payment;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public String getTrackNo() {
		return trackNo;
	}
	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
