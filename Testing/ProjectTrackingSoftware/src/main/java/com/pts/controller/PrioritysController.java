
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
import com.pts.entitys.Prioritys;
import com.pts.validation.PrioritysValidation;
import com.pts.mapper.PrioritysMapper;
import com.pts.service.PrioritysService;
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
import com.pts.pojo.PrioritysPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/prioritys")
public class PrioritysController {

    @Autowired
    PrioritysService prioritysService;

    @Autowired
    PrioritysValidation prioritysValidationService;

    @Autowired
   PrioritysMapper prioritysMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getvalue/{value}")
        private  ResponseEntity<EntityRespone> findByValue(@PathVariable("value") String  value) {
        String busca = (String) prioritysValidationService.validation(value);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(prioritysService.findByValue(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdescription/{description}")
        private  ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String  description) {
        String busca = (String) prioritysValidationService.validation(description);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(prioritysService.findByDescription(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getvaluecontain/{value}")
        private ResponseEntity<EntityRespone> findByValueContain(@PathVariable("value") String  value) {
              String busca = (String) prioritysValidationService.validation(value);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(prioritysService.findByValueContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdescriptioncontain/{description}")
        private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String  description) {
              String busca = (String) prioritysValidationService.validation(description);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(prioritysService.findByDescriptionContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetPrioritys/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(prioritysService.findById(prioritysValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllPrioritys")
        private  ResponseEntity<EntityRespone> getAllPrioritys(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(prioritysService.getAllPrioritys());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  savePrioritys(@RequestBody PrioritysPojo  prioritys){ 
            return prioritysService.savePrioritys(prioritysMapper.pojoToEntity(prioritysValidationService.valida(prioritys)) ); }





        @DeleteMapping("/deletePrioritys/{id}")
            private boolean deletePrioritys(@PathVariable("id") String id) {
            return prioritysService.deletePrioritys(prioritysValidationService.valida_id(id)); }

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


