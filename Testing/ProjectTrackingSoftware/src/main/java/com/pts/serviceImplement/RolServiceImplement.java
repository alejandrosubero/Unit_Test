
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



package com.pts.serviceImplement ;

import com.pts.service.RolService;
import com.pts.repository.RolRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.pts.entitys.Rol;




@Service
public class RolServiceImplement implements RolService {

protected static final Log logger = LogFactory.getLog(RolServiceImplement.class);
@Autowired
private RolRepository rolrepository;

		@Override
		public Rol findByRol(String rol){

		logger.info("Starting getRol");
			Rol rolEntity = new Rol();
		Optional<Rol> fileOptional1 = rolrepository.findByRol(rol);

		if (fileOptional1.isPresent()) { 

				try {
			rolEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return rolEntity;	}
		@Override
		public Rol findByDescription(String description){

		logger.info("Starting getRol");
			Rol rolEntity = new Rol();
		Optional<Rol> fileOptional1 = rolrepository.findByDescription(description);

		if (fileOptional1.isPresent()) { 

				try {
			rolEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return rolEntity;	}




		@Override
		public List<Rol> getAllRol(){
		logger.info("Get allProyect");
			List<Rol> listaRol = new ArrayList<Rol>();
				rolrepository.findAll().forEach(rol -> listaRol.add(rol));
			return listaRol;
}


		@Override
		public boolean saveRol(Rol rol){
		logger.info("Save Proyect");


				try {
				rolrepository.save(rol);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deleteRol( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				rolrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}




		@Override
		public Rol findById( Long id){
				return  rolrepository.findById(id).get();
		}



		@Override
		public List<Rol> findByRolContaining(String rol){
			logger.info("Get allProyect");
 			List<Rol> listaRol = new ArrayList<Rol>();
			listaRol = rolrepository.findByRolContaining(rol);
  			return listaRol;
		}

		@Override
		public List<Rol> findByDescriptionContaining(String description){
			logger.info("Get allProyect");
 			List<Rol> listaRol = new ArrayList<Rol>();
			listaRol = rolrepository.findByDescriptionContaining(description);
  			return listaRol;
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


}
