package com.admin.model;

public class AdministratorVO implements java.io.Serializable {
	private Integer adminid;
	private String adminacc;
	private String adminpwd;
	private Byte adminStatus;
	private String adminName;

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getAdminacc() {
		return adminacc;
	}

	public void setAdminacc(String adminacc) {
		this.adminacc = adminacc;
	}

	public String getAdminpwd() {
		return adminpwd;
	}

	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}

	public Byte getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Byte adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
