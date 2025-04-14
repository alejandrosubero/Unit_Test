package com.pts.service;

import com.pts.pojo.LoginAuthPojo;
import com.pts.pojo.LogingResponse;

public interface AuthoritiesLogingService {
	public LogingResponse authorizationFromLogin( LoginAuthPojo auth);
}
