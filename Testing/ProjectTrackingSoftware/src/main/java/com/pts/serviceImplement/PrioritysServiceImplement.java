
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

import com.pts.service.PrioritysService;
import com.pts.repository.PrioritysRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.pts.entitys.Prioritys;




@Service
public class PrioritysServiceImplement implements PrioritysService {

protected static final Log logger = LogFactory.getLog(PrioritysServiceImplement.class);
@Autowired
private PrioritysRepository prioritysrepository;

		@Override
		public Prioritys findByValue(String value){

		logger.info("Starting getPrioritys");
			Prioritys prioritysEntity = new Prioritys();
		Optional<Prioritys> fileOptional1 = prioritysrepository.findByValue(value);

		if (fileOptional1.isPresent()) { 

				try {
			prioritysEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return prioritysEntity;	}
		@Override
		public Prioritys findByDescription(String description){

		logger.info("Starting getPrioritys");
			Prioritys prioritysEntity = new Prioritys();
		Optional<Prioritys> fileOptional1 = prioritysrepository.findByDescription(description);

		if (fileOptional1.isPresent()) { 

				try {
			prioritysEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return prioritysEntity;	}




		@Override
		public List<Prioritys> getAllPrioritys(){
		logger.info("Get allProyect");
			List<Prioritys> listaPrioritys = new ArrayList<Prioritys>();
				prioritysrepository.findAll().forEach(prioritys -> listaPrioritys.add(prioritys));
			return listaPrioritys;
}


		@Override
		public boolean savePrioritys(Prioritys prioritys){
		logger.info("Save Proyect");


				try {
				prioritysrepository.save(prioritys);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deletePrioritys( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				prioritysrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}




		@Override
		public Prioritys findById( Long id){
				return  prioritysrepository.findById(id).get();
		}



		@Override
		public List<Prioritys> findByValueContaining(String value){
			logger.info("Get allProyect");
 			List<Prioritys> listaPrioritys = new ArrayList<Prioritys>();
			listaPrioritys = prioritysrepository.findByValueContaining(value);
  			return listaPrioritys;
		}

		@Override
		public List<Prioritys> findByDescriptionContaining(String description){
			logger.info("Get allProyect");
 			List<Prioritys> listaPrioritys = new ArrayList<Prioritys>();
			listaPrioritys = prioritysrepository.findByDescriptionContaining(description);
  			return listaPrioritys;
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
