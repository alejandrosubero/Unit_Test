
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

import com.bpm.employee.entitys.FichaSueldo;
import com.bpm.employee.service.FichaSueldoService;
import com.bpm.employee.repository.FichaSueldoRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class FichaSueldoServiceImplement implements FichaSueldoService {

protected static final Log logger = LogFactory.getLog(FichaSueldoServiceImplement.class);
@Autowired
private FichaSueldoRepository fichasueldorepository;

		@Override
		public FichaSueldo findByTasaHoraFeriado(Long tasaHoraFeriado){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByTasaHoraFeriado(tasaHoraFeriado);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findByTasaHora(Long tasaHora){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByTasaHora(tasaHora);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findByTasaDia(Long tasaDia){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByTasaDia(tasaDia);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findByHorasJornadaDia(Long horasJornadaDia){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByHorasJornadaDia(horasJornadaDia);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findByTipoDePago(String tipoDePago){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByTipoDePago(tipoDePago);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findByJornadasemanalDias(Long jornadasemanalDias){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByJornadasemanalDias(jornadasemanalDias);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findBySueldoIntegral(Long sueldoIntegral){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findBySueldoIntegral(sueldoIntegral);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findByTotalAnual(Long totalAnual){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByTotalAnual(totalAnual);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}
		@Override
		public FichaSueldo findByTasaHoraExtra(Long tasaHoraExtra){

		logger.info("Starting getFichaSueldo");
			FichaSueldo fichasueldoEntity = new FichaSueldo();
		Optional<FichaSueldo> fileOptional1 = fichasueldorepository.findByTasaHoraExtra(tasaHoraExtra);

		if (fileOptional1.isPresent()) { 

				try {
			fichasueldoEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichasueldoEntity;	}




		@Override
		public List<FichaSueldo> getAllFichaSueldo(){
		logger.info("Get allProyect");
			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
				fichasueldorepository.findAll().forEach(fichasueldo -> listaFichaSueldo.add(fichasueldo));
			return listaFichaSueldo;
}


		@Override
		public boolean saveFichaSueldo(FichaSueldo fichasueldo){
		logger.info("Save Proyect");


				try {
				fichasueldorepository.save(fichasueldo);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaSueldo(FichaSueldo  fichasueldo ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaSueldo empre = findById(fichasueldo.getId());
			empre = fichasueldo;

				try {
				fichasueldorepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaSueldo findById( Long id){
				return  fichasueldorepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaSueldo(FichaSueldo  fichasueldo ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaSueldo> fileOptional2 = fichasueldorepository.findById(fichasueldo.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaSueldo(fichasueldo);
				logger.info(" is update");
			} else {
					clave = this.saveFichaSueldo(fichasueldo);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<FichaSueldo> findByTasaHoraFeriadoContaining(Long tasahoraferiado){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByTasaHoraFeriadoContaining(tasahoraferiado);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findByTasaHoraContaining(Long tasahora){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByTasaHoraContaining(tasahora);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findByTasaDiaContaining(Long tasadia){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByTasaDiaContaining(tasadia);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findByHorasJornadaDiaContaining(Long horasjornadadia){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByHorasJornadaDiaContaining(horasjornadadia);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findByTipoDePagoContaining(String tipodepago){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByTipoDePagoContaining(tipodepago);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findByJornadasemanalDiasContaining(Long jornadasemanaldias){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByJornadasemanalDiasContaining(jornadasemanaldias);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findBySueldoIntegralContaining(Long sueldointegral){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findBySueldoIntegralContaining(sueldointegral);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findByTotalAnualContaining(Long totalanual){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByTotalAnualContaining(totalanual);
  			return listaFichaSueldo;
		}

		@Override
		public List<FichaSueldo> findByTasaHoraExtraContaining(Long tasahoraextra){
			logger.info("Get allProyect");
 			List<FichaSueldo> listaFichaSueldo = new ArrayList<FichaSueldo>();
			listaFichaSueldo = fichasueldorepository.findByTasaHoraExtraContaining(tasahoraextra);
  			return listaFichaSueldo;
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
