
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


package com.pts.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.pts.entitys.Report;
import org.springframework.stereotype.Repository;

@Repository("report")
public interface ReportRepository extends CrudRepository< Report, Long> {
 
		public Optional<Report> findByUserCode(String userCode);
		public List<Report> findByUserCodeContaining(String userCode);
		public Optional<Report> findByUserAssigned(String userAssigned);
		public List<Report> findByUserAssignedContaining(String userAssigned);
		public Optional<Report> findByClient(String client);
		public List<Report> findByClientContaining(String client);
		public Optional<Report> findByCodeReports(String codeReports);
		public List<Report> findByCodeReportsContaining(String codeReports);
		public Optional<Report> findByTitle(String title);
		public List<Report> findByTitleContaining(String title);
		public Optional<Report> findByDescription(String description);
		public List<Report> findByDescriptionContaining(String description);
		public Optional<Report> findByComentarios(Byte comentarios);
		public List<Report> findByComentariosContaining(Byte comentarios);
		public Optional<Report> findByState(String state);
		public List<Report> findByStateContaining(String state);
		public Optional<Report> findByStateReport(String stateReport);
		public List<Report> findByStateReportContaining(String stateReport);
		public Optional<Report> findByTimeDedicate(Long timeDedicate);
		public List<Report> findByTimeDedicateContaining(Long timeDedicate);
		public Optional<Report> findByStartDate(Date startDate);
		public List<Report> findByStartDateContaining(Date startDate);
		public Optional<Report> findByCommitmentDate(Date commitmentDate);
		public List<Report> findByCommitmentDateContaining(Date commitmentDate);
		public Optional<Report> findByPriority(String priority);
		public List<Report> findByPriorityContaining(String priority);
		public Optional<Report> findByLinkAcceso(String linkAcceso);
		public List<Report> findByLinkAccesoContaining(String linkAcceso);

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


