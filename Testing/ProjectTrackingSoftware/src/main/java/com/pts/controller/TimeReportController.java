
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
import com.pts.entitys.TimeReport;
import com.pts.validation.TimeReportValidation;
import com.pts.mapper.TimeReportMapper;
import com.pts.service.TimeReportService;
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
import com.pts.pojo.TimeReportPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/timereport")
public class TimeReportController {

    @Autowired
    TimeReportService timereportService;

    @Autowired
    TimeReportValidation timereportValidationService;

    @Autowired
   TimeReportMapper timereportMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Gettimededicate/{timededicate}")
        private  ResponseEntity<EntityRespone> findByTimeDedicate(@PathVariable("timededicate") Date  timededicate) {
        Date busca = (Date) timereportValidationService.validation(timededicate);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timereportService.findByTimeDedicate(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettimededicatetotal/{timededicatetotal}")
        private  ResponseEntity<EntityRespone> findByTimeDedicateTotal(@PathVariable("timededicatetotal") Date  timededicatetotal) {
        Date busca = (Date) timereportValidationService.validation(timededicatetotal);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timereportService.findByTimeDedicateTotal(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getidreport/{idreport}")
        private  ResponseEntity<EntityRespone> findByIdReport(@PathVariable("idreport") Long  idreport) {
        Long busca = (Long) timereportValidationService.validation(idreport);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timereportService.findByIdReport(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getusercode/{usercode}")
        private  ResponseEntity<EntityRespone> findByUserCode(@PathVariable("usercode") String  usercode) {
        String busca = (String) timereportValidationService.validation(usercode);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timereportService.findByUserCode(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Gettimededicatecontain/{timededicate}")
        private ResponseEntity<EntityRespone> findByTimeDedicateContain(@PathVariable("timededicate") Date  timededicate) {
              Date busca = (Date) timereportValidationService.validation(timededicate);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timereportService.findByTimeDedicateContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettimededicatetotalcontain/{timededicatetotal}")
        private ResponseEntity<EntityRespone> findByTimeDedicateTotalContain(@PathVariable("timededicatetotal") Date  timededicatetotal) {
              Date busca = (Date) timereportValidationService.validation(timededicatetotal);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timereportService.findByTimeDedicateTotalContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getidreportcontain/{idreport}")
        private ResponseEntity<EntityRespone> findByIdReportContain(@PathVariable("idreport") Long  idreport) {
              Long busca = (Long) timereportValidationService.validation(idreport);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timereportService.findByIdReportContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getusercodecontain/{usercode}")
        private ResponseEntity<EntityRespone> findByUserCodeContain(@PathVariable("usercode") String  usercode) {
              String busca = (String) timereportValidationService.validation(usercode);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(timereportService.findByUserCodeContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetTimeReport/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(timereportService.findById(timereportValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllTimeReport")
        private  ResponseEntity<EntityRespone> getAllTimeReport(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(timereportService.getAllTimeReport());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveTimeReport(@RequestBody TimeReportPojo  timereport){ 
            return timereportService.saveTimeReport(timereportMapper.pojoToEntity(timereportValidationService.valida(timereport)) ); }





        @DeleteMapping("/deleteTimeReport/{id}")
            private boolean deleteTimeReport(@PathVariable("id") String id) {
            return timereportService.deleteTimeReport(timereportValidationService.valida_id(id)); }

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


