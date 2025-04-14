
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

import java.util.List;

import com.pts.entitys.TypeReport;


public interface TypeReportService{
 
		public TypeReport  findByType(String type);

		public TypeReport  findByDescripton(String descripton);

		public List<TypeReport>  findByTypeContaining(String type);

		public List<TypeReport>  findByDescriptonContaining(String descripton);

		public TypeReport findById(Long id);
		
		public boolean saveTypeReport(TypeReport typereport);
		
		public List<TypeReport> getAllTypeReport();
		
		public boolean deleteTypeReport(Long id);

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


