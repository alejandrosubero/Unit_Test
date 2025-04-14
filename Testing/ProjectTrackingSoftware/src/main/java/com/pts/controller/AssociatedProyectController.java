
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
import com.pts.entitys.AssociatedProyect;
import com.pts.validation.AssociatedProyectValidation;
import com.pts.mapper.AssociatedProyectMapper;
import com.pts.service.AssociatedProyectService;
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
import com.pts.pojo.AssociatedProyectPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/associatedproyect")
public class AssociatedProyectController {

    @Autowired
    AssociatedProyectService associatedproyectService;

    @Autowired
    AssociatedProyectValidation associatedproyectValidationService;

    @Autowired
   AssociatedProyectMapper associatedproyectMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getidproyect/{idproyect}")
        private  ResponseEntity<EntityRespone> findByIdProyect(@PathVariable("idproyect") Long  idproyect) {
        Long busca = (Long) associatedproyectValidationService.validation(idproyect);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(associatedproyectService.findByIdProyect(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getidreport/{idreport}")
        private  ResponseEntity<EntityRespone> findByIdReport(@PathVariable("idreport") Long  idreport) {
        Long busca = (Long) associatedproyectValidationService.validation(idreport);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(associatedproyectService.findByIdReport(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getidproyectcontain/{idproyect}")
        private ResponseEntity<EntityRespone> findByIdProyectContain(@PathVariable("idproyect") Long  idproyect) {
              Long busca = (Long) associatedproyectValidationService.validation(idproyect);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(associatedproyectService.findByIdProyectContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getidreportcontain/{idreport}")
        private ResponseEntity<EntityRespone> findByIdReportContain(@PathVariable("idreport") Long  idreport) {
              Long busca = (Long) associatedproyectValidationService.validation(idreport);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(associatedproyectService.findByIdReportContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetAssociatedProyect/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(associatedproyectService.findById(associatedproyectValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllAssociatedProyect")
        private  ResponseEntity<EntityRespone> getAllAssociatedProyect(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(associatedproyectService.getAllAssociatedProyect());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveAssociatedProyect(@RequestBody AssociatedProyectPojo  associatedproyect){ 
            return associatedproyectService.saveAssociatedProyect(associatedproyectMapper.pojoToEntity(associatedproyectValidationService.valida(associatedproyect)) ); }





        @DeleteMapping("/deleteAssociatedProyect/{id}")
            private boolean deleteAssociatedProyect(@PathVariable("id") String id) {
            return associatedproyectService.deleteAssociatedProyect(associatedproyectValidationService.valida_id(id)); }

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


