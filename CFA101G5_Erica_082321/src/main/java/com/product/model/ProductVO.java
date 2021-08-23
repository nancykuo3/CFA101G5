package com.product.model;

import java.io.InputStream;
import java.sql.Date;

public class ProductVO implements java.io.Serializable {
	private Integer prodId;
	private String isbn;
	private Integer storeId;
	private Byte status;
	private Integer price;
	private Integer prodQty;
	private String intro;
	private Date regDate;
	private Integer salesFig;
	private byte[] firstPic;

	// 購物車
	private Integer ordQty;
	// 商城
	private String storeName;
	private String prodName;
	private String prodVer;
	private String prodLang;
	
	public String getProdVer() {
		return prodVer;
	}

	public void setProdVer(String prodVer) {
		this.prodVer = prodVer;
	}

	public String getProdLang() {
		return prodLang;
	}

	public void setProdLang(String prodLang) {
		this.prodLang = prodLang;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public Integer getordQty() {
		return ordQty;
	}

	public void setordQty(Integer ordQty) {
		this.ordQty = ordQty;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getProdQty() {
		return prodQty;
	}

	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getSalesFig() {
		return salesFig;
	}

	public void setSalesFig(Integer salesFig) {
		this.salesFig = salesFig;
	}

	public byte[] getFirstPic() {
		return firstPic;
	}

	public void setFirstPic(byte[] firstPic) {
		this.firstPic = firstPic;
	}

}
