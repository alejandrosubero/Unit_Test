
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

import java.util.List;import java.util.Date;

import java.util.Optional;

import com.bpm.employee.entitys.FichaPermisos;
import org.springframework.data.repository.CrudRepository;

public interface FichaPermisosRepository extends CrudRepository<FichaPermisos, Long> {
 
		public Optional<FichaPermisos> findByFechaSalida(Date fechaSalida);
		public List<FichaPermisos> findByFechaSalidaContaining(Date fechaSalida);
		public Optional<FichaPermisos> findByFechaRetorno(Date fechaRetorno);
		public List<FichaPermisos> findByFechaRetornoContaining(Date fechaRetorno);
		public Optional<FichaPermisos> findByHoraSalida(Date horaSalida);
		public List<FichaPermisos> findByHoraSalidaContaining(Date horaSalida);
		public Optional<FichaPermisos> findByHoraRetorno(Date horaRetorno);
		public List<FichaPermisos> findByHoraRetornoContaining(Date horaRetorno);
		public Optional<FichaPermisos> findByTipoPermiso(String tipoPermiso);
		public List<FichaPermisos> findByTipoPermisoContaining(String tipoPermiso);
		public Optional<FichaPermisos> findByMotivo(String motivo);
		public List<FichaPermisos> findByMotivoContaining(String motivo);

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


