
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



package com.bpm.employee.controller;
import com.bpm.employee.mapper.EmployeeReliefMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EmployeeReliefPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.validation.EmployeeReliefValidation;
import com.bpm.employee.service.EmployeeReliefService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employeerelief")
public class EmployeeReliefController {



		@Autowired
		EmployeeReliefService employeereliefService;

		@Autowired
        EmployeeReliefValidation employeereliefValidationService;

		@Autowired
        EmployeeReliefMapper employeereliefMapper;

		@Autowired
        MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Getemployeetoberelieved/{employeetoberelieved}")
		private  ResponseEntity<EntityRespone> findByEmployeeToBeRelieved(@PathVariable("employeetoberelieved") String  employeetoberelieved) {
		String busca = (String) employeereliefValidationService.validation(employeetoberelieved);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(employeereliefService.findByEmployeeToBeRelieved(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/get/employee/relief/{employeerelief}")
		private  ResponseEntity<EntityRespone> findByEmployeeRelief(@PathVariable("employeerelief") String  employeerelief) {
		String busca = (String) employeereliefValidationService.validation(employeerelief);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(employeereliefService.findByEmployeeRelief(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getemployeetoberelievedcontain/{employeetoberelieved}")
		private ResponseEntity<EntityRespone> findByEmployeeToBeRelievedContain(@PathVariable("employeetoberelieved") String  employeetoberelieved) {
			String busca = (String) employeereliefValidationService.validation(employeetoberelieved);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(employeereliefService.findByEmployeeToBeRelievedContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getemployeereliefcontain/{employeerelief}")
		private ResponseEntity<EntityRespone> findByEmployeeReliefContain(@PathVariable("employeerelief") String  employeerelief) {
			String busca = (String) employeereliefValidationService.validation(employeerelief);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(employeereliefService.findByEmployeeReliefContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetEmployeeRelief/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(employeereliefService.findById(employeereliefValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllEmployeeRelief")
		private  ResponseEntity<EntityRespone> getAllEmployeeRelief(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(employeereliefService.getAllEmployeeRelief());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveEmployeeRelief(@RequestBody EmployeeReliefPojo employeerelief){
			return employeereliefService.saveEmployeeRelief(employeereliefMapper.pojoToEntity(employeereliefValidationService.valida(employeerelief)) ); }



		@PostMapping("/Update")
		private Long UpdateEmployeeRelief(@RequestBody EmployeeReliefPojo  employeerelief){ 
			employeereliefService.updateEmployeeRelief(employeereliefMapper.pojoToEntity(employeereliefValidationService.valida(employeerelief)));
			return employeerelief.getId(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateEmployeeRelief(@RequestBody EmployeeReliefPojo  employeerelief){ 
			return employeereliefService.saveOrUpdateEmployeeRelief(employeereliefMapper.pojoToEntity(employeereliefValidationService.valida(employeerelief)) ); }


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


