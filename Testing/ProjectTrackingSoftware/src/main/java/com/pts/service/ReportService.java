
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


package com.pts.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.pts.entitys.Report;import com.pts.entitys.TypeReport;
import com.pts.pojo.ReportPojo;
import com.pts.entitys.AssociatedProyect;
import com.pts.entitys.TimeReport;
import com.pts.entitys.TimeAssigned;



public interface ReportService{
 
	
		public ReportPojo saveNewReport(ReportPojo report);
	
		public Report  findByUserCode(String userCode);

		public Report  findByUserAssigned(String userAssigned);

		public Report  findByClient(String client);

		public Report  findByCodeReports(String codeReports);

		public Report  findByTitle(String title);

		public Report  findByDescription(String description);

		public Report  findByComentarios(Byte comentarios);

		public Report  findByState(String state);

		public Report  findByStateReport(String stateReport);

		public Report  findByTimeDedicate(Long timeDedicate);

		public Report  findByStartDate(Date startDate);

		public Report  findByCommitmentDate(Date commitmentDate);

		public Report  findByPriority(String priority);

		public Report  findByLinkAcceso(String linkAcceso);

		public List<Report>  findByUserCodeContaining(String userCode);

		public List<Report>  findByUserAssignedContaining(String userAssigned);

		public List<Report>  findByClientContaining(String client);

		public List<Report>  findByCodeReportsContaining(String codeReports);

		public List<Report>  findByTitleContaining(String title);

		public List<Report>  findByDescriptionContaining(String description);

		public List<Report>  findByComentariosContaining(Byte comentarios);

		public List<Report>  findByStateContaining(String state);

		public List<Report>  findByStateReportContaining(String stateReport);

		public List<Report>  findByTimeDedicateContaining(Long timeDedicate);

		public List<Report>  findByStartDateContaining(Date startDate);

		public List<Report>  findByCommitmentDateContaining(Date commitmentDate);

		public List<Report>  findByPriorityContaining(String priority);

		public List<Report>  findByLinkAccesoContaining(String linkAcceso);

		public Report findById(Long id);
		public boolean saveReport(Report report);
		public List<Report> getAllReport();
		public boolean deleteReport(Long id);

		public List<Report>  findByRelacionTypeReport(TypeReport typereport);
		public List<Report>  findByAssociatedProyectContaining(AssociatedProyect associatedProyects);
		public List<Report>  findByTimeReportContaining(TimeReport times );
		public List<Report>  findByTimeAssignedContaining(TimeAssigned assigmeds);
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


