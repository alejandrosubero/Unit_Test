
/*
Create on Mon Aug 08 06:22:32 EDT 2022
*Copyright (C) 122.
@author Alejandro subero
@author JSH
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Employee’s hamblet </p>
*/



package com.bpm.employee.serviceImplement;

import com.bpm.employee.entitys.Deduccion;
import com.bpm.employee.service.DeduccionService;
import com.bpm.employee.repository.DeduccionRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class DeduccionServiceImplement implements DeduccionService {

protected static final Log logger = LogFactory.getLog(DeduccionServiceImplement.class);
@Autowired
private DeduccionRepository deduccionrepository;

		@Override
		public Deduccion findByImpuestoFederalRenta(Long impuestoFederalRenta){

		logger.info("Starting getDeduccion");
			Deduccion deduccionEntity = new Deduccion();
		Optional<Deduccion> fileOptional1 = deduccionrepository.findByImpuestoFederalRenta(impuestoFederalRenta);

		if (fileOptional1.isPresent()) { 

				try {
			deduccionEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return deduccionEntity;	}
		@Override
		public Deduccion findBySocialSecurity(Long socialSecurity){

		logger.info("Starting getDeduccion");
			Deduccion deduccionEntity = new Deduccion();
		Optional<Deduccion> fileOptional1 = deduccionrepository.findBySocialSecurity(socialSecurity);

		if (fileOptional1.isPresent()) { 

				try {
			deduccionEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return deduccionEntity;	}
		@Override
		public Deduccion findByMedicare(Long medicare){

		logger.info("Starting getDeduccion");
			Deduccion deduccionEntity = new Deduccion();
		Optional<Deduccion> fileOptional1 = deduccionrepository.findByMedicare(medicare);

		if (fileOptional1.isPresent()) { 

				try {
			deduccionEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return deduccionEntity;	}




		@Override
		public List<Deduccion> getAllDeduccion(){
		logger.info("Get allProyect");
			List<Deduccion> listaDeduccion = new ArrayList<Deduccion>();
				deduccionrepository.findAll().forEach(deduccion -> listaDeduccion.add(deduccion));
			return listaDeduccion;
}


		@Override
		public boolean saveDeduccion(Deduccion deduccion){
		logger.info("Save Proyect");


				try {
				deduccionrepository.save(deduccion);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateDeduccion(Deduccion  deduccion ){
			logger.info("Update Proyect");
			boolean clave = false;
		Deduccion empre = findById(deduccion.getId());
			empre = deduccion;

				try {
				deduccionrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public Deduccion findById( Long id){
				return  deduccionrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateDeduccion(Deduccion  deduccion ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<Deduccion> fileOptional2 = deduccionrepository.findById(deduccion.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateDeduccion(deduccion);
				logger.info(" is update");
			} else {
					clave = this.saveDeduccion(deduccion);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<Deduccion> findByImpuestoFederalRentaContaining(Long impuestofederalrenta){
			logger.info("Get allProyect");
 			List<Deduccion> listaDeduccion = new ArrayList<Deduccion>();
			listaDeduccion = deduccionrepository.findByImpuestoFederalRentaContaining(impuestofederalrenta);
  			return listaDeduccion;
		}

		@Override
		public List<Deduccion> findBySocialSecurityContaining(Long socialsecurity){
			logger.info("Get allProyect");
 			List<Deduccion> listaDeduccion = new ArrayList<Deduccion>();
			listaDeduccion = deduccionrepository.findBySocialSecurityContaining(socialsecurity);
  			return listaDeduccion;
		}

		@Override
		public List<Deduccion> findByMedicareContaining(Long medicare){
			logger.info("Get allProyect");
 			List<Deduccion> listaDeduccion = new ArrayList<Deduccion>();
			listaDeduccion = deduccionrepository.findByMedicareContaining(medicare);
  			return listaDeduccion;
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
