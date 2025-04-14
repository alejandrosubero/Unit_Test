
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

import com.pts.service.TimeReportService;
import com.pts.repository.TimeReportRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.pts.entitys.TimeReport;




@Service
public class TimeReportServiceImplement implements TimeReportService {

protected static final Log logger = LogFactory.getLog(TimeReportServiceImplement.class);
@Autowired
private TimeReportRepository timereportrepository;

		@Override
		public TimeReport findByTimeDedicate(Date timeDedicate){

		logger.info("Starting getTimeReport");
			TimeReport timereportEntity = new TimeReport();
		Optional<TimeReport> fileOptional1 = timereportrepository.findByTimeDedicate(timeDedicate);

		if (fileOptional1.isPresent()) { 

				try {
			timereportEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timereportEntity;	}
		@Override
		public TimeReport findByTimeDedicateTotal(Date timeDedicateTotal){

		logger.info("Starting getTimeReport");
			TimeReport timereportEntity = new TimeReport();
		Optional<TimeReport> fileOptional1 = timereportrepository.findByTimeDedicateTotal(timeDedicateTotal);

		if (fileOptional1.isPresent()) { 

				try {
			timereportEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timereportEntity;	}
		@Override
		public TimeReport findByIdReport(Long idReport){

		logger.info("Starting getTimeReport");
			TimeReport timereportEntity = new TimeReport();
		Optional<TimeReport> fileOptional1 = timereportrepository.findByIdReport(idReport);

		if (fileOptional1.isPresent()) { 

				try {
			timereportEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timereportEntity;	}
		@Override
		public TimeReport findByUserCode(String userCode){

		logger.info("Starting getTimeReport");
			TimeReport timereportEntity = new TimeReport();
		Optional<TimeReport> fileOptional1 = timereportrepository.findByUserCode(userCode);

		if (fileOptional1.isPresent()) { 

				try {
			timereportEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return timereportEntity;	}




		@Override
		public List<TimeReport> getAllTimeReport(){
		logger.info("Get allProyect");
			List<TimeReport> listaTimeReport = new ArrayList<TimeReport>();
				timereportrepository.findAll().forEach(timereport -> listaTimeReport.add(timereport));
			return listaTimeReport;
}


		@Override
		public boolean saveTimeReport(TimeReport timereport){
		logger.info("Save Proyect");


				try {
				timereportrepository.save(timereport);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deleteTimeReport( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				timereportrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}




		@Override
		public TimeReport findById( Long id){
				return  timereportrepository.findById(id).get();
		}



		@Override
		public List<TimeReport> findByTimeDedicateContaining(Date timededicate){
			logger.info("Get allProyect");
 			List<TimeReport> listaTimeReport = new ArrayList<TimeReport>();
			listaTimeReport = timereportrepository.findByTimeDedicateContaining(timededicate);
  			return listaTimeReport;
		}

		@Override
		public List<TimeReport> findByTimeDedicateTotalContaining(Date timededicatetotal){
			logger.info("Get allProyect");
 			List<TimeReport> listaTimeReport = new ArrayList<TimeReport>();
			listaTimeReport = timereportrepository.findByTimeDedicateTotalContaining(timededicatetotal);
  			return listaTimeReport;
		}

		@Override
		public List<TimeReport> findByIdReportContaining(Long idreport){
			logger.info("Get allProyect");
 			List<TimeReport> listaTimeReport = new ArrayList<TimeReport>();
			listaTimeReport = timereportrepository.findByIdReportContaining(idreport);
  			return listaTimeReport;
		}

		@Override
		public List<TimeReport> findByUserCodeContaining(String usercode){
			logger.info("Get allProyect");
 			List<TimeReport> listaTimeReport = new ArrayList<TimeReport>();
			listaTimeReport = timereportrepository.findByUserCodeContaining(usercode);
  			return listaTimeReport;
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
