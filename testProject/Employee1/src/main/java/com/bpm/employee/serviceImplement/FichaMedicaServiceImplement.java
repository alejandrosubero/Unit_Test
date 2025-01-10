
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
import com.bpm.employee.entitys.Enfermedad;
import com.bpm.employee.entitys.FichaMedica;
import com.bpm.employee.service.FichaMedicaService;
import com.bpm.employee.repository.FichaMedicaRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class FichaMedicaServiceImplement implements FichaMedicaService {

protected static final Log logger = LogFactory.getLog(FichaMedicaServiceImplement.class);
@Autowired
private FichaMedicaRepository fichamedicarepository;

		@Override
		public FichaMedica findByPeso(Long peso){

		logger.info("Starting getFichaMedica");
			FichaMedica fichamedicaEntity = new FichaMedica();
		Optional<FichaMedica> fileOptional1 = fichamedicarepository.findByPeso(peso);

		if (fileOptional1.isPresent()) { 

				try {
			fichamedicaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichamedicaEntity;	}
		@Override
		public FichaMedica findByAltura(Long altura){

		logger.info("Starting getFichaMedica");
			FichaMedica fichamedicaEntity = new FichaMedica();
		Optional<FichaMedica> fileOptional1 = fichamedicarepository.findByAltura(altura);

		if (fileOptional1.isPresent()) { 

				try {
			fichamedicaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichamedicaEntity;	}
		@Override
		public FichaMedica findByGenero(String genero){

		logger.info("Starting getFichaMedica");
			FichaMedica fichamedicaEntity = new FichaMedica();
		Optional<FichaMedica> fileOptional1 = fichamedicarepository.findByGenero(genero);

		if (fileOptional1.isPresent()) { 

				try {
			fichamedicaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichamedicaEntity;	}
		@Override
		public FichaMedica findByEtnia(String etnia){

		logger.info("Starting getFichaMedica");
			FichaMedica fichamedicaEntity = new FichaMedica();
		Optional<FichaMedica> fileOptional1 = fichamedicarepository.findByEtnia(etnia);

		if (fileOptional1.isPresent()) { 

				try {
			fichamedicaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichamedicaEntity;	}
		@Override
		public FichaMedica findByEdad(Long edad){

		logger.info("Starting getFichaMedica");
			FichaMedica fichamedicaEntity = new FichaMedica();
		Optional<FichaMedica> fileOptional1 = fichamedicarepository.findByEdad(edad);

		if (fileOptional1.isPresent()) { 

				try {
			fichamedicaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichamedicaEntity;	}
		@Override
		public FichaMedica findBySeguroMedicoComp(String seguroMedicoComp){

		logger.info("Starting getFichaMedica");
			FichaMedica fichamedicaEntity = new FichaMedica();
		Optional<FichaMedica> fileOptional1 = fichamedicarepository.findBySeguroMedicoComp(seguroMedicoComp);

		if (fileOptional1.isPresent()) { 

				try {
			fichamedicaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichamedicaEntity;	}
		@Override
		public FichaMedica findBySeguroMedicoPersonal(String seguroMedicoPersonal){

		logger.info("Starting getFichaMedica");
			FichaMedica fichamedicaEntity = new FichaMedica();
		Optional<FichaMedica> fileOptional1 = fichamedicarepository.findBySeguroMedicoPersonal(seguroMedicoPersonal);

		if (fileOptional1.isPresent()) { 

				try {
			fichamedicaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return fichamedicaEntity;	}




		@Override
		public List<FichaMedica> getAllFichaMedica(){
		logger.info("Get allProyect");
			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
				fichamedicarepository.findAll().forEach(fichamedica -> listaFichaMedica.add(fichamedica));
			return listaFichaMedica;
}


		@Override
		public boolean saveFichaMedica(FichaMedica fichamedica){
		logger.info("Save Proyect");


				try {
				fichamedicarepository.save(fichamedica);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateFichaMedica(FichaMedica  fichamedica ){
			logger.info("Update Proyect");
			boolean clave = false;
		FichaMedica empre = findById(fichamedica.getId());
			empre = fichamedica;

				try {
				fichamedicarepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public FichaMedica findById( Long id){
				return  fichamedicarepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateFichaMedica(FichaMedica  fichamedica ){
			logger.info("Update Proyect");
			boolean clave = false;
			Optional<FichaMedica> fileOptional2 = fichamedicarepository.findById(fichamedica.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateFichaMedica(fichamedica);
				logger.info(" is update");
			} else {
					clave = this.saveFichaMedica(fichamedica);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<FichaMedica> findByPesoContaining(Long peso){
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			listaFichaMedica = fichamedicarepository.findByPesoContaining(peso);
  			return listaFichaMedica;
		}

		@Override
		public List<FichaMedica> findByAlturaContaining(Long altura){
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			listaFichaMedica = fichamedicarepository.findByAlturaContaining(altura);
  			return listaFichaMedica;
		}

		@Override
		public List<FichaMedica> findByGeneroContaining(String genero){
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			listaFichaMedica = fichamedicarepository.findByGeneroContaining(genero);
  			return listaFichaMedica;
		}

		@Override
		public List<FichaMedica> findByEtniaContaining(String etnia){
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			listaFichaMedica = fichamedicarepository.findByEtniaContaining(etnia);
  			return listaFichaMedica;
		}

		@Override
		public List<FichaMedica> findByEdadContaining(Long edad){
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			listaFichaMedica = fichamedicarepository.findByEdadContaining(edad);
  			return listaFichaMedica;
		}

		@Override
		public List<FichaMedica> findBySeguroMedicoCompContaining(String seguromedicocomp){
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			listaFichaMedica = fichamedicarepository.findBySeguroMedicoCompContaining(seguromedicocomp);
  			return listaFichaMedica;
		}

		@Override
		public List<FichaMedica> findBySeguroMedicoPersonalContaining(String seguromedicopersonal){
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			listaFichaMedica = fichamedicarepository.findBySeguroMedicoPersonalContaining(seguromedicopersonal);
  			return listaFichaMedica;
		}




		@Override
		public List<FichaMedica> findByAlergiaContaining(Alergia alergias){
			logger.info("metodo: metodContainingRelacion NEW ");
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			for (FichaMedica fichamedica : this.getAllFichaMedica()) {
				for (Alergia alergiasx : fichamedica.getalergias()) { 
						if(alergiasx.equalsAlergia(alergias)) {
						listaFichaMedica.add(fichamedica);	
				}
	  	 	}
		}
			return listaFichaMedica;	

	}


		@Override
		public List<FichaMedica> findByEnfermedadContaining(Enfermedad enfermedades){
			logger.info("metodo: metodContainingRelacion NEW ");
			logger.info("Get allProyect");
 			List<FichaMedica> listaFichaMedica = new ArrayList<FichaMedica>();
			for (FichaMedica fichamedica : this.getAllFichaMedica()) {
				for (Enfermedad enfermedadesx : fichamedica.getenfermedades()) { 
						if(enfermedadesx.equalsEnfermedad(enfermedades)) {
						listaFichaMedica.add(fichamedica);	
				}
	  	 	}
		}
			return listaFichaMedica;	

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
