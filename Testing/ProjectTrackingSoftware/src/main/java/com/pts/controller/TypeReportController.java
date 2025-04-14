
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
import com.pts.entitys.TypeReport;
import com.pts.validation.TypeReportValidation;
import com.pts.mapper.TypeReportMapper;
import com.pts.service.TypeReportService;
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
import com.pts.pojo.TypeReportPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/typereport")
public class TypeReportController {

    @Autowired
    TypeReportService typereportService;

    @Autowired
    TypeReportValidation typereportValidationService;

    @Autowired
   TypeReportMapper typereportMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Gettype/{type}")
        private  ResponseEntity<EntityRespone> findByType(@PathVariable("type") String  type) {
        String busca = (String) typereportValidationService.validation(type);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(typereportService.findByType(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdescripton/{descripton}")
        private  ResponseEntity<EntityRespone> findByDescripton(@PathVariable("descripton") String  descripton) {
        String busca = (String) typereportValidationService.validation(descripton);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(typereportService.findByDescripton(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Gettypecontain/{type}")
        private ResponseEntity<EntityRespone> findByTypeContain(@PathVariable("type") String  type) {
              String busca = (String) typereportValidationService.validation(type);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(typereportService.findByTypeContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdescriptoncontain/{descripton}")
        private ResponseEntity<EntityRespone> findByDescriptonContain(@PathVariable("descripton") String  descripton) {
              String busca = (String) typereportValidationService.validation(descripton);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(typereportService.findByDescriptonContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetTypeReport/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(typereportService.findById(typereportValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllTypeReport")
        private  ResponseEntity<EntityRespone> getAllTypeReport(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(typereportService.getAllTypeReport());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveTypeReport(@RequestBody TypeReportPojo  typereport){ 
            return typereportService.saveTypeReport(typereportMapper.pojoToEntity(typereportValidationService.valida(typereport)) ); }





        @DeleteMapping("/deleteTypeReport/{id}")
            private boolean deleteTypeReport(@PathVariable("id") String id) {
            return typereportService.deleteTypeReport(typereportValidationService.valida_id(id)); }

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


