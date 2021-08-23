package com.store.model;

public class StoreActivitesVO implements java.io.Serializable{

	private static final long serialVersionUID = -7577425030883477290L;
	private Integer storeActNo;
	private Integer storeId;
	private String storeActTitle;
	private String storeActContext;
	private Byte[] storeActImage;
	public Integer getStoreActNo() {
		return storeActNo;
	}
	public void setStoreActNo(Integer storeActNo) {
		this.storeActNo = storeActNo;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreActTitle() {
		return storeActTitle;
	}
	public void setStoreActTitle(String storeActTitle) {
		this.storeActTitle = storeActTitle;
	}
	public String getStoreActContext() {
		return storeActContext;
	}
	public void setStoreActContext(String storeActContext) {
		this.storeActContext = storeActContext;
	}
	public Byte[] getStoreActImage() {
		return storeActImage;
	}
	public void setStoreActImage(Byte[] storeActImage) {
		this.storeActImage = storeActImage;
	}
	

}
