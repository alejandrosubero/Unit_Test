
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

import com.bpm.employee.entitys.FichaFeriadosTrabajados;
import com.bpm.employee.service.FichaFeriadosTrabajadosService;
import com.bpm.employee.repository.FichaFeriadosTrabajadosRepository;
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
public class FichaFeriadosTrabajadosServiceImplement implements FichaFeriadosTrabajadosService {

protected static final Log logger = LogFactory.getLog(FichaFeriadosTrabajadosServiceImplement.class);
@Autowired
private FichaFeriadosTrabajadosRepository fichaferiadostrabajadosrepository;

		@Override
		public FichaFeriadosTrabajados findByFecha(Date fecha){

		logger.info("Starting getFichaFeriadosTrabajados");
			FichaFeriadosTrabajados fichaferiadostrabajadosEntity = new FichaFeriadosTrabajados();
		Optional<FichaFeriadosTrabajados> fileOptional1 = fichaferiadostrabajadosrepository.findByFecha(fecha);

		if (fileOptional1.isPresent()) { 

				try {
			fichaferiadostrabajadosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichaferiadostrabajadosEntity;	}
		@Override
		public FichaFeriadosTrabajados findByHoras(Long horas){

		logger.info("Starting getFichaFeriadosTrabajados");
			FichaFeriadosTrabajados fichaferiadostrabajadosEntity = new FichaFeriadosTrabajados();
		Optional<FichaFeriadosTrabajados> fileOptional1 = fichaferiadostrabajadosrepository.findByHoras(horas);

		if (fileOptional1.isPresent()) { 

				try {
			fichaferiadostrabajadosEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichaferiadostrabajadosEntity;	}




		@Override
		public List<FichaFeriadosTrabajados> getAllFichaFeriadosTrabajados(){
		logger.info("Get allProyect");
			List<FichaFeriadosTrabajados> listaFichaFeriadosTrabajados = new ArrayList<FichaFeriadosTrabajados>();
				fichaferiadostrabajadosrepository.findAll().forEach(fichaferiadostrabajados -> listaFichaFeriadosTrabajados.add(fichaferiadostrabajados));
			return listaFichaFeriadosTrabajados;
}


		@Override
		public boolean saveFichaFeriadosTrabajados(FichaFeriadosTrabajados fichaferiadostrabajados){
		logger.info("Save Proyect");


				try {
				fichaferiadostrabajadosrepository.save(fichaferiadostrabajados);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaFeriadosTrabajados(FichaFeriadosTrabajados  fichaferiadostrabajados ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaFeriadosTrabajados empre = findById(fichaferiadostrabajados.getId());
			empre = fichaferiadostrabajados;

				try {
				fichaferiadostrabajadosrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaFeriadosTrabajados findById( Long id){
				return  fichaferiadostrabajadosrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaFeriadosTrabajados(FichaFeriadosTrabajados  fichaferiadostrabajados ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaFeriadosTrabajados> fileOptional2 = fichaferiadostrabajadosrepository.findById(fichaferiadostrabajados.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaFeriadosTrabajados(fichaferiadostrabajados);
				logger.info(" is update");
			} else {
					clave = this.saveFichaFeriadosTrabajados(fichaferiadostrabajados);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<FichaFeriadosTrabajados> findByFechaContaining(Date fecha){
			logger.info("Get allProyect");
 			List<FichaFeriadosTrabajados> listaFichaFeriadosTrabajados = new ArrayList<FichaFeriadosTrabajados>();
			listaFichaFeriadosTrabajados = fichaferiadostrabajadosrepository.findByFechaContaining(fecha);
  			return listaFichaFeriadosTrabajados;
		}

		@Override
		public List<FichaFeriadosTrabajados> findByHorasContaining(Long horas){
			logger.info("Get allProyect");
 			List<FichaFeriadosTrabajados> listaFichaFeriadosTrabajados = new ArrayList<FichaFeriadosTrabajados>();
			listaFichaFeriadosTrabajados = fichaferiadostrabajadosrepository.findByHorasContaining(horas);
  			return listaFichaFeriadosTrabajados;
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
