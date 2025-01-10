
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

import com.bpm.employee.entitys.Alergia;
import com.bpm.employee.service.AlergiaService;
import com.bpm.employee.repository.AlergiaRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class AlergiaServiceImplement implements AlergiaService {

protected static final Log logger = LogFactory.getLog(AlergiaServiceImplement.class);
@Autowired
private AlergiaRepository alergiarepository;

		@Override
		public Alergia findByTipo(String tipo){

		logger.info("Starting getAlergia");
			Alergia alergiaEntity = new Alergia();
		Optional<Alergia> fileOptional1 = alergiarepository.findByTipo(tipo);

		if (fileOptional1.isPresent()) { 

				try {
			alergiaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return alergiaEntity;	}
		@Override
		public Alergia findByCausal(String causal){

		logger.info("Starting getAlergia");
			Alergia alergiaEntity = new Alergia();
		Optional<Alergia> fileOptional1 = alergiarepository.findByCausal(causal);

		if (fileOptional1.isPresent()) { 

				try {
			alergiaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return alergiaEntity;	}
		@Override
		public Alergia findByMedicamento(String medicamento){

		logger.info("Starting getAlergia");
			Alergia alergiaEntity = new Alergia();
		Optional<Alergia> fileOptional1 = alergiarepository.findByMedicamento(medicamento);

		if (fileOptional1.isPresent()) { 

				try {
			alergiaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return alergiaEntity;	}




		@Override
		public List<Alergia> getAllAlergia(){
		logger.info("Get allProyect");
			List<Alergia> listaAlergia = new ArrayList<Alergia>();
				alergiarepository.findAll().forEach(alergia -> listaAlergia.add(alergia));
			return listaAlergia;
}


		@Override
		public boolean saveAlergia(Alergia alergia){
		logger.info("Save Proyect");


				try {
				alergiarepository.save(alergia);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateAlergia(Alergia  alergia ){
			logger.info("Update Proyect");
			boolean clave = false;
		Alergia empre = findById(alergia.getId());
			empre = alergia;

				try {
				alergiarepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public Alergia findById( Long id){
				return  alergiarepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateAlergia(Alergia  alergia ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<Alergia> fileOptional2 = alergiarepository.findById(alergia.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateAlergia(alergia);
				logger.info(" is update");
			} else {
					clave = this.saveAlergia(alergia);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<Alergia> findByTipoContaining(String tipo){
			logger.info("Get allProyect");
 			List<Alergia> listaAlergia = new ArrayList<Alergia>();
			listaAlergia = alergiarepository.findByTipoContaining(tipo);
  			return listaAlergia;
		}

		@Override
		public List<Alergia> findByCausalContaining(String causal){
			logger.info("Get allProyect");
 			List<Alergia> listaAlergia = new ArrayList<Alergia>();
			listaAlergia = alergiarepository.findByCausalContaining(causal);
  			return listaAlergia;
		}

		@Override
		public List<Alergia> findByMedicamentoContaining(String medicamento){
			logger.info("Get allProyect");
 			List<Alergia> listaAlergia = new ArrayList<Alergia>();
			listaAlergia = alergiarepository.findByMedicamentoContaining(medicamento);
  			return listaAlergia;
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
