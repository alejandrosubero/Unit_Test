
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


package com.bpm.employee.service;

import java.util.Date;

import java.util.List;

import com.bpm.employee.entitys.FichaFaltas;


public interface FichaFaltasService{
 
		public FichaFaltas findByFechaFalta(Date fechaFalta);

		public FichaFaltas  findByRazon(String razon);

		public FichaFaltas  findByRetorno(Date retorno);

		public List<FichaFaltas>  findByFechaFaltaContaining(Date fechaFalta);

		public List<FichaFaltas>  findByRazonContaining(String razon);

		public List<FichaFaltas>  findByRetornoContaining(Date retorno);

		public FichaFaltas findById(Long id);
		public boolean saveFichaFaltas(FichaFaltas fichafaltas);
		public List<FichaFaltas> getAllFichaFaltas();
		public boolean updateFichaFaltas(FichaFaltas fichafaltas);
 		public boolean saveOrUpdateFichaFaltas(FichaFaltas fichafaltas);

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


