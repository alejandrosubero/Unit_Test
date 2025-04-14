
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
import com.pts.entitys.TimeReport;
import org.springframework.stereotype.Repository;

@Repository("timeReport")
public interface TimeReportRepository extends CrudRepository< TimeReport, Long> {
 
		public Optional<TimeReport> findByTimeDedicate(Date timeDedicate);
		public List<TimeReport> findByTimeDedicateContaining(Date timeDedicate);
		public Optional<TimeReport> findByTimeDedicateTotal(Date timeDedicateTotal);
		public List<TimeReport> findByTimeDedicateTotalContaining(Date timeDedicateTotal);
		public Optional<TimeReport> findByIdReport(Long idReport);
		public List<TimeReport> findByIdReportContaining(Long idReport);
		public Optional<TimeReport> findByUserCode(String userCode);
		public List<TimeReport> findByUserCodeContaining(String userCode);

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


