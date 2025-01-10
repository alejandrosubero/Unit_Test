
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

import javax.transaction.Transactional;

import com.bpm.employee.entitys.Cargo;
import com.bpm.employee.entitys.Empleado;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

		public Optional<Empleado> findByPosition(Cargo position);
		public Optional<Empleado> findBySocialSecurityNumber(String socialSecurityNumber);
		public List<Empleado> findBySocialSecurityNumberContaining(String socialSecurityNumber);
		public Optional<Empleado> findByName(String name);
		public List<Empleado> findByNameContaining(String name);
		public Optional<Empleado> findByLastName(String lastName);
		public List<Empleado> findByLastNameContaining(String lastName);
		public Optional<Empleado> findByFullName(String fullName);
		public List<Empleado> findByFullNameContaining(String fullName);
		public Optional<Empleado> findByAddress(String address);
		public List<Empleado> findByAddressContaining(String address);
		public Optional<Empleado> findByPhoneNumber(String phoneNumber);
		public List<Empleado> findByPhoneNumberContaining(String phoneNumber);

		public Optional<Empleado> findByNumeroEmpleado(String numeroEmpleado);
		public List<Empleado> findByNumeroEmpleadoContaining(String numeroEmpleado);
		public Optional<Empleado> findByFechaIngreso(Date fechaIngreso);
		public List<Empleado> findByFechaIngresoContaining(Date fechaIngreso);
		public Optional<Empleado> findByFechaEgreso(Date fechaEgreso);
		public List<Empleado> findByFechaEgresoContaining(Date fechaEgreso);
		public Optional<Empleado> findByCausaEgreso(String causaEgreso);
		public List<Empleado> findByCausaEgresoContaining(String causaEgreso);
		public Optional<Empleado> findByTotalDiasTrabajados(Long totalDiasTrabajados);
		public List<Empleado> findByTotalDiasTrabajadosContaining(Long totalDiasTrabajados);
		public Optional<Empleado> findByTotalHorasMes(Long totalHorasMes);
		public List<Empleado> findByTotalHorasMesContaining(Long totalHorasMes);
		public Optional<Empleado> findByTotalHorasYear(Long totalHorasYear);
		public List<Empleado> findByTotalHorasYearContaining(Long totalHorasYear);
		public Optional<Empleado> findByTotalHorasExtrasYear(Long totalHorasExtrasYear);
		public List<Empleado> findByTotalHorasExtrasYearContaining(Long totalHorasExtrasYear);
		public Optional<Empleado> findByDateLastPay(Date dateLastPay);
		public List<Empleado> findByDateLastPayContaining(Date dateLastPay);
		public Optional<Empleado> findByTotalHorasFeriadoYear(Long totalHorasFeriadoYear);
		public List<Empleado> findByTotalHorasFeriadoYearContaining(Long totalHorasFeriadoYear);

		@Query("SELECT e.numeroEmpleado FROM Empleado e INNER JOIN Cargo c ON e.position = c.idCargo AND c.areaDivision = ?1 AND c.subAreaDivision =?2")
		public List<String> findNumeroEmpleadoByAreaDivisionAndSubAreaDivision(String areaDivision, String subAreaDivision);

		@Query("SELECT e.numeroEmpleado FROM Empleado e INNER JOIN Cargo c ON e.position = c.idCargo AND c.areaDivision = ?1 AND c.subAreaDivision =?2 AND c.code =?3")
		public List<String> findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCode(String areaDivision, String subAreaDivision, String positioCode);

		@Query("SELECT e.numeroEmpleado FROM Empleado e INNER JOIN ComputerSheet c ON e.computerSheet = c.idComputerSheet AND c.userName = ?1")
		public List<String> findNumeroEmpleadoByUserName(String userName);


	    @Modifying
	    @Transactional
	    @Query(value = "SELECT  EMPLOYEE.ID  FROM EMPLOYEE inner join CARGO on EMPLOYEE.ID_CARGO = CARGO.ID_CARGO and CARGO.AREA_DIVISION = :areaDivision and CARGO.CODE = :cargoCode", nativeQuery = true)
	    public List<Long> findIDEmployeeByCargoandDivisionSQL(String areaDivision, String cargoCode);
	    
	    
	    
		@Query("SELECT e.id FROM Empleado e INNER JOIN Cargo c ON e.position = c.idCargo AND c.areaDivision = ?1 AND c.code =?2")
		public List<Long> findIdEmpleadoByAreaDivisionAndPositioCode(String areaDivision, String positioCode);
	    

		@Query("SELECT e.numeroEmpleado FROM Empleado e INNER JOIN Cargo c ON e.position = c.idCargo AND c.areaDivision = ?1 AND c.subAreaDivision =?2 AND c.code =?3 AND e.activo =?4")
		public List<String> findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCodeAndActivo(String areaDivision, String subAreaDivision, String positioCode, Boolean activo);

		
		

	//	public List<Work>finByCreateDayBetween(Date startDate, Date endDay);
//	@Query("SELECT p FROM Work p WHERE p.createDay BETWEEN ?1 AND ?2 AND p.active = true")

//	SELECT p FROM employee p WHERE p.numero_empleado = ( SELECT e.numero_empleado from employee e INNER JOIN cargo c ON e.id_cargo = c.id_cargo AND c.area_division = ?1 AND c.sub_area_division =?2);
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


