
/*
Create on Sun Nov 07 14:17:04 ART 2021
*Copyright (C) 121.
@author Alejandro Subero
@author Subero Alejandro
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: This project tracking software </p>
*/


package com.pts.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.pts.entitys.User;import com.pts.entitys.Rol;
import com.pts.pojo.EntityRespone;
import com.pts.pojo.UserResponsePojo;


public interface UserService{
	
	    public Boolean addUserToTeam(String userCode, Long id, String team, String description);
	
		public List<UserResponsePojo> allUsers();

	    public EntityRespone newUser(User user , String Key);

	    public User  findByUserCode(String userCode);

		public User  findByUserFirsName(String userFirsName);

		public User  findByUserLastName(String userLastName);

		public User  findByFullName(String fullName);

		public User  findByUserName(String userName);
		
		//public Optional<User> findByUserName(String userName);

		public User  findByMail(String mail);

		public User  findByPassword(String password);

		public User  findByImagen(String imagen);

		public User  findByAccountNonExpired(Boolean accountNonExpired);

		public User  findByAccountNonLocked(Boolean accountNonLocked);

		public User  findByCredentialsNonExpired(Boolean credentialsNonExpired);

		public User  findByEnabled(Boolean enabled);

		public List<User>  findByUserCodeContaining(String userCode);

		public List<User>  findByUserFirsNameContaining(String userFirsName);

		public List<User>  findByUserLastNameContaining(String userLastName);

		public List<User>  findByFullNameContaining(String fullName);

		public List<User>  findByUserNameContaining(String userName);

		public List<User>  findByMailContaining(String mail);

		public List<User>  findByPasswordContaining(String password);

		public List<User>  findByImagenContaining(String imagen);

		public List<User>  findByAccountNonExpiredContaining(Boolean accountNonExpired);

		public List<User>  findByAccountNonLockedContaining(Boolean accountNonLocked);

		public List<User>  findByCredentialsNonExpiredContaining(Boolean credentialsNonExpired);

		public List<User>  findByEnabledContaining(Boolean enabled);

		public User findById(Long id);
		public boolean saveUser(User user);
		public List<User> getAllUser();
		public boolean deleteUser(Long id);

		public List<User>  findByRolContaining(Rol rol);
}
 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


