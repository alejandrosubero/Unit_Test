
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

import com.bpm.employee.entitys.FichaDesempeno;
import com.bpm.employee.service.FichaDesempenoService;
import com.bpm.employee.repository.FichaDesempenoRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class FichaDesempenoServiceImplement implements FichaDesempenoService {

protected static final Log logger = LogFactory.getLog(FichaDesempenoServiceImplement.class);
@Autowired
private FichaDesempenoRepository fichadesempenorepository;





		@Override
		public List<FichaDesempeno> getAllFichaDesempeno(){
		logger.info("Get allProyect");
			List<FichaDesempeno> listaFichaDesempeno = new ArrayList<FichaDesempeno>();
				fichadesempenorepository.findAll().forEach(fichadesempeno -> listaFichaDesempeno.add(fichadesempeno));
			return listaFichaDesempeno;
}


		@Override
		public boolean saveFichaDesempeno(FichaDesempeno fichadesempeno){
		logger.info("Save Proyect");


				try {
				fichadesempenorepository.save(fichadesempeno);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaDesempeno(FichaDesempeno  fichadesempeno ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaDesempeno empre = findById(fichadesempeno.getId());
			empre = fichadesempeno;

				try {
				fichadesempenorepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaDesempeno findById( Long id){
				return  fichadesempenorepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaDesempeno(FichaDesempeno  fichadesempeno ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaDesempeno> fileOptional2 = fichadesempenorepository.findById(fichadesempeno.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaDesempeno(fichadesempeno);
				logger.info(" is update");
			} else {
					clave = this.saveFichaDesempeno(fichadesempeno);
					logger.info(" is save");
 				}
 		return clave;
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
