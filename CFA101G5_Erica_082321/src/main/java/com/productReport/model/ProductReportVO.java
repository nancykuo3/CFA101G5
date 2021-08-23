package com.productReport.model;

import java.sql.Timestamp;

public class ProductReportVO implements java.io.Serializable {
	private Integer reportno;
	private Integer memid;
	private Integer prodid;
	private Timestamp reportTime;
	private String content;
	private Integer adminid;
	private Timestamp doneTime;
	private Byte status;
	private Byte result;
	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getReportno() {
		return reportno;
	}

	public void setReportno(Integer reportno) {
		this.reportno = reportno;
	}

	public Integer getMemid() {
		return memid;
	}

	public void setMemid(Integer memid) {
		this.memid = memid;
	}

	public Integer getProductid() {
		return prodid;
	}

	public void setProductid(Integer prodid) {
		this.prodid = prodid;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public Timestamp getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Timestamp doneTime) {
		this.doneTime = doneTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getResult() {
		return result;
	}

	public void setResult(Byte result) {
		this.result = result;
	}

}
