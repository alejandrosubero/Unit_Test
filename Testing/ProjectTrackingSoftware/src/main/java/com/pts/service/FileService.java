package com.pts.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public void saveFile(MultipartFile file);
	public Boolean saveFileDataBase(MultipartFile file, String userCode);

}
