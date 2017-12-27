package com.share.entity;

public class Comm {

	private int commId;
	private String commName;
	public Comm(int commId, String commName) {
		this.commId = commId;
		this.commName = commName;
	}

	public Comm() {
	}

	public int getCommId() {
		return commId;
	}

	public void setCommId(int commId) {
		this.commId = commId;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

}
