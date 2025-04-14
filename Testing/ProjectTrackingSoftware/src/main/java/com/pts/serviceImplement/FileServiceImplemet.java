package com.pts.serviceImplement;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pts.configurations.Constantes;
import com.pts.entitys.AttachmentFile;
import com.pts.mapper.AttachmentFileMapper;
import com.pts.repository.AttachmentFileRepository;
import com.pts.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;




@Service
public class FileServiceImplemet implements FileService {

	@Autowired
	private AttachmentFileRepository attachmentFileRepository;
	
	@Autowired
	private AttachmentFileMapper attachmentFileMapper;

//	private static String separador = java.nio.file.FileSystems.getDefault().getSeparator();
//	private static String workDir = System.getProperty("user.dir");
//	
//	//private String upload_folder = ".//src//main//resources//files//";
//	private String upload_folder = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"files"+separador;
//	
	
	@Override
	public void saveFile(MultipartFile file) {

		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(Constantes.upload_folder + file.getOriginalFilename());
				Files.write(path, bytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public Boolean saveFileDataBase(MultipartFile file, String userCode) {
			
		Boolean response = false;
		try {
			if (!file.isEmpty()) {
				AttachmentFile attach = new AttachmentFile();
				byte[] bytes = file.getBytes();
				attach.setUploadDate(new Date());
				attach.setBic(bytes);
				attach.setUserCode(userCode);
				attach.setName(file.getOriginalFilename());
				attachmentFileRepository.save(attach);
				
				if(attachmentFileRepository.findByName(file.getOriginalFilename()) != null) {
					response = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return response;
	}
	
	

}
