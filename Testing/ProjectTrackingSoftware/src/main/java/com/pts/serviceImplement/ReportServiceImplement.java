
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.pts.entitys.AssociatedProyect;
import com.pts.entitys.Report;
import com.pts.entitys.TimeAssigned;
import com.pts.entitys.TimeReport;
import com.pts.entitys.TypeReport;
import com.pts.mapper.ReportMapper;
import com.pts.pojo.ReportPojo;
import com.pts.repository.ReportRepository;
import com.pts.service.CodigoReportSecuenceService;
import com.pts.service.ReportService;
import com.pts.service.SequencesReportService;

@Service
public class ReportServiceImplement implements ReportService {

	protected static final Log logger = LogFactory.getLog(ReportServiceImplement.class);

	 @Value("${codeSecuence}")
	 private String codeSecuence;
	
	
	@Autowired
	private ReportRepository reportrepository;

	
	@Autowired
	private CodigoReportSecuenceService codigoReportSecuenceService;

	
	@Autowired
	private SequencesReportService sequencesReportService;
	
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Override
	public List<Report> getAllReport() {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		reportrepository.findAll().forEach(report -> listaReport.add(report));
		return listaReport;
	}
	
	
	@Override
	public boolean saveReport(Report report) {
		logger.info("Save report...");
		try { 
			// set code of report
			Long idReport = sequencesReportService.getSecuence(codeSecuence);
			report.setid(idReport);
			report.setCommitmentDate(new Date());
			reportrepository.save(report);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	
	@Override	
	public ReportPojo saveNewReport(ReportPojo report) {
		logger.info("Save New Report...");
		
		try {
			Long idReport = sequencesReportService.getSecuence(codeSecuence);
			report.setid(idReport);
			
			
			if (report.getComentarios() != null && report.getComentarios().size() > 0) {
				report.getComentarios().stream().forEach(comments -> comments.setIdReport(idReport));
			}
			
			if (report.getAssociatedProyects() != null && report.getAssociatedProyects().size() > 0) {
				report.getAssociatedProyects().stream().forEach(associate -> associate.setIdreport(idReport));
			}
			
			report.getTimes().stream().forEach(time ->time.setIdReport(idReport));
			
			report.getAssigmeds().stream().forEach(assigmeds -> {
				assigmeds.setIdReport(idReport);
				assigmeds.setStateReport(report.getStateReport());
			});
			
			reportrepository.save(reportMapper.pojoToEntity(report));
			
//			Long idReport = sequencesReportService.getSecuence(codeSecuence);
//			report.setid(idReport);
//			report.setStartDate(new Date());
//			report.setCommitmentDate(new Date());
//			report.setStateReport("NOT TREATED");
//			report.setState("NOT TREATED");
//			report.setLinkAcceso("link");
//			
//			reportrepository.save(reportMapper.pojoToEntity(report));
//			
//			ReportPojo re = reportMapper.entityToPojo(reportrepository.findById(idReport).get());
//			
//			
//			System.out.println(report);
//			System.out.println(re);
//			
//			
//						
//			re.getTimes().stream().forEach(time ->time.setIdReport(idReport));
//			
//			re.getAssigmeds().stream().forEach(assigmeds -> {
//				assigmeds.setIdreport(idReport);
//				assigmeds.setStatereport(report.getStateReport());
//			});
//			
//			String codigo = codigoReportSecuenceService.generateNewCodigoReport(report.getTypeReport().getTypeCode());
//			System.out.println(codigo);
//			//re.putInCodeReport(codigo);
			
			return reportMapper.entityToPojo(reportrepository.findById(idReport).get());
			
			
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return null;
		}
	}
	
	
	@Override
	public Report findByUserCode(String userCode) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByUserCode(userCode);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	//TODO: TEST IF WORCK
	private List<TimeAssigned> updateAssignedList(List<TimeAssigned> assigmeds){	
		assigmeds.stream().forEach(assigmed-> {
					if (assigmed.getIdtimeassigned() == null) {
						assigmeds.stream().forEach(as -> as.setStateassigned("no assignment"));
					}
				});
		return assigmeds;
	}
	
	
	
	@Override
	public Report findByUserAssigned(String userAssigned) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByUserAssigned(userAssigned);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	@Override
	public Report findByClient(String client) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByClient(client);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	@Override
	public Report findByCodeReports(String codeReports) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByCodeReports(codeReports);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	
	@Override
	public Report findByTitle(String title) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByTitle(title);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	@Override
	public Report findByDescription(String description) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByDescription(description);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	@Override
	public Report findByComentarios(Byte comentarios) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByComentarios(comentarios);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	@Override
	public Report findByState(String state) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByState(state);
		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	@Override
	public Report findByStateReport(String stateReport) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByStateReport(stateReport);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	@Override
	public Report findByTimeDedicate(Long timeDedicate) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByTimeDedicate(timeDedicate);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	@Override
	public Report findByStartDate(Date startDate) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByStartDate(startDate);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	
	@Override
	public Report findByCommitmentDate(Date commitmentDate) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByCommitmentDate(commitmentDate);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	@Override
	public Report findByPriority(String priority) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByPriority(priority);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

	
	@Override
	public Report findByLinkAcceso(String linkAcceso) {

		logger.info("Starting getReport");
		Report reportEntity = new Report();
		Optional<Report> fileOptional1 = reportrepository.findByLinkAcceso(linkAcceso);

		if (fileOptional1.isPresent()) {
			try {
				reportEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return reportEntity;
	}

		
	@Override
	public boolean deleteReport(Long id) {
		logger.info("Delete Proyect");
		boolean clave = false;
		try {
			reportrepository.deleteById(id);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}
		return clave;
	}

	
	@Override
	public Report findById(Long id) {
		return reportrepository.findById(id).get();
	}

	
	
	@Override
	public List<Report> findByUserCodeContaining(String usercode) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByUserCodeContaining(usercode);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByUserAssignedContaining(String userassigned) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByUserAssignedContaining(userassigned);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByClientContaining(String client) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByClientContaining(client);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByCodeReportsContaining(String codereports) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByCodeReportsContaining(codereports);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByTitleContaining(String title) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByTitleContaining(title);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByDescriptionContaining(String description) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByDescriptionContaining(description);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByComentariosContaining(Byte comentarios) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByComentariosContaining(comentarios);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByStateContaining(String state) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByStateContaining(state);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByStateReportContaining(String statereport) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByStateReportContaining(statereport);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByTimeDedicateContaining(Long timededicate) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByTimeDedicateContaining(timededicate);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByStartDateContaining(Date startdate) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByStartDateContaining(startdate);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByCommitmentDateContaining(Date commitmentdate) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByCommitmentDateContaining(commitmentdate);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByPriorityContaining(String priority) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByPriorityContaining(priority);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByLinkAccesoContaining(String linkacceso) {
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		listaReport = reportrepository.findByLinkAccesoContaining(linkacceso);
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByRelacionTypeReport(TypeReport typereport) {
		List<Report> listaReport = new ArrayList<Report>();
		
		for (Report report : this.getAllReport()) {
			if (report.getTypeReport().equals(typereport)) {
				listaReport.add(report);
			}
		}
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByAssociatedProyectContaining(AssociatedProyect associatedProyects) {
		logger.info("metodo: metodContainingRelacion NEW ");
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		for (Report report : this.getAllReport()) {
			for (AssociatedProyect associatedProyectsx : report.getAssociatedProyects()) {
				if (associatedProyectsx.equalsAssociatedProyect(associatedProyects)) {
					listaReport.add(report);
				}
			}
		}
		return listaReport;
	}

	
	
	@Override
	public List<Report> findByTimeReportContaining(TimeReport times) {
		logger.info("metodo: metodContainingRelacion NEW ");
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		for (Report report : this.getAllReport()) {
			for (TimeReport timesx : report.getTimes()) {
				if (timesx.equals(times)) {
					listaReport.add(report);
				}
			}
		}
		return listaReport;

	}

	
	
	@Override
	public List<Report> findByTimeAssignedContaining(TimeAssigned assigmeds) {
		logger.info("metodo: metodContainingRelacion NEW ");
		logger.info("Get allProyect");
		List<Report> listaReport = new ArrayList<Report>();
		for (Report report : this.getAllReport()) {
			for (TimeAssigned assigmedsx : report.getAssigmeds()) {
				if (assigmedsx.equalsTimeAssigned(assigmeds)) {
					listaReport.add(report);
				}
			}
		}
		return listaReport;

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
