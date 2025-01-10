
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

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bpm.employee.entitys.*;
import com.bpm.employee.pojo.EmpleadoPojo;
import com.bpm.employee.entitys.*;


public interface EmpleadoService {

    public Empleado findBySocialSecurityNumber(String socialSecurityNumber);

    public Empleado findByName(String name);

    public Empleado findByLastName(String lastName);

    public Empleado findByFullName(String fullName);

    public Empleado findByAddress(String address);

    public Empleado findByPhoneNumber(String phoneNumber);


    public Empleado findByPosition(Cargo position);
    public EmpleadoPojo findByNumeroEmpleado(String numeroEmpleado);

    public Empleado findByFechaIngreso(Date fechaIngreso);

    public Empleado findByFechaEgreso(Date fechaEgreso);

    public Empleado findByCausaEgreso(String causaEgreso);

    public Empleado findByTotalDiasTrabajados(Long totalDiasTrabajados);

    public Empleado findByTotalHorasMes(Long totalHorasMes);

    public Empleado findByTotalHorasYear(Long totalHorasYear);

    public Empleado findByTotalHorasExtrasYear(Long totalHorasExtrasYear);

    public Empleado findByDateLastPay(Date dateLastPay);

    public Empleado findByTotalHorasFeriadoYear(Long totalHorasFeriadoYear);

    public List<Empleado> findBySocialSecurityNumberContaining(String socialSecurityNumber);

    public List<Empleado> findByNameContaining(String name);

    public List<Empleado> findByLastNameContaining(String lastName);

    public List<Empleado> findByFullNameContaining(String fullName);

    public List<Empleado> findByAddressContaining(String address);

    public List<Empleado> findByPhoneNumberContaining(String phoneNumber);

    public List<Empleado> findByNumeroEmpleadoContaining(String numeroEmpleado);

    public List<Empleado> findByFechaIngresoContaining(Date fechaIngreso);

    public List<Empleado> findByFechaEgresoContaining(Date fechaEgreso);

    public List<Empleado> findByCausaEgresoContaining(String causaEgreso);

    public List<Empleado> findByTotalDiasTrabajadosContaining(Long totalDiasTrabajados);

    public List<Empleado> findByTotalHorasMesContaining(Long totalHorasMes);

    public List<Empleado> findByTotalHorasYearContaining(Long totalHorasYear);

    public List<Empleado> findByTotalHorasExtrasYearContaining(Long totalHorasExtrasYear);

    public List<Empleado> findByDateLastPayContaining(Date dateLastPay);

    public List<Empleado> findByTotalHorasFeriadoYearContaining(Long totalHorasFeriadoYear);

    public Empleado findById(Long id);

    public boolean saveEmpleado(Empleado empleado);

    public List<Empleado> getAllEmpleado();

    public boolean updateEmpleado(Empleado empleado);

    public String saveOrUpdateEmpleado(Empleado empleado);
    public boolean saveListEmployees(List<Empleado> employees);

    public List<Empleado> findByFichaDesempenoContaining(FichaDesempeno listaDesempeno);

    public List<Empleado> findByFichaPermisosContaining(FichaPermisos permisos);

    public List<Empleado> findByRelacionFichaSueldo(FichaSueldo fichasueldo);

    public List<Empleado> findByFichaPagosContaining(FichaPagos pagos);

    public List<Empleado> findByFichaFaltasContaining(FichaFaltas faltas);

    public List<Empleado> findByRelacionFichaMedica(FichaMedica fichamedica);

    public List<String> findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCode(String areaDivision, String subAreaDivision, String positioCode);
    public List<String> findNumeroEmpleadoByUserName(String userName);
    public EmpleadoPojo findEmployeeByUserName(String userName);
    
  
    
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


