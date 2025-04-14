
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
import com.pts.entitys.TimeReport;


public interface TimeReportService{
 
		public TimeReport  findByTimeDedicate(Date timeDedicate);

		public TimeReport  findByTimeDedicateTotal(Date timeDedicateTotal);

		public TimeReport  findByIdReport(Long idReport);

		public TimeReport  findByUserCode(String userCode);

		public List<TimeReport>  findByTimeDedicateContaining(Date timeDedicate);

		public List<TimeReport>  findByTimeDedicateTotalContaining(Date timeDedicateTotal);

		public List<TimeReport>  findByIdReportContaining(Long idReport);

		public List<TimeReport>  findByUserCodeContaining(String userCode);

		public TimeReport findById(Long id);
		public boolean saveTimeReport(TimeReport timereport);
		public List<TimeReport> getAllTimeReport();
		public boolean deleteTimeReport(Long id);

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


