
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
import com.bpm.employee.entitys.FichaPagos;
import com.bpm.employee.service.FichaPagosService;
import com.bpm.employee.repository.FichaPagosRepository;
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
public class FichaPagosServiceImplement implements FichaPagosService {

protected static final Log logger = LogFactory.getLog(FichaPagosServiceImplement.class);
@Autowired
private FichaPagosRepository fichapagosrepository;

		@Override
		public FichaPagos findByFechaPago(Date fechaPago){

		logger.info("Starting getFichaPagos");
			FichaPagos fichapagosEntity = new FichaPagos();
		Optional<FichaPagos> fileOptional1 = fichapagosrepository.findByFechaPago(fechaPago);

		if (fileOptional1.isPresent()) { 

				try {
			fichapagosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapagosEntity;	}
		@Override
		public FichaPagos findByPeriodoPagado(String periodoPagado){

		logger.info("Starting getFichaPagos");
			FichaPagos fichapagosEntity = new FichaPagos();
		Optional<FichaPagos> fileOptional1 = fichapagosrepository.findByPeriodoPagado(periodoPagado);

		if (fileOptional1.isPresent()) { 

				try {
			fichapagosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapagosEntity;	}
		@Override
		public FichaPagos findByDiasTrabajados(Long diasTrabajados){

		logger.info("Starting getFichaPagos");
			FichaPagos fichapagosEntity = new FichaPagos();
		Optional<FichaPagos> fileOptional1 = fichapagosrepository.findByDiasTrabajados(diasTrabajados);

		if (fileOptional1.isPresent()) { 

				try {
			fichapagosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapagosEntity;	}
		@Override
		public FichaPagos findByHorasTrabajadas(Double horasTrabajadas){

		logger.info("Starting getFichaPagos");
			FichaPagos fichapagosEntity = new FichaPagos();
		Optional<FichaPagos> fileOptional1 = fichapagosrepository.findByHorasTrabajadas(horasTrabajadas);

		if (fileOptional1.isPresent()) { 

				try {
			fichapagosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapagosEntity;	}
		@Override
		public FichaPagos findByCantidadPagada(Long cantidadPagada){

		logger.info("Starting getFichaPagos");
			FichaPagos fichapagosEntity = new FichaPagos();
		Optional<FichaPagos> fileOptional1 = fichapagosrepository.findByCantidadPagada(cantidadPagada);

		if (fileOptional1.isPresent()) { 

				try {
			fichapagosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapagosEntity;	}




		@Override
		public List<FichaPagos> getAllFichaPagos(){
		logger.info("Get allProyect");
			List<FichaPagos> listaFichaPagos = new ArrayList<FichaPagos>();
				fichapagosrepository.findAll().forEach(fichapagos -> listaFichaPagos.add(fichapagos));
			return listaFichaPagos;
}


		@Override
		public boolean saveFichaPagos(FichaPagos fichapagos){
		logger.info("Save Proyect");


				try {
				fichapagosrepository.save(fichapagos);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaPagos(FichaPagos  fichapagos ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaPagos empre = findById(fichapagos.getId());
			empre = fichapagos;

				try {
				fichapagosrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaPagos findById( Long id){
				return  fichapagosrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaPagos(FichaPagos  fichapagos ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaPagos> fileOptional2 = fichapagosrepository.findById(fichapagos.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaPagos(fichapagos);
				logger.info(" is update");
			} else {
					clave = this.saveFichaPagos(fichapagos);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<FichaPagos> findByFechaPagoContaining(Date fechapago){
			logger.info("Get allProyect");
 			List<FichaPagos> listaFichaPagos = new ArrayList<FichaPagos>();
			listaFichaPagos = fichapagosrepository.findByFechaPagoContaining(fechapago);
  			return listaFichaPagos;
		}

		@Override
		public List<FichaPagos> findByPeriodoPagadoContaining(String periodopagado){
			logger.info("Get allProyect");
 			List<FichaPagos> listaFichaPagos = new ArrayList<FichaPagos>();
			listaFichaPagos = fichapagosrepository.findByPeriodoPagadoContaining(periodopagado);
  			return listaFichaPagos;
		}

		@Override
		public List<FichaPagos> findByDiasTrabajadosContaining(Long diastrabajados){
			logger.info("Get allProyect");
 			List<FichaPagos> listaFichaPagos = new ArrayList<FichaPagos>();
			listaFichaPagos = fichapagosrepository.findByDiasTrabajadosContaining(diastrabajados);
  			return listaFichaPagos;
		}

		@Override
		public List<FichaPagos> findByHorasTrabajadasContaining(Double horastrabajadas){
			logger.info("Get allProyect");
 			List<FichaPagos> listaFichaPagos = new ArrayList<FichaPagos>();
			listaFichaPagos = fichapagosrepository.findByHorasTrabajadasContaining(horastrabajadas);
  			return listaFichaPagos;
		}

		@Override
		public List<FichaPagos> findByCantidadPagadaContaining(Long cantidadpagada){
			logger.info("Get allProyect");
 			List<FichaPagos> listaFichaPagos = new ArrayList<FichaPagos>();
			listaFichaPagos = fichapagosrepository.findByCantidadPagadaContaining(cantidadpagada);
  			return listaFichaPagos;
		}



			@Override
			public List<FichaPagos> findByRelacionDeduccion(Deduccion deduccion){
				List<FichaPagos> listaFichaPagos = new ArrayList<FichaPagos>();
				for (FichaPagos fichapagos : this.getAllFichaPagos()) {
					if(fichapagos.getdeducciones().equalsDeduccion(deduccion)){
						listaFichaPagos.add(fichapagos);
					}
				}
				return listaFichaPagos;
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
