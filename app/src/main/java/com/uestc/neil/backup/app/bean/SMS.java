package com.uestc.neil.backup.app.bean;

public class SMS {
	private String body;
	private long id;
	private long date;
	private String address;

	public SMS(String body, long id, long date, String address) {
		this.body = body;
		this.id = id;
		this.date = date;
		this.address = address;
	}

	public SMS() {
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "id-" + id + ",address-" + address + ",body-" + body + ",date-"
				+ date;
	}
}
