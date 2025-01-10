
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

import com.bpm.employee.entitys.FichaPermisos;
import com.bpm.employee.service.FichaPermisosService;
import com.bpm.employee.repository.FichaPermisosRepository;
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
public class FichaPermisosServiceImplement implements FichaPermisosService {

protected static final Log logger = LogFactory.getLog(FichaPermisosServiceImplement.class);
@Autowired
private FichaPermisosRepository fichapermisosrepository;

		@Override
		public FichaPermisos findByFechaSalida(Date fechaSalida){

		logger.info("Starting getFichaPermisos");
			FichaPermisos fichapermisosEntity = new FichaPermisos();
		Optional<FichaPermisos> fileOptional1 = fichapermisosrepository.findByFechaSalida(fechaSalida);

		if (fileOptional1.isPresent()) { 

				try {
			fichapermisosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapermisosEntity;	}
		@Override
		public FichaPermisos findByFechaRetorno(Date fechaRetorno){

		logger.info("Starting getFichaPermisos");
			FichaPermisos fichapermisosEntity = new FichaPermisos();
		Optional<FichaPermisos> fileOptional1 = fichapermisosrepository.findByFechaRetorno(fechaRetorno);

		if (fileOptional1.isPresent()) { 

				try {
			fichapermisosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapermisosEntity;	}
		@Override
		public FichaPermisos findByHoraSalida(Date horaSalida){

		logger.info("Starting getFichaPermisos");
			FichaPermisos fichapermisosEntity = new FichaPermisos();
		Optional<FichaPermisos> fileOptional1 = fichapermisosrepository.findByHoraSalida(horaSalida);

		if (fileOptional1.isPresent()) { 

				try {
			fichapermisosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapermisosEntity;	}
		@Override
		public FichaPermisos findByHoraRetorno(Date horaRetorno){

		logger.info("Starting getFichaPermisos");
			FichaPermisos fichapermisosEntity = new FichaPermisos();
		Optional<FichaPermisos> fileOptional1 = fichapermisosrepository.findByHoraRetorno(horaRetorno);

		if (fileOptional1.isPresent()) { 

				try {
			fichapermisosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapermisosEntity;	}
		@Override
		public FichaPermisos findByTipoPermiso(String tipoPermiso){

		logger.info("Starting getFichaPermisos");
			FichaPermisos fichapermisosEntity = new FichaPermisos();
		Optional<FichaPermisos> fileOptional1 = fichapermisosrepository.findByTipoPermiso(tipoPermiso);

		if (fileOptional1.isPresent()) { 

				try {
			fichapermisosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapermisosEntity;	}
		@Override
		public FichaPermisos findByMotivo(String motivo){

		logger.info("Starting getFichaPermisos");
			FichaPermisos fichapermisosEntity = new FichaPermisos();
		Optional<FichaPermisos> fileOptional1 = fichapermisosrepository.findByMotivo(motivo);

		if (fileOptional1.isPresent()) { 

				try {
			fichapermisosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichapermisosEntity;	}




		@Override
		public List<FichaPermisos> getAllFichaPermisos(){
		logger.info("Get allProyect");
			List<FichaPermisos> listaFichaPermisos = new ArrayList<FichaPermisos>();
				fichapermisosrepository.findAll().forEach(fichapermisos -> listaFichaPermisos.add(fichapermisos));
			return listaFichaPermisos;
}


		@Override
		public boolean saveFichaPermisos(FichaPermisos fichapermisos){
		logger.info("Save Proyect");


				try {
				fichapermisosrepository.save(fichapermisos);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaPermisos(FichaPermisos  fichapermisos ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaPermisos empre = findById(fichapermisos.getId());
			empre = fichapermisos;

				try {
				fichapermisosrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaPermisos findById( Long id){
				return  fichapermisosrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaPermisos(FichaPermisos  fichapermisos ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaPermisos> fileOptional2 = fichapermisosrepository.findById(fichapermisos.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaPermisos(fichapermisos);
				logger.info(" is update");
			} else {
					clave = this.saveFichaPermisos(fichapermisos);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<FichaPermisos> findByFechaSalidaContaining(Date fechasalida){
			logger.info("Get allProyect");
 			List<FichaPermisos> listaFichaPermisos = new ArrayList<FichaPermisos>();
			listaFichaPermisos = fichapermisosrepository.findByFechaSalidaContaining(fechasalida);
  			return listaFichaPermisos;
		}

		@Override
		public List<FichaPermisos> findByFechaRetornoContaining(Date fecharetorno){
			logger.info("Get allProyect");
 			List<FichaPermisos> listaFichaPermisos = new ArrayList<FichaPermisos>();
			listaFichaPermisos = fichapermisosrepository.findByFechaRetornoContaining(fecharetorno);
  			return listaFichaPermisos;
		}

		@Override
		public List<FichaPermisos> findByHoraSalidaContaining(Date horasalida){
			logger.info("Get allProyect");
 			List<FichaPermisos> listaFichaPermisos = new ArrayList<FichaPermisos>();
			listaFichaPermisos = fichapermisosrepository.findByHoraSalidaContaining(horasalida);
  			return listaFichaPermisos;
		}

		@Override
		public List<FichaPermisos> findByHoraRetornoContaining(Date horaretorno){
			logger.info("Get allProyect");
 			List<FichaPermisos> listaFichaPermisos = new ArrayList<FichaPermisos>();
			listaFichaPermisos = fichapermisosrepository.findByHoraRetornoContaining(horaretorno);
  			return listaFichaPermisos;
		}

		@Override
		public List<FichaPermisos> findByTipoPermisoContaining(String tipopermiso){
			logger.info("Get allProyect");
 			List<FichaPermisos> listaFichaPermisos = new ArrayList<FichaPermisos>();
			listaFichaPermisos = fichapermisosrepository.findByTipoPermisoContaining(tipopermiso);
  			return listaFichaPermisos;
		}

		@Override
		public List<FichaPermisos> findByMotivoContaining(String motivo){
			logger.info("Get allProyect");
 			List<FichaPermisos> listaFichaPermisos = new ArrayList<FichaPermisos>();
			listaFichaPermisos = fichapermisosrepository.findByMotivoContaining(motivo);
  			return listaFichaPermisos;
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
