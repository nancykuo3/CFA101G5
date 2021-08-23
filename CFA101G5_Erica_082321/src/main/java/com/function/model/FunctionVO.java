package com.function.model;

public class FunctionVO implements java.io.Serializable {
	private Integer funcid;
	private String funcName;
	private String funcdes;

	public Integer getFuncid() {
		return funcid;
	}

	public void setFuncid(Integer funcid) {
		this.funcid = funcid;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncdes() {
		return funcdes;
	}

	public void setFuncdes(String funcdes) {
		this.funcdes = funcdes;
	}

}
