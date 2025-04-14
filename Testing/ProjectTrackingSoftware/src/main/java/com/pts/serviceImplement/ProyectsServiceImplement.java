
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

import com.pts.service.ProyectsService;
import com.pts.repository.ProyectsRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.pts.entitys.Proyects;




@Service
public class ProyectsServiceImplement implements ProyectsService {

protected static final Log logger = LogFactory.getLog(ProyectsServiceImplement.class);
@Autowired
private ProyectsRepository proyectsrepository;

		@Override
		public Proyects findByTitleProyect(String titleProyect){

		logger.info("Starting getProyects");
			Proyects proyectsEntity = new Proyects();
		Optional<Proyects> fileOptional1 = proyectsrepository.findByTitleProyect(titleProyect);

		if (fileOptional1.isPresent()) { 

				try {
			proyectsEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return proyectsEntity;	}
		@Override
		public Proyects findByDescription(String description){

		logger.info("Starting getProyects");
			Proyects proyectsEntity = new Proyects();
		Optional<Proyects> fileOptional1 = proyectsrepository.findByDescription(description);

		if (fileOptional1.isPresent()) { 

				try {
			proyectsEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return proyectsEntity;	}




		@Override
		public List<Proyects> getAllProyects(){
		logger.info("Get allProyect");
			List<Proyects> listaProyects = new ArrayList<Proyects>();
				proyectsrepository.findAll().forEach(proyects -> listaProyects.add(proyects));
			return listaProyects;
}


		@Override
		public boolean saveProyects(Proyects proyects){
		logger.info("Save Proyect");


				try {
				proyectsrepository.save(proyects);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deleteProyects( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				proyectsrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}




		@Override
		public Proyects findById( Long id){
				return  proyectsrepository.findById(id).get();
		}



		@Override
		public List<Proyects> findByTitleProyectContaining(String titleproyect){
			logger.info("Get allProyect");
 			List<Proyects> listaProyects = new ArrayList<Proyects>();
			listaProyects = proyectsrepository.findByTitleProyectContaining(titleproyect);
  			return listaProyects;
		}

		@Override
		public List<Proyects> findByDescriptionContaining(String description){
			logger.info("Get allProyect");
 			List<Proyects> listaProyects = new ArrayList<Proyects>();
			listaProyects = proyectsrepository.findByDescriptionContaining(description);
  			return listaProyects;
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
