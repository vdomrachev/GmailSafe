package com.dvv.gmailSafe.http.entities;

public class Response {
	private int status;
	private Object data;
	public Response(int status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public Object getData() {
		return data;
	}
}
