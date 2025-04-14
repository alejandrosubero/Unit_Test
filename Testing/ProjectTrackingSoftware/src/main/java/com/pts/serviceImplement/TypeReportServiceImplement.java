
package com.pts.serviceImplement;

/*
Create on Sun Nov 07 14:17:04 ART 2021
*Copyright (C) 121.
@author Alejandro Subero
@author Subero Alejandro
@author ANACODE 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: This project tracking software </p>
*/

import com.pts.service.TypeReportService;
import com.pts.repository.TypeReportRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.pts.entitys.TypeReport;

@Service
public class TypeReportServiceImplement implements TypeReportService {

	protected static final Log logger = LogFactory.getLog(TypeReportServiceImplement.class);
	@Autowired
	private TypeReportRepository typereportrepository;

	@Override
	public TypeReport findByType(String type) {

		logger.info("Starting getTypeReport");
		TypeReport typereportEntity = new TypeReport();
		Optional<TypeReport> fileOptional1 = typereportrepository.findByType(type);

		if (fileOptional1.isPresent()) {

			try {
				typereportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return typereportEntity;
	}

	@Override
	public TypeReport findByDescripton(String descripton) {

		logger.info("Starting getTypeReport");
		TypeReport typereportEntity = new TypeReport();
		Optional<TypeReport> fileOptional1 = typereportrepository.findByDescripton(descripton);

		if (fileOptional1.isPresent()) {

			try {
				typereportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return typereportEntity;
	}

	
	@Override
	public List<TypeReport> getAllTypeReport() {
		logger.info("Get allProyect");
		List<TypeReport> listaTypeReport = new ArrayList<TypeReport>();
		typereportrepository.findAll().forEach(typereport -> listaTypeReport.add(typereport));
		return listaTypeReport;
	}

	
	@Override
	public boolean saveTypeReport(TypeReport typereport) {
		logger.info("Save Proyect");

		try {
			typereportrepository.save(typereport);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean deleteTypeReport(Long id) {
		logger.info("Delete Proyect");
		boolean clave = false;

		try {
			typereportrepository.deleteById(id);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}
		return clave;
	}

	@Override
	public TypeReport findById(Long id) {
		return typereportrepository.findById(id).get();
	}

	@Override
	public List<TypeReport> findByTypeContaining(String type) {
		logger.info("Get allProyect");
		List<TypeReport> listaTypeReport = new ArrayList<TypeReport>();
		listaTypeReport = typereportrepository.findByTypeContaining(type);
		return listaTypeReport;
	}

	@Override
	public List<TypeReport> findByDescriptonContaining(String descripton) {
		logger.info("Get allProyect");
		List<TypeReport> listaTypeReport = new ArrayList<TypeReport>();
		listaTypeReport = typereportrepository.findByDescriptonContaining(descripton);
		return listaTypeReport;
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
