package com.product.model;

public class ProdPicVO implements java.io.Serializable {
	private Integer picId;
	private Integer prodId;
	private byte[] prodPic;

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public byte[] getPic() {
		return prodPic;
	}

	public void setPic(byte[] pic) {
		this.prodPic = pic;
	}
}
