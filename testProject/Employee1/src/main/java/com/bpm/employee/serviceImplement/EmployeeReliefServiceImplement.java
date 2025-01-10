
/*
Create on Sat Oct 07 21:00:29 EDT 2023
*Copyright (C) 123.
@author yo
@author yo
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Employee’s hamblet </p>
*/



package com.bpm.employee.serviceImplement;

import com.bpm.employee.entitys.EmployeeRelief;
import com.bpm.employee.mapper.EmployeeReliefMapper;
import com.bpm.employee.pojo.EmployeeReliefPojo;
import com.bpm.employee.service.EmployeeReliefService;
import com.bpm.employee.repository.EmployeeReliefRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class EmployeeReliefServiceImplement implements EmployeeReliefService {

protected static final Log logger = LogFactory.getLog(EmployeeReliefServiceImplement.class);
@Autowired
private EmployeeReliefRepository employeereliefrepository;

		@Autowired
		private EmployeeReliefMapper employeereliefMapper;


		@Override
		public EmployeeReliefPojo findByEmployeeToBeRelieved(String	employeeToBeRelieved){

		logger.info("Starting getEmployeeRelief");
		EmployeeRelief employeereliefEntity = new EmployeeRelief();
		Optional<EmployeeRelief> fileOptional1 = employeereliefrepository.findByEmployeeToBeRelieved(employeeToBeRelieved);

		if (fileOptional1.isPresent()) { 

				try {
			employeereliefEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	employeereliefMapper.entityToPojo(employeereliefEntity);	}
		@Override
		public	EmployeeReliefPojo	findByEmployeeRelief(String	employeeRelief){

		logger.info("Starting getEmployeeRelief");
		EmployeeRelief	employeereliefEntity = new EmployeeRelief();
		Optional<EmployeeRelief> fileOptional1 = employeereliefrepository.findByEmployeeRelief(employeeRelief);

		if (fileOptional1.isPresent()) { 

				try {
			employeereliefEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	employeereliefMapper.entityToPojo(employeereliefEntity);	}




		@Override
		public List<EmployeeReliefPojo> getAllEmployeeRelief(){
		logger.info("execute> getAllEmployeeReliefGet allProyect");
			List<EmployeeReliefPojo> listaEmployeeRelief = new ArrayList<EmployeeReliefPojo>();
				employeereliefrepository.findAll().forEach(employeerelief -> listaEmployeeRelief.add(employeereliefMapper.entityToPojo(employeerelief)));
			return listaEmployeeRelief;
	}


		@Override
		public boolean saveEmployeeRelief(EmployeeRelief employeerelief){
		logger.info("Save Proyect");


				try {
				employeereliefrepository.save(employeerelief);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateEmployeeRelief(EmployeeRelief	employeerelief ){
			logger.info("Update ENTITY");
			boolean clave = false;
		EmployeeRelief empre = employeereliefrepository.findById(employeerelief.getId()).get();
			empre = employeerelief;

				try {
			employeereliefrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			clave = false;
				}

					return clave;
	}



		@Override
		public	EmployeeReliefPojo findById( Long id){
				return  employeereliefMapper.entityToPojo(employeereliefrepository.findById(id).get());
		}



		@Override
		public boolean saveOrUpdateEmployeeRelief(EmployeeRelief  employeerelief ){
			logger.info("Update Proyect");
			boolean clave = false;
	Optional<EmployeeRelief> fileOptional2 = employeereliefrepository.findById(employeerelief.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateEmployeeRelief(employeerelief);
				logger.info(" is update");
			} else {
					clave = this.saveEmployeeRelief(employeerelief);
					logger.info(" is save");
 				}
 		return clave;
		}



		@Override
		public List<EmployeeReliefPojo> findByEmployeeToBeRelievedContaining(String	employeetoberelieved){
		logger.info("Execute EmployeeToBeRelieved Containing" );		List<EmployeeReliefPojo> listaEmployeeRelief = new ArrayList<EmployeeReliefPojo>();
		List<EmployeeRelief> listaSEmployeeRelief = employeereliefrepository.findByEmployeeToBeRelievedContaining(employeetoberelieved);
		listaSEmployeeRelief.forEach(employeereliefx -> listaEmployeeRelief.add(employeereliefMapper.entityToPojo(employeereliefx)));
				return listaEmployeeRelief;
		}


		@Override
		public List<EmployeeReliefPojo> findByEmployeeReliefContaining(String	employeerelief){
		logger.info("Execute EmployeeRelief Containing" );		List<EmployeeReliefPojo> listaEmployeeRelief = new ArrayList<EmployeeReliefPojo>();
		List<EmployeeRelief> listaSEmployeeRelief = employeereliefrepository.findByEmployeeReliefContaining(employeerelief);
		listaSEmployeeRelief.forEach(employeereliefx -> listaEmployeeRelief.add(employeereliefMapper.entityToPojo(employeereliefx)));
				return listaEmployeeRelief;
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
