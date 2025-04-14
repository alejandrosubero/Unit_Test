package com.pts.serviceImplement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pts.pojo.LoginAuthPojo;
import com.pts.pojo.LogingResponse;
import com.pts.service.AuthoritiesLogingService;
import com.pts.entitys.User;
import com.pts.pojo.LoginAuthPojo;
import com.pts.pojo.LogingResponse;
import com.pts.pojo.UserPojo;
import com.pts.service.AuthoritiesLogingService;
import com.pts.service.UserService;

@Service
public class AuthoritiesLogingServiceImplement implements AuthoritiesLogingService {

	 @Autowired
	 private UserService userService;
	
	@Autowired
	private RestTemplateBuilder restTemplate;
	
	 @Value("${AuthoritiesLoginUrl}")
	private String urlLogin;
	
	
	
		   
	public void getHeaders() {   
	        ResponseEntity responseEntity = restTemplate.build().getForEntity("http://localhost:8080/getEmployee/{id}", String.class, 2);
	        responseEntity.getHeaders().entrySet().forEach((k) -> {
	            System.out.println("Key is:"+ k.getKey());
	            System.out.println("Values are:"+k.getValue().stream().collect(Collectors.joining()));
	        });
	      
	    }


	@Override
	public LogingResponse authorizationFromLogin(LoginAuthPojo auth) {
		
		Map<String, Object> map2 = new HashMap<>();
		LogingResponse login = new LogingResponse();
		
		String url= "http://localhost:1212/projectTrackingSoftware/login";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", auth.getUsername() );
		map.put("password", auth.getPassword() );
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
				
		login.setStatus(response.getStatusCodeValue());
		
		if (response.getStatusCodeValue() == 200){
			User user =  userService.findByUserName(auth.getUsername());
			login.setUserCode(user.getUserCode());
			login.setUsername(auth.getUsername());
		}
		
		response.getHeaders().entrySet().forEach((k) -> {
			if (k.getKey().equals("Authorization")) {
				login.setAuthorization(k.getKey());
				login.setToken( k.getValue().stream().collect(Collectors.joining()));
			}
			map2.put(k.getKey(), k.getValue().stream().collect(Collectors.joining()));
	        });
		
		return login;
	}
	
	
	
	
	
	
	
}

// what coutry are restriction for entry tu eeun