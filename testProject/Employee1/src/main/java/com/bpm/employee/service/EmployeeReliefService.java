
/*
Create on Sat Oct 07 21:00:29 EDT 2023
*Copyright (C) 123.
@author yo
@author yo
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Employee’s hamblet </p>
*/


package com.bpm.employee.service;

import java.util.List;

import com.bpm.employee.entitys.EmployeeRelief;
import com.bpm.employee.pojo.EmployeeReliefPojo;


public interface EmployeeReliefService{
 
		public EmployeeReliefPojo findByEmployeeToBeRelieved(String	employeeToBeRelieved);

		public EmployeeReliefPojo  findByEmployeeRelief(String	employeeRelief);

		public List<EmployeeReliefPojo>  findByEmployeeToBeRelievedContaining(String	employeeToBeRelieved);				public List<EmployeeReliefPojo>  findByEmployeeReliefContaining(String	employeeRelief);				public	EmployeeReliefPojo findById(Long id);
		public boolean saveEmployeeRelief(EmployeeRelief employeerelief);
		public List<EmployeeReliefPojo> getAllEmployeeRelief();
		public boolean updateEmployeeRelief(EmployeeRelief	employeerelief);
		public boolean saveOrUpdateEmployeeRelief(EmployeeRelief	employeerelief);

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


