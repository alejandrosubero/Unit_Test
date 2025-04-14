
/*
Create on Sun Nov 07 14:17:04 ART 2021
*Copyright (C) 121.
@author Alejandro Subero
@author Subero Alejandro
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: This project tracking software </p>
*/

package com.pts.serviceImplement;

import com.pts.service.AssociatedProyectService;
import com.pts.repository.AssociatedProyectRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.pts.entitys.AssociatedProyect;

@Service
public class AssociatedProyectServiceImplement implements AssociatedProyectService {

	protected static final Log logger = LogFactory.getLog(AssociatedProyectServiceImplement.class);
	@Autowired
	private AssociatedProyectRepository associatedproyectrepository;

	@Override
	public AssociatedProyect findByIdProyect(Long idProyect) {

		logger.info("Starting getAssociatedProyect");
		AssociatedProyect associatedproyectEntity = new AssociatedProyect();
		Optional<AssociatedProyect> fileOptional1 = associatedproyectrepository.findByIdProyect(idProyect);

		if (fileOptional1.isPresent()) {

			try {
				associatedproyectEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return associatedproyectEntity;
	}

	@Override
	public AssociatedProyect findByIdReport(Long idReport) {

		logger.info("Starting getAssociatedProyect");
		AssociatedProyect associatedproyectEntity = new AssociatedProyect();
		Optional<AssociatedProyect> fileOptional1 = associatedproyectrepository.findByIdReport(idReport);

		if (fileOptional1.isPresent()) {

			try {
				associatedproyectEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return associatedproyectEntity;
	}

	@Override
	public List<AssociatedProyect> getAllAssociatedProyect() {
		logger.info("Get allProyect");
		List<AssociatedProyect> listaAssociatedProyect = new ArrayList<AssociatedProyect>();
		associatedproyectrepository.findAll()
				.forEach(associatedproyect -> listaAssociatedProyect.add(associatedproyect));
		return listaAssociatedProyect;
	}

	@Override
	public boolean saveAssociatedProyect(AssociatedProyect associatedproyect) {
		logger.info("Save Proyect");

		try {
			associatedproyectrepository.save(associatedproyect);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean deleteAssociatedProyect(Long id) {
		logger.info("Delete Proyect");
		boolean clave = false;

		try {
			associatedproyectrepository.deleteById(id);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}
		return clave;
	}

	@Override
	public AssociatedProyect findById(Long id) {
		return associatedproyectrepository.findById(id).get();
	}

	@Override
	public List<AssociatedProyect> findByIdProyectContaining(Long idproyect) {
		logger.info("Get allProyect");
		List<AssociatedProyect> listaAssociatedProyect = new ArrayList<AssociatedProyect>();
		listaAssociatedProyect = associatedproyectrepository.findByIdProyectContaining(idproyect);
		return listaAssociatedProyect;
	}

	@Override
	public List<AssociatedProyect> findByIdReportContaining(Long idreport) {
		logger.info("Get allProyect");
		List<AssociatedProyect> listaAssociatedProyect = new ArrayList<AssociatedProyect>();
		listaAssociatedProyect = associatedproyectrepository.findByIdReportContaining(idreport);
		return listaAssociatedProyect;
	}

	/*
	 * Copyright (C) 2008 Google Inc. Licensed to the Apache Software Foundation
	 * (ASF) under one or more contributor license agreements. See the NOTICE file
	 * distributed with this work for additional information regarding copyright
	 * ownership. The ASF licenses this file to You under the Apache License,
	 * Version 2.0 (the "License"); you may not use this file except in compliance
	 * with the License. You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
	 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
	 * License for the specific language governing permissions and limitations under
	 * the License.
	 */

}
