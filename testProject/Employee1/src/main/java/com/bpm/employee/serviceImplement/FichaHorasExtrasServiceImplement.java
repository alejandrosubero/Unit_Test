
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

import com.bpm.employee.entitys.FichaHorasExtras;
import com.bpm.employee.service.FichaHorasExtrasService;
import com.bpm.employee.repository.FichaHorasExtrasRepository;
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
public class FichaHorasExtrasServiceImplement implements FichaHorasExtrasService {

protected static final Log logger = LogFactory.getLog(FichaHorasExtrasServiceImplement.class);
@Autowired
private FichaHorasExtrasRepository fichahorasextrasrepository;

		@Override
		public FichaHorasExtras findByFecha(Date fecha){

		logger.info("Starting getFichaHorasExtras");
			FichaHorasExtras fichahorasextrasEntity = new FichaHorasExtras();
		Optional<FichaHorasExtras> fileOptional1 = fichahorasextrasrepository.findByFecha(fecha);

		if (fileOptional1.isPresent()) { 

				try {
			fichahorasextrasEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichahorasextrasEntity;	}
		@Override
		public FichaHorasExtras findByHoras(Long horas){

		logger.info("Starting getFichaHorasExtras");
			FichaHorasExtras fichahorasextrasEntity = new FichaHorasExtras();
		Optional<FichaHorasExtras> fileOptional1 = fichahorasextrasrepository.findByHoras(horas);

		if (fileOptional1.isPresent()) { 

				try {
			fichahorasextrasEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichahorasextrasEntity;	}




		@Override
		public List<FichaHorasExtras> getAllFichaHorasExtras(){
		logger.info("Get allProyect");
			List<FichaHorasExtras> listaFichaHorasExtras = new ArrayList<FichaHorasExtras>();
				fichahorasextrasrepository.findAll().forEach(fichahorasextras -> listaFichaHorasExtras.add(fichahorasextras));
			return listaFichaHorasExtras;
}


		@Override
		public boolean saveFichaHorasExtras(FichaHorasExtras fichahorasextras){
		logger.info("Save Proyect");


				try {
				fichahorasextrasrepository.save(fichahorasextras);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaHorasExtras(FichaHorasExtras  fichahorasextras ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaHorasExtras empre = findById(fichahorasextras.getId());
			empre = fichahorasextras;

				try {
				fichahorasextrasrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaHorasExtras findById( Long id){
				return  fichahorasextrasrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaHorasExtras(FichaHorasExtras  fichahorasextras ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaHorasExtras> fileOptional2 = fichahorasextrasrepository.findById(fichahorasextras.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaHorasExtras(fichahorasextras);
				logger.info(" is update");
			} else {
					clave = this.saveFichaHorasExtras(fichahorasextras);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<FichaHorasExtras> findByFechaContaining(Date fecha){
			logger.info("Get allProyect");
 			List<FichaHorasExtras> listaFichaHorasExtras = new ArrayList<FichaHorasExtras>();
			listaFichaHorasExtras = fichahorasextrasrepository.findByFechaContaining(fecha);
  			return listaFichaHorasExtras;
		}

		@Override
		public List<FichaHorasExtras> findByHorasContaining(Long horas){
			logger.info("Get allProyect");
 			List<FichaHorasExtras> listaFichaHorasExtras = new ArrayList<FichaHorasExtras>();
			listaFichaHorasExtras = fichahorasextrasrepository.findByHorasContaining(horas);
  			return listaFichaHorasExtras;
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
