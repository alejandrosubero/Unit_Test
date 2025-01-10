
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


package com.bpm.employee.repository;

import java.util.List;

import java.util.Optional;

import com.bpm.employee.entitys.Deduccion;
import org.springframework.data.repository.CrudRepository;

public interface DeduccionRepository extends CrudRepository<Deduccion, Long> {
 
		public Optional<Deduccion> findByImpuestoFederalRenta(Long impuestoFederalRenta);
		public List<Deduccion> findByImpuestoFederalRentaContaining(Long impuestoFederalRenta);
		public Optional<Deduccion> findBySocialSecurity(Long socialSecurity);
		public List<Deduccion> findBySocialSecurityContaining(Long socialSecurity);
		public Optional<Deduccion> findByMedicare(Long medicare);
		public List<Deduccion> findByMedicareContaining(Long medicare);

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


