package com.pts.pojo;

public class LogingResponse {

	private String authorization;
	private String token;
	private String username;
	private String userCode;
	private Object status;
	
	
	
	public LogingResponse() {
		this.authorization = "Authorization";
	}


	public String getAuthorization() {
		return authorization;
	}


	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Object getStatus() {
		return status;
	}


	public void setStatus(Object status) {
		this.status = status;
	}



	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	

	
	
}
