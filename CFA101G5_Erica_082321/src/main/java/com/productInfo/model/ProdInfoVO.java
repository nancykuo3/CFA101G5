package com.productInfo.model;

public class ProdInfoVO implements java.io.Serializable{
	private String isbn;
	private String prodName;
	private String prodLang;
	private String prodVer;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdLang() {
		return prodLang;
	}
	public void setProdLang(String prodLang) {
		this.prodLang = prodLang;
	}
	public String getProdVer() {
		return prodVer;
	}
	public void setProdVer(String prodVer) {
		this.prodVer = prodVer;
	}
}

