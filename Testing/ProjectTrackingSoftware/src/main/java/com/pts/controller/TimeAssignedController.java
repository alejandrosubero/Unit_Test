
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
import com.pts.entitys.TimeAssigned;
import com.pts.validation.TimeAssignedValidation;
import com.pts.mapper.TimeAssignedMapper;
import com.pts.service.TimeAssignedService;
import com.pts.mapper.MapperEntityRespone;
import com.pts.pojo.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.pts.pojo.TimeAssignedPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/timeassigned")
public class TimeAssignedController {

    @Autowired
    TimeAssignedService timeassignedService;

    @Autowired
    TimeAssignedValidation timeassignedValidationService;

    @Autowired
   TimeAssignedMapper timeassignedMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getassigneddate/{assigneddate}")
        private  ResponseEntity<EntityRespone> findByAssignedDate(@PathVariable("assigneddate") Date  assigneddate) {
        Date busca = (Date) timeassignedValidationService.validation(assigneddate);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timeassignedService.findByAssignedDate(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getstateassigned/{stateassigned}")
        private  ResponseEntity<EntityRespone> findByStateAssigned(@PathVariable("stateassigned") String  stateassigned) {
        String busca = (String) timeassignedValidationService.validation(stateassigned);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timeassignedService.findByStateAssigned(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getstatereport/{statereport}")
        private  ResponseEntity<EntityRespone> findByStateReport(@PathVariable("statereport") String  statereport) {
        String busca = (String) timeassignedValidationService.validation(statereport);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timeassignedService.findByStateReport(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getusercode/{usercode}")
        private  ResponseEntity<EntityRespone> findByUserCode(@PathVariable("usercode") String  usercode) {
        String busca = (String) timeassignedValidationService.validation(usercode);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timeassignedService.findByUserCode(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getidreport/{idreport}")
        private  ResponseEntity<EntityRespone> findByIdReport(@PathVariable("idreport") Long  idreport) {
        Long busca = (Long) timeassignedValidationService.validation(idreport);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timeassignedService.findByIdReport(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getuserassigned/{userassigned}")
        private  ResponseEntity<EntityRespone> findByUserAssigned(@PathVariable("userassigned") String  userassigned) {
        String busca = (String) timeassignedValidationService.validation(userassigned);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timeassignedService.findByUserAssigned(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getassigneddatecontain/{assigneddate}")
        private ResponseEntity<EntityRespone> findByAssignedDateContain(@PathVariable("assigneddate") Date  assigneddate) {
              Date busca = (Date) timeassignedValidationService.validation(assigneddate);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timeassignedService.findByAssignedDateContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getstateassignedcontain/{stateassigned}")
        private ResponseEntity<EntityRespone> findByStateAssignedContain(@PathVariable("stateassigned") String  stateassigned) {
              String busca = (String) timeassignedValidationService.validation(stateassigned);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timeassignedService.findByStateAssignedContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getstatereportcontain/{statereport}")
        private ResponseEntity<EntityRespone> findByStateReportContain(@PathVariable("statereport") String  statereport) {
              String busca = (String) timeassignedValidationService.validation(statereport);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timeassignedService.findByStateReportContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getusercodecontain/{usercode}")
        private ResponseEntity<EntityRespone> findByUserCodeContain(@PathVariable("usercode") String  usercode) {
              String busca = (String) timeassignedValidationService.validation(usercode);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timeassignedService.findByUserCodeContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getidreportcontain/{idreport}")
        private ResponseEntity<EntityRespone> findByIdReportContain(@PathVariable("idreport") Long  idreport) {
              Long busca = (Long) timeassignedValidationService.validation(idreport);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timeassignedService.findByIdReportContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getuserassignedcontain/{userassigned}")
        private ResponseEntity<EntityRespone> findByUserAssignedContain(@PathVariable("userassigned") String  userassigned) {
              String busca = (String) timeassignedValidationService.validation(userassigned);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timeassignedService.findByUserAssignedContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetTimeAssigned/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timeassignedService.findById(timeassignedValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllTimeAssigned")
        private  ResponseEntity<EntityRespone> getAllTimeAssigned(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(timeassignedService.getAllTimeAssigned());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveTimeAssigned(@RequestBody TimeAssignedPojo  timeassigned){ 
            return timeassignedService.saveTimeAssigned(timeassignedMapper.pojoToEntity(timeassignedValidationService.valida(timeassigned)) ); }





        @DeleteMapping("/deleteTimeAssigned/{id}")
            private boolean deleteTimeAssigned(@PathVariable("id") String id) {
            return timeassignedService.deleteTimeAssigned(timeassignedValidationService.valida_id(id)); }

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


