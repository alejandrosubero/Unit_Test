package com.pts.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pts.service.FileService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/file")
public class FileController {

	@Autowired
	private FileService fileService;
	
	@PostMapping("upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
        }

        try {
        	fileService.saveFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>("Error Happen", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>("Upload file ok", HttpStatus.OK);
    }
	
	
	
}

//  String userCode