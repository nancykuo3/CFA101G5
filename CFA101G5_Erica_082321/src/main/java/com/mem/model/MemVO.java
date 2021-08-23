package com.mem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.pic.model.PicVO;

public class MemVO implements java.io.Serializable {

	private static final long serialVersionUID = -3931037260596136913L;
	private Integer memId;
	private String memAcc;
	private String memPwd;
	private Byte accStatus;
	private String memName;
	private String memGender;
 	private String memEmail;
	private String memMobile;
	private String memCity;
	private String memDist;
	private String memAddr;
	private String memRegDate;
	private byte[] memPic;
	private String memPicSrc;
	private String picId;
	private Integer memRatingScore;	
	private Integer memRatingCount;
	private Byte memReportCount;
	

	private List<PicVO> PicVO = new ArrayList<PicVO>();

	
	
	
	public String getMemPicSrc() {
		return memPicSrc;
	}
	public void setMemPicSrc(String memPicSrc) {
		this.memPicSrc = memPicSrc;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemAcc() {
		return memAcc;
	}
	public void setMemAcc(String memAcc) {
		this.memAcc = memAcc;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public Byte getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(Byte accStatus) {
		this.accStatus = accStatus;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemMobile() {
		return memMobile;
	}
	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
	}
	public String getMemCity() {
		return memCity;
	}
	public void setMemCity(String memCity) {
		this.memCity = memCity;
	}
	public String getMemDist() {
		return memDist;
	}
	public void setMemDist(String memDist) {
		this.memDist = memDist;
	
	}
	
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public String getMemRegDate() {
		return memRegDate;
	}
	public void setMemRegDate(String memRegDate) {
		this.memRegDate = memRegDate;
	}
	public byte[] getMemPic() {
		return memPic;
	}
	public void setMemPic(byte[] memPic) {
		this.memPic = memPic;
	}
	public Integer getMemRatingScore() {
		return memRatingScore;
	}
	public void setMemRatingScore(Integer memRatingScore) {
		this.memRatingScore = memRatingScore;
	}
	public Integer getMemRatingCount() {
		return memRatingCount;
	}
	public void setMemRatingCount(Integer memRatingCount) {
		this.memRatingCount = memRatingCount;
	}
	public Byte getMemReportCount() {
		return memReportCount;
	}
	public void setMemReportCount(Byte memReportCount) {
		this.memReportCount = memReportCount;
	}
	@Override
	public String toString() {
		return "MemVO [memId=" + memId + ", memAcc=" + memAcc + ", memPwd=" + memPwd + ", accStatus=" + accStatus
				+ ", memName=" + memName + ", memGender=" + memGender + ", memEmail=" + memEmail + ", memMobile="
				+ memMobile + ", memCity=" + memCity + ", memDist=" + memDist + ", memAddr=" + memAddr + ", memRegDate="
				+ memRegDate + ", memPic=" + Arrays.toString(memPic) + ", memRatingScore=" + memRatingScore
				+ ", memRatingCount=" + memRatingCount + ", memReportCount=" + memReportCount + "]";
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	
	
	
}
	