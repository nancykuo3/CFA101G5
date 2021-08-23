package com.favrecode.model;

import java.sql.Date;

public class ProdFavRecordVO implements java.io.Serializable {

	private static final long serialVersionUID = 3993065823304537423L;
	private Integer memId;
	private Integer prodId;
	private Date prodFavDate;
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Date getProdFavDate() {
		return prodFavDate;
	}
	public void setProdFavDate(Date prodFavDate) {
		this.prodFavDate = prodFavDate;
	}
	

	
}
