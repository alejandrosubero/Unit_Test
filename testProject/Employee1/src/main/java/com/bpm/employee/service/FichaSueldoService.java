
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

import java.util.List;

import com.bpm.employee.entitys.FichaSueldo;


public interface FichaSueldoService{
 
		public FichaSueldo findByTasaHoraFeriado(Long tasaHoraFeriado);

		public FichaSueldo  findByTasaHora(Long tasaHora);

		public FichaSueldo  findByTasaDia(Long tasaDia);

		public FichaSueldo  findByHorasJornadaDia(Long horasJornadaDia);

		public FichaSueldo  findByTipoDePago(String tipoDePago);

		public FichaSueldo  findByJornadasemanalDias(Long jornadasemanalDias);

		public FichaSueldo  findBySueldoIntegral(Long sueldoIntegral);

		public FichaSueldo  findByTotalAnual(Long totalAnual);

		public FichaSueldo  findByTasaHoraExtra(Long tasaHoraExtra);

		public List<FichaSueldo>  findByTasaHoraFeriadoContaining(Long tasaHoraFeriado);

		public List<FichaSueldo>  findByTasaHoraContaining(Long tasaHora);

		public List<FichaSueldo>  findByTasaDiaContaining(Long tasaDia);

		public List<FichaSueldo>  findByHorasJornadaDiaContaining(Long horasJornadaDia);

		public List<FichaSueldo>  findByTipoDePagoContaining(String tipoDePago);

		public List<FichaSueldo>  findByJornadasemanalDiasContaining(Long jornadasemanalDias);

		public List<FichaSueldo>  findBySueldoIntegralContaining(Long sueldoIntegral);

		public List<FichaSueldo>  findByTotalAnualContaining(Long totalAnual);

		public List<FichaSueldo>  findByTasaHoraExtraContaining(Long tasaHoraExtra);

		public FichaSueldo findById(Long id);
		public boolean saveFichaSueldo(FichaSueldo fichasueldo);
		public List<FichaSueldo> getAllFichaSueldo();
		public boolean updateFichaSueldo(FichaSueldo fichasueldo);
 		public boolean saveOrUpdateFichaSueldo(FichaSueldo fichasueldo);

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


