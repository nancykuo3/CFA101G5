package com.store.model;

import java.sql.Date;

public class StoreVO implements java.io.Serializable {

	private static final long serialVersionUID = -7639959615035881035L;
	private Integer storeId;
	private String storeAcc;
	private String storePwd;
	private Byte accStatus;
	private String storeName;
	private Integer storeGui;
	private String storePic;
	private String storeTel;
	private String storeFax;
	private String storeAdd;
	private String storePoc;
	private String storeConPhone;
	private String storeConAdd;
	private String storeEmail;
	private Date storeSettingDate;
	private Date storeChangeDate;
	private Integer storeCapital;
	private Date storeRegDate;
	private Byte storeStatus;
	private String bankAccount;
	private Integer storeShopRatingScore;
	private Integer storeShopRatingCount;
	private Integer storeRsvnRatingScore;
	private Integer storeRsvnRatingCount;
	private Byte storeReportCount;
	private Integer capacity;
	private Byte dayOff;
	private String openingHours;
	private Integer startTime;
	private Integer endTime;

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreAcc() {
		return storeAcc;
	}

	public void setStoreAcc(String storeAcc) {
		this.storeAcc = storeAcc;
	}

	public String getStorePwd() {
		return storePwd;
	}

	public void setStorePwd(String storePwd) {
		this.storePwd = storePwd;
	}

	public Byte getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Byte accStatus) {
		this.accStatus = accStatus;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getStoreGui() {
		return storeGui;
	}

	public void setStoreGui(Integer storeGui) {
		this.storeGui = storeGui;
	}

	public String getStorePic() {
		return storePic;
	}

	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public String getStoreFax() {
		return storeFax;
	}

	public void setStoreFax(String storeFax) {
		this.storeFax = storeFax;
	}

	public String getStoreAdd() {
		return storeAdd;
	}

	public void setStoreAdd(String storeAdd) {
		this.storeAdd = storeAdd;
	}

	public String getStorePoc() {
		return storePoc;
	}

	public void setStorePoc(String storePoc) {
		this.storePoc = storePoc;
	}

	public String getStoreConPhone() {
		return storeConPhone;
	}

	public void setStoreConPhone(String storeConPhone) {
		this.storeConPhone = storeConPhone;
	}

	public String getStoreConAdd() {
		return storeConAdd;
	}

	public void setStoreConAdd(String storeConAdd) {
		this.storeConAdd = storeConAdd;
	}

	public String getStoreEmail() {
		return storeEmail;
	}

	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}

	public Integer getStoreCapital() {
		return storeCapital;
	}

	public void setStoreCapital(Integer storeCapital) {
		this.storeCapital = storeCapital;
	}

	public Byte getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(Byte storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Integer getStoreShopRatingScore() {
		return storeShopRatingScore;
	}

	public void setStoreShopRatingScore(Integer storeShopRatingScore) {
		this.storeShopRatingScore = storeShopRatingScore;
	}

	public Integer getStoreShopRatingCount() {
		return storeShopRatingCount;
	}

	public void setStoreShopRatingCount(Integer storeShopRatingCount) {
		this.storeShopRatingCount = storeShopRatingCount;
	}

	public Integer getStoreRsvnRatingScore() {
		return storeRsvnRatingScore;
	}

	public void setStoreRsvnRatingScore(Integer storeRsvnRatingScore) {
		this.storeRsvnRatingScore = storeRsvnRatingScore;
	}

	public Integer getStoreRsvnRatingCount() {
		return storeRsvnRatingCount;
	}

	public void setStoreRsvnRatingCount(Integer storeRsvnRatingCount) {
		this.storeRsvnRatingCount = storeRsvnRatingCount;
	}

	public Byte getStoreReportCount() {
		return storeReportCount;
	}

	public void setStoreReportCount(Byte storeReportCount) {
		this.storeReportCount = storeReportCount;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Byte getDayOff() {
		return dayOff;
	}

	public void setDayOff(Byte dayOff) {
		this.dayOff = dayOff;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public Date getStoreSettingDate() {
		return storeSettingDate;
	}

	public void setStoreSettingDate(Date storeSettingDate) {
		this.storeSettingDate = storeSettingDate;
	}

	public Date getStoreChangeDate() {
		return storeChangeDate;
	}

	public void setStoreChangeDate(Date storeChangeDate) {
		this.storeChangeDate = storeChangeDate;
	}

	public Date getStoreRegDate() {
		return storeRegDate;
	}

	public void setStoreRegDate(Date storeRegDate) {
		this.storeRegDate = storeRegDate;
	}

	@Override
	public String toString() {
		return "StoreVO [storeId=" + storeId + ", storeAcc=" + storeAcc + ", storePwd=" + storePwd + ", accStatus="
				+ accStatus + ", storeName=" + storeName + ", storeGui=" + storeGui + ", storePic=" + storePic
				+ ", storeTel=" + storeTel + ", storeFax=" + storeFax + ", storeAdd=" + storeAdd + ", storePoc="
				+ storePoc + ", storeConPhone=" + storeConPhone + ", storeConAdd=" + storeConAdd + ", storeEmail="
				+ storeEmail + ", storeSettingDate=" + storeSettingDate + ", storeChangeDate=" + storeChangeDate
				+ ", storeCapital=" + storeCapital + ", storeRegDate=" + storeRegDate + ", storeStatus=" + storeStatus
				+ ", bankAccount=" + bankAccount + ", storeShopRatingScore=" + storeShopRatingScore
				+ ", storeShopRatingCount=" + storeShopRatingCount + ", storeRsvnRatingScore=" + storeRsvnRatingScore
				+ ", storeRsvnRatingCount=" + storeRsvnRatingCount + ", storeReportCount=" + storeReportCount
				+ ", capacity=" + capacity + ", dayOff=" + dayOff + ", openingHours=" + openingHours + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
