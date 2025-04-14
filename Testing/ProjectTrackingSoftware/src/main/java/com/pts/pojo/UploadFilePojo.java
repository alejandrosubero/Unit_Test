package com.pts.pojo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadFilePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4317464788454747374L;

	
	private MultipartFile file;
	private String userCode;
	private Boolean delete;
	
	
	public UploadFilePojo() {
	}
	
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Boolean getDelete() {
		return delete;
	}
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	
	
	
	
}
