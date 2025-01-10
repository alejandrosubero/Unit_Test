
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
import com.bpm.employee.mapper.CargoMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.CargoPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.validation.CargoValidation;
import com.bpm.employee.service.CargoService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cargo")
public class CargoController {



		@Autowired
		CargoService cargoService;

		@Autowired
        CargoValidation cargoValidationService;

		@Autowired
        CargoMapper cargoMapper;

		@Autowired
        MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Getcode/{code}")
		private  ResponseEntity<EntityRespone> findByCode(@PathVariable("code") String  code) {
		String busca = (String) cargoValidationService.validation(code);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(cargoService.findByCode(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdescription/{description}")
		private  ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String  description) {
		String busca = (String) cargoValidationService.validation(description);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(cargoService.findByDescription(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getname/{name}")
		private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
		String busca = (String) cargoValidationService.validation(name);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(cargoService.findByName(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getareaordivision/{areaordivision}")
		private  ResponseEntity<EntityRespone> findByAreaOrDivision(@PathVariable("areaordivision") String  areaordivision) {
		String busca = (String) cargoValidationService.validation(areaordivision);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(cargoService.findByAreaOrDivision(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getsubareaordivision/{subareaordivision}")
		private  ResponseEntity<EntityRespone> findBySubAreaOrDivision(@PathVariable("subareaordivision") String  subareaordivision) {
		String busca = (String) cargoValidationService.validation(subareaordivision);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(cargoService.findBySubAreaOrDivision(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getcodecontain/{code}")
		private ResponseEntity<EntityRespone> findByCodeContain(@PathVariable("code") String  code) {
			String busca = (String) cargoValidationService.validation(code);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(cargoService.findByCodeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdescriptioncontain/{description}")
		private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String  description) {
			String busca = (String) cargoValidationService.validation(description);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(cargoService.findByDescriptionContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnamecontain/{name}")
		private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
			String busca = (String) cargoValidationService.validation(name);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(cargoService.findByNameContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getareaordivisioncontain/{areaordivision}")
		private ResponseEntity<EntityRespone> findByAreaOrDivisionContain(@PathVariable("areaordivision") String  areaordivision) {
			String busca = (String) cargoValidationService.validation(areaordivision);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(cargoService.findByAreaOrDivisionContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getsubareaordivisioncontain/{subareaordivision}")
		private ResponseEntity<EntityRespone> findBySubAreaOrDivisionContain(@PathVariable("subareaordivision") String  subareaordivision) {
			String busca = (String) cargoValidationService.validation(subareaordivision);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(cargoService.findBySubAreaOrDivisionContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetCargo/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(cargoService.findById(cargoValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllCargo")
		private  ResponseEntity<EntityRespone> getAllCargo(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(cargoService.getAllCargo());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveCargo(@RequestBody CargoPojo cargo){
			return cargoService.saveCargo(cargoMapper.pojoToEntity(cargoValidationService.valida(cargo)) ); }



		@PostMapping("/Update")
		private Long UpdateCargo(@RequestBody CargoPojo  cargo){ 
			cargoService.updateCargo(cargoMapper.pojoToEntity(cargoValidationService.valida(cargo)));
			return cargo.getIdCargo(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateCargo(@RequestBody CargoPojo  cargo){ 
			return cargoService.saveOrUpdateCargo(cargoMapper.pojoToEntity(cargoValidationService.valida(cargo)) ); }


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


