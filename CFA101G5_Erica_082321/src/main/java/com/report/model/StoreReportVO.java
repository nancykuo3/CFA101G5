package com.report.model;



public class StoreReportVO implements java.io.Serializable {

	private static final long serialVersionUID = 2161863553508904574L;
	private Integer reportno;
	private Integer memid;
	private Integer storeid;
	private String reportTime;
	private String content;
	private Integer adminid;
	private String doneTime;
	private Byte status;
	private Byte result;
	private String note;
	
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
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
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
	public String getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(String doneTime) {
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	
}