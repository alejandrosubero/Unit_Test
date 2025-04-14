
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

package com.pts.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.entitys.Report;
import com.pts.mapper.AssociatedProyectMapper;
import com.pts.mapper.MapperEntityRespone;
import com.pts.mapper.ReportMapper;
import com.pts.mapper.TimeAssignedMapper;
import com.pts.mapper.TimeReportMapper;
import com.pts.mapper.TypeReportMapper;
import com.pts.pojo.AssociatedProyectPojo;
import com.pts.pojo.EntityRespone;
import com.pts.pojo.ReportPojo;
import com.pts.pojo.TimeAssignedPojo;
import com.pts.pojo.TimeReportPojo;
import com.pts.pojo.TypeReportPojo;
import com.pts.service.ReportService;
import com.pts.validation.AssociatedProyectValidation;
import com.pts.validation.ReportValidation;
import com.pts.validation.TimeAssignedValidation;
import com.pts.validation.TimeReportValidation;
import com.pts.validation.TypeReportValidation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	ReportService reportService;

	@Autowired
	ReportValidation reportValidationService;

	@Autowired
	ReportMapper reportMapper;

	@Autowired
	MapperEntityRespone mapperEntityRespone;

	@Autowired
	TypeReportValidation typereportValidationService;

	@Autowired
	TypeReportMapper typereportMapper;

	@Autowired
	AssociatedProyectValidation associatedproyectValidationService;

	@Autowired
	AssociatedProyectMapper associatedproyectMapper;

	@Autowired
	TimeReportValidation timereportValidationService;

	@Autowired
	TimeReportMapper timereportMapper;

	@Autowired
	TimeAssignedValidation timeassignedValidationService;

	@Autowired
	TimeAssignedMapper timeassignedMapper;
	
	@PostMapping("/save/new")
	public ReportPojo saveNewReport(ReportPojo report) {
	//	 reportService.saveNewReport(reportMapper.pojoToEntity(reportValidationService.validaNewReport(report)));
		 return reportService.saveNewReport(report);
	}

	
	@PostMapping("/save")
	private Boolean saveReport(@RequestBody ReportPojo report) {
		return reportService.saveReport(reportMapper.pojoToEntity(reportValidationService.valida(report)));
	}
	
	@GetMapping("/GetAllReport")
	private ResponseEntity<EntityRespone> getAllReport() {
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.getAllReport());
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}
	

	@GetMapping("/Getusercode/{usercode}")
	private ResponseEntity<EntityRespone> findByUserCode(@PathVariable("usercode") String usercode) {
		String busca = (String) reportValidationService.validation(usercode);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByUserCode(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getuserassigned/{userassigned}")
	private ResponseEntity<EntityRespone> findByUserAssigned(@PathVariable("userassigned") String userassigned) {
		String busca = (String) reportValidationService.validation(userassigned);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByUserAssigned(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getclient/{client}")
	private ResponseEntity<EntityRespone> findByClient(@PathVariable("client") String client) {
		String busca = (String) reportValidationService.validation(client);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByClient(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getcodereports/{codereports}")
	private ResponseEntity<EntityRespone> findByCodeReports(@PathVariable("codereports") String codereports) {
		String busca = (String) reportValidationService.validation(codereports);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByCodeReports(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Gettitle/{title}")
	private ResponseEntity<EntityRespone> findByTitle(@PathVariable("title") String title) {
		String busca = (String) reportValidationService.validation(title);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByTitle(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getdescription/{description}")
	private ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String description) {
		String busca = (String) reportValidationService.validation(description);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByDescription(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getcomentarios/{comentarios}")
	private ResponseEntity<EntityRespone> findByComentarios(@PathVariable("comentarios") Byte comentarios) {
		Byte busca = (Byte) reportValidationService.validation(comentarios);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByComentarios(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getstate/{state}")
	private ResponseEntity<EntityRespone> findByState(@PathVariable("state") String state) {
		String busca = (String) reportValidationService.validation(state);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByState(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getstatereport/{statereport}")
	private ResponseEntity<EntityRespone> findByStateReport(@PathVariable("statereport") String statereport) {
		String busca = (String) reportValidationService.validation(statereport);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByStateReport(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Gettimededicate/{timededicate}")
	private ResponseEntity<EntityRespone> findByTimeDedicate(@PathVariable("timededicate") Long timededicate) {
		Long busca = (Long) reportValidationService.validation(timededicate);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByTimeDedicate(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getstartdate/{startdate}")
	private ResponseEntity<EntityRespone> findByStartDate(@PathVariable("startdate") Date startdate) {
		Date busca = (Date) reportValidationService.validation(startdate);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByStartDate(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getcommitmentdate/{commitmentdate}")
	private ResponseEntity<EntityRespone> findByCommitmentDate(@PathVariable("commitmentdate") Date commitmentdate) {
		Date busca = (Date) reportValidationService.validation(commitmentdate);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByCommitmentDate(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getpriority/{priority}")
	private ResponseEntity<EntityRespone> findByPriority(@PathVariable("priority") String priority) {
		String busca = (String) reportValidationService.validation(priority);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByPriority(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getlinkacceso/{linkacceso}")
	private ResponseEntity<EntityRespone> findByLinkAcceso(@PathVariable("linkacceso") String linkacceso) {
		String busca = (String) reportValidationService.validation(linkacceso);
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findByLinkAcceso(busca));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/Getusercodecontain/{usercode}")
	private ResponseEntity<EntityRespone> findByUserCodeContain(@PathVariable("usercode") String usercode) {
		String busca = (String) reportValidationService.validation(usercode);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByUserCodeContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getuserassignedcontain/{userassigned}")
	private ResponseEntity<EntityRespone> findByUserAssignedContain(@PathVariable("userassigned") String userassigned) {
		String busca = (String) reportValidationService.validation(userassigned);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByUserAssignedContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getclientcontain/{client}")
	private ResponseEntity<EntityRespone> findByClientContain(@PathVariable("client") String client) {
		String busca = (String) reportValidationService.validation(client);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByClientContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getcodereportscontain/{codereports}")
	private ResponseEntity<EntityRespone> findByCodeReportsContain(@PathVariable("codereports") String codereports) {
		String busca = (String) reportValidationService.validation(codereports);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByCodeReportsContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Gettitlecontain/{title}")
	private ResponseEntity<EntityRespone> findByTitleContain(@PathVariable("title") String title) {
		String busca = (String) reportValidationService.validation(title);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByTitleContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getdescriptioncontain/{description}")
	private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String description) {
		String busca = (String) reportValidationService.validation(description);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByDescriptionContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getcomentarioscontain/{comentarios}")
	private ResponseEntity<EntityRespone> findByComentariosContain(@PathVariable("comentarios") Byte comentarios) {
		Byte busca = (Byte) reportValidationService.validation(comentarios);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByComentariosContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getstatecontain/{state}")
	private ResponseEntity<EntityRespone> findByStateContain(@PathVariable("state") String state) {
		String busca = (String) reportValidationService.validation(state);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByStateContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getstatereportcontain/{statereport}")
	private ResponseEntity<EntityRespone> findByStateReportContain(@PathVariable("statereport") String statereport) {
		String busca = (String) reportValidationService.validation(statereport);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByStateReportContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Gettimededicatecontain/{timededicate}")
	private ResponseEntity<EntityRespone> findByTimeDedicateContain(@PathVariable("timededicate") Long timededicate) {
		Long busca = (Long) reportValidationService.validation(timededicate);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByTimeDedicateContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getstartdatecontain/{startdate}")
	private ResponseEntity<EntityRespone> findByStartDateContain(@PathVariable("startdate") Date startdate) {
		Date busca = (Date) reportValidationService.validation(startdate);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByStartDateContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getcommitmentdatecontain/{commitmentdate}")
	private ResponseEntity<EntityRespone> findByCommitmentDateContain(
			@PathVariable("commitmentdate") Date commitmentdate) {
		Date busca = (Date) reportValidationService.validation(commitmentdate);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByCommitmentDateContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getprioritycontain/{priority}")
	private ResponseEntity<EntityRespone> findByPriorityContain(@PathVariable("priority") String priority) {
		String busca = (String) reportValidationService.validation(priority);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByPriorityContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/Getlinkaccesocontain/{linkacceso}")
	private ResponseEntity<EntityRespone> findByLinkAccesoContain(@PathVariable("linkacceso") String linkacceso) {
		String busca = (String) reportValidationService.validation(linkacceso);
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByLinkAccesoContaining(busca));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/GetReport/{id}")
	private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(reportService.findById(reportValidationService.valida_id(id)));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	

	@DeleteMapping("/deleteReport/{id}")
	private boolean deleteReport(@PathVariable("id") String id) {
		return reportService.deleteReport(reportValidationService.valida_id(id));
	}

	@PostMapping("/Get_associatedProyects_contain/")
	private List<Report> findByAssociatedProyect(@RequestBody AssociatedProyectPojo associatedproyect) {
		return reportService.findByAssociatedProyectContaining(
				associatedproyectMapper.pojoToEntity(associatedproyectValidationService.valida(associatedproyect)));
	}

	@PostMapping("/Get_times _contain/")
	private List<Report> findByTimeReport(@RequestBody TimeReportPojo timereport) {
		return reportService.findByTimeReportContaining(
				timereportMapper.pojoToEntity(timereportValidationService.valida(timereport)));
	}

	@PostMapping("/Get_assigmeds_contain/")
	private List<Report> findByTimeAssigned(@RequestBody TimeAssignedPojo timeassigned) {
		return reportService.findByTimeAssignedContaining(
				timeassignedMapper.pojoToEntity(timeassignedValidationService.valida(timeassigned)));
	}

	@PostMapping("/findtypeReport")
	private ResponseEntity<EntityRespone> findRelacionTypeReport(@RequestBody TypeReportPojo typereport) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(reportService.findByRelacionTypeReport(
				typereportMapper.pojoToEntity(typereportValidationService.valida(typereport))));
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

}
/*
 * Copyright (C) 2008 Google Inc. Licensed to the Apache Software Foundation
 * (ASF) under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to You under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
