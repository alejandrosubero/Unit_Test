package com.pts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.pojo.EntityRespone;
import com.pts.pojo.LoginAuthPojo;
import com.pts.service.AuthoritiesLogingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AuthoritiesCotroller")
public class AuthoritiesCotroller {
	
	@Autowired
	private AuthoritiesLogingService authoritiesLogingService;
	
	//@RequestMapping(value = "/loginAuth", method = RequestMethod.POST, consumes="application/json")
    @PostMapping("/loginAuth")
    private EntityRespone loginAuth(@RequestBody LoginAuthPojo loging){
    	List<Object> response = new ArrayList<Object>(); 
    try {
    	
    	response.add(authoritiesLogingService.authorizationFromLogin(loging));
    	return  new EntityRespone("", " ",response);
	} catch (Exception e) {
		return  new EntityRespone("Error: " + e, "Error ",response);
	}	
    
    
    
	

    }
}



