package com.pic.model;

import java.util.Arrays;

public class PicVO implements java.io.Serializable {


	private static final long serialVersionUID = 988544060564158485L;
	private String memId;
	private String picId;
	private byte[] pic;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "PicVO [memId=" + memId + ", picId=" + picId + ", pic=" + Arrays.toString(pic) + "]";
	}

}

