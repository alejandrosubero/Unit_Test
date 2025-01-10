
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

import com.bpm.employee.entitys.FichaFaltas;
import com.bpm.employee.service.FichaFaltasService;
import com.bpm.employee.repository.FichaFaltasRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class FichaFaltasServiceImplement implements FichaFaltasService {

protected static final Log logger = LogFactory.getLog(FichaFaltasServiceImplement.class);
@Autowired
private FichaFaltasRepository fichafaltasrepository;

		@Override
		public FichaFaltas findByFechaFalta(Date fechaFalta){

		logger.info("Starting getFichaFaltas");
			FichaFaltas fichafaltasEntity = new FichaFaltas();
		Optional<FichaFaltas> fileOptional1 = fichafaltasrepository.findByFechaFalta(fechaFalta);

		if (fileOptional1.isPresent()) { 

				try {
			fichafaltasEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichafaltasEntity;	}
		@Override
		public FichaFaltas findByRazon(String razon){

		logger.info("Starting getFichaFaltas");
			FichaFaltas fichafaltasEntity = new FichaFaltas();
		Optional<FichaFaltas> fileOptional1 = fichafaltasrepository.findByRazon(razon);

		if (fileOptional1.isPresent()) { 

				try {
			fichafaltasEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichafaltasEntity;	}
		@Override
		public FichaFaltas findByRetorno(Date retorno){

		logger.info("Starting getFichaFaltas");
			FichaFaltas fichafaltasEntity = new FichaFaltas();
		Optional<FichaFaltas> fileOptional1 = fichafaltasrepository.findByRetorno(retorno);

		if (fileOptional1.isPresent()) { 

				try {
			fichafaltasEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichafaltasEntity;	}




		@Override
		public List<FichaFaltas> getAllFichaFaltas(){
		logger.info("Get allProyect");
			List<FichaFaltas> listaFichaFaltas = new ArrayList<FichaFaltas>();
				fichafaltasrepository.findAll().forEach(fichafaltas -> listaFichaFaltas.add(fichafaltas));
			return listaFichaFaltas;
}


		@Override
		public boolean saveFichaFaltas(FichaFaltas fichafaltas){
		logger.info("Save Proyect");


				try {
				fichafaltasrepository.save(fichafaltas);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaFaltas(FichaFaltas  fichafaltas ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaFaltas empre = findById(fichafaltas.getId());
			empre = fichafaltas;

				try {
				fichafaltasrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaFaltas findById( Long id){
				return  fichafaltasrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaFaltas(FichaFaltas  fichafaltas ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaFaltas> fileOptional2 = fichafaltasrepository.findById(fichafaltas.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaFaltas(fichafaltas);
				logger.info(" is update");
			} else {
					clave = this.saveFichaFaltas(fichafaltas);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<FichaFaltas> findByFechaFaltaContaining(Date fechafalta){
			logger.info("Get allProyect");
 			List<FichaFaltas> listaFichaFaltas = new ArrayList<FichaFaltas>();
			listaFichaFaltas = fichafaltasrepository.findByFechaFaltaContaining(fechafalta);
  			return listaFichaFaltas;
		}

		@Override
		public List<FichaFaltas> findByRazonContaining(String razon){
			logger.info("Get allProyect");
 			List<FichaFaltas> listaFichaFaltas = new ArrayList<FichaFaltas>();
			listaFichaFaltas = fichafaltasrepository.findByRazonContaining(razon);
  			return listaFichaFaltas;
		}

		@Override
		public List<FichaFaltas> findByRetornoContaining(Date retorno){
			logger.info("Get allProyect");
 			List<FichaFaltas> listaFichaFaltas = new ArrayList<FichaFaltas>();
			listaFichaFaltas = fichafaltasrepository.findByRetornoContaining(retorno);
  			return listaFichaFaltas;
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
