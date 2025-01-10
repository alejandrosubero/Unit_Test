
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

import com.bpm.employee.entitys.Enfermedad;
import com.bpm.employee.service.EnfermedadService;
import com.bpm.employee.repository.EnfermedadRepository;
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
public class EnfermedadServiceImplement implements EnfermedadService {

protected static final Log logger = LogFactory.getLog(EnfermedadServiceImplement.class);
@Autowired
private EnfermedadRepository enfermedadrepository;

		@Override
		public Enfermedad findByName(String name){

		logger.info("Starting getEnfermedad");
			Enfermedad enfermedadEntity = new Enfermedad();
		Optional<Enfermedad> fileOptional1 = enfermedadrepository.findByName(name);

		if (fileOptional1.isPresent()) { 

				try {
			enfermedadEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return enfermedadEntity;	}
		@Override
		public Enfermedad findByDateEnfermedad(Date dateEnfermedad){

		logger.info("Starting getEnfermedad");
			Enfermedad enfermedadEntity = new Enfermedad();
		Optional<Enfermedad> fileOptional1 = enfermedadrepository.findByDateEnfermedad(dateEnfermedad);

		if (fileOptional1.isPresent()) { 

				try {
			enfermedadEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return enfermedadEntity;	}
		@Override
		public Enfermedad findByDianostico(String dianostico){

		logger.info("Starting getEnfermedad");
			Enfermedad enfermedadEntity = new Enfermedad();
		Optional<Enfermedad> fileOptional1 = enfermedadrepository.findByDianostico(dianostico);

		if (fileOptional1.isPresent()) { 

				try {
			enfermedadEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return enfermedadEntity;	}
		@Override
		public Enfermedad findByNotas(String notas){

		logger.info("Starting getEnfermedad");
			Enfermedad enfermedadEntity = new Enfermedad();
		Optional<Enfermedad> fileOptional1 = enfermedadrepository.findByNotas(notas);

		if (fileOptional1.isPresent()) { 

				try {
			enfermedadEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return enfermedadEntity;	}
		@Override
		public Enfermedad findByCronica(Boolean cronica){

		logger.info("Starting getEnfermedad");
			Enfermedad enfermedadEntity = new Enfermedad();
		Optional<Enfermedad> fileOptional1 = enfermedadrepository.findByCronica(cronica);

		if (fileOptional1.isPresent()) { 

				try {
			enfermedadEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return enfermedadEntity;	}




		@Override
		public List<Enfermedad> getAllEnfermedad(){
		logger.info("Get allProyect");
			List<Enfermedad> listaEnfermedad = new ArrayList<Enfermedad>();
				enfermedadrepository.findAll().forEach(enfermedad -> listaEnfermedad.add(enfermedad));
			return listaEnfermedad;
}


		@Override
		public boolean saveEnfermedad(Enfermedad enfermedad){
		logger.info("Save Proyect");


				try {
				enfermedadrepository.save(enfermedad);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateEnfermedad(Enfermedad  enfermedad ){
			logger.info("Update Proyect");
			boolean clave = false;
		Enfermedad empre = findById(enfermedad.getId());
			empre = enfermedad;

				try {
				enfermedadrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public Enfermedad findById( Long id){
				return  enfermedadrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateEnfermedad(Enfermedad  enfermedad ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<Enfermedad> fileOptional2 = enfermedadrepository.findById(enfermedad.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateEnfermedad(enfermedad);
				logger.info(" is update");
			} else {
					clave = this.saveEnfermedad(enfermedad);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<Enfermedad> findByNameContaining(String name){
			logger.info("Get allProyect");
 			List<Enfermedad> listaEnfermedad = new ArrayList<Enfermedad>();
			listaEnfermedad = enfermedadrepository.findByNameContaining(name);
  			return listaEnfermedad;
		}

		@Override
		public List<Enfermedad> findByDateEnfermedadContaining(Date dateenfermedad){
			logger.info("Get allProyect");
 			List<Enfermedad> listaEnfermedad = new ArrayList<Enfermedad>();
			listaEnfermedad = enfermedadrepository.findByDateEnfermedadContaining(dateenfermedad);
  			return listaEnfermedad;
		}

		@Override
		public List<Enfermedad> findByDianosticoContaining(String dianostico){
			logger.info("Get allProyect");
 			List<Enfermedad> listaEnfermedad = new ArrayList<Enfermedad>();
			listaEnfermedad = enfermedadrepository.findByDianosticoContaining(dianostico);
  			return listaEnfermedad;
		}

		@Override
		public List<Enfermedad> findByNotasContaining(String notas){
			logger.info("Get allProyect");
 			List<Enfermedad> listaEnfermedad = new ArrayList<Enfermedad>();
			listaEnfermedad = enfermedadrepository.findByNotasContaining(notas);
  			return listaEnfermedad;
		}

		@Override
		public List<Enfermedad> findByCronicaContaining(Boolean cronica){
			logger.info("Get allProyect");
 			List<Enfermedad> listaEnfermedad = new ArrayList<Enfermedad>();
			listaEnfermedad = enfermedadrepository.findByCronicaContaining(cronica);
  			return listaEnfermedad;
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
