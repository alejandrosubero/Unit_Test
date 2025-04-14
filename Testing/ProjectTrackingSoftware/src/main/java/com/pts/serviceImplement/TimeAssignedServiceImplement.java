
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

import com.pts.service.TimeAssignedService;
import com.pts.repository.TimeAssignedRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.pts.entitys.TimeAssigned;




@Service
public class TimeAssignedServiceImplement implements TimeAssignedService {

protected static final Log logger = LogFactory.getLog(TimeAssignedServiceImplement.class);
@Autowired
private TimeAssignedRepository timeassignedrepository;

		@Override
		public TimeAssigned findByAssignedDate(Date assignedDate){

		logger.info("Starting getTimeAssigned");
			TimeAssigned timeassignedEntity = new TimeAssigned();
		Optional<TimeAssigned> fileOptional1 = timeassignedrepository.findByAssignedDate(assignedDate);

		if (fileOptional1.isPresent()) { 

				try {
			timeassignedEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timeassignedEntity;	}
		@Override
		public TimeAssigned findByStateAssigned(String stateAssigned){

		logger.info("Starting getTimeAssigned");
			TimeAssigned timeassignedEntity = new TimeAssigned();
		Optional<TimeAssigned> fileOptional1 = timeassignedrepository.findByStateAssigned(stateAssigned);

		if (fileOptional1.isPresent()) { 

				try {
			timeassignedEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timeassignedEntity;	}
		@Override
		public TimeAssigned findByStateReport(String stateReport){

		logger.info("Starting getTimeAssigned");
			TimeAssigned timeassignedEntity = new TimeAssigned();
		Optional<TimeAssigned> fileOptional1 = timeassignedrepository.findByStateReport(stateReport);

		if (fileOptional1.isPresent()) { 

				try {
			timeassignedEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timeassignedEntity;	}
		@Override
		public TimeAssigned findByUserCode(String userCode){

		logger.info("Starting getTimeAssigned");
			TimeAssigned timeassignedEntity = new TimeAssigned();
		Optional<TimeAssigned> fileOptional1 = timeassignedrepository.findByUserCode(userCode);

		if (fileOptional1.isPresent()) { 

				try {
			timeassignedEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timeassignedEntity;	}
		@Override
		public TimeAssigned findByIdReport(Long idReport){

		logger.info("Starting getTimeAssigned");
			TimeAssigned timeassignedEntity = new TimeAssigned();
		Optional<TimeAssigned> fileOptional1 = timeassignedrepository.findByIdReport(idReport);

		if (fileOptional1.isPresent()) { 

				try {
			timeassignedEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timeassignedEntity;	}
		@Override
		public TimeAssigned findByUserAssigned(String userAssigned){

		logger.info("Starting getTimeAssigned");
			TimeAssigned timeassignedEntity = new TimeAssigned();
		Optional<TimeAssigned> fileOptional1 = timeassignedrepository.findByUserAssigned(userAssigned);

		if (fileOptional1.isPresent()) { 

				try {
			timeassignedEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timeassignedEntity;	}




		@Override
		public List<TimeAssigned> getAllTimeAssigned(){
		logger.info("Get allProyect");
			List<TimeAssigned> listaTimeAssigned = new ArrayList<TimeAssigned>();
				timeassignedrepository.findAll().forEach(timeassigned -> listaTimeAssigned.add(timeassigned));
			return listaTimeAssigned;
}


		@Override
		public boolean saveTimeAssigned(TimeAssigned timeassigned){
		logger.info("Save Proyect");


				try {
				timeassignedrepository.save(timeassigned);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deleteTimeAssigned( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				timeassignedrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}




		@Override
		public TimeAssigned findById( Long id){
				return  timeassignedrepository.findById(id).get();
		}



		@Override
		public List<TimeAssigned> findByAssignedDateContaining(Date assigneddate){
			logger.info("Get allProyect");
 			List<TimeAssigned> listaTimeAssigned = new ArrayList<TimeAssigned>();
			listaTimeAssigned = timeassignedrepository.findByAssignedDateContaining(assigneddate);
  			return listaTimeAssigned;
		}

		@Override
		public List<TimeAssigned> findByStateAssignedContaining(String stateassigned){
			logger.info("Get allProyect");
 			List<TimeAssigned> listaTimeAssigned = new ArrayList<TimeAssigned>();
			listaTimeAssigned = timeassignedrepository.findByStateAssignedContaining(stateassigned);
  			return listaTimeAssigned;
		}

		@Override
		public List<TimeAssigned> findByStateReportContaining(String statereport){
			logger.info("Get allProyect");
 			List<TimeAssigned> listaTimeAssigned = new ArrayList<TimeAssigned>();
			listaTimeAssigned = timeassignedrepository.findByStateReportContaining(statereport);
  			return listaTimeAssigned;
		}

		@Override
		public List<TimeAssigned> findByUserCodeContaining(String usercode){
			logger.info("Get allProyect");
 			List<TimeAssigned> listaTimeAssigned = new ArrayList<TimeAssigned>();
			listaTimeAssigned = timeassignedrepository.findByUserCodeContaining(usercode);
  			return listaTimeAssigned;
		}

		@Override
		public List<TimeAssigned> findByIdReportContaining(Long idreport){
			logger.info("Get allProyect");
 			List<TimeAssigned> listaTimeAssigned = new ArrayList<TimeAssigned>();
			listaTimeAssigned = timeassignedrepository.findByIdReportContaining(idreport);
  			return listaTimeAssigned;
		}

		@Override
		public List<TimeAssigned> findByUserAssignedContaining(String userassigned){
			logger.info("Get allProyect");
 			List<TimeAssigned> listaTimeAssigned = new ArrayList<TimeAssigned>();
			listaTimeAssigned = timeassignedrepository.findByUserAssignedContaining(userassigned);
  			return listaTimeAssigned;
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
