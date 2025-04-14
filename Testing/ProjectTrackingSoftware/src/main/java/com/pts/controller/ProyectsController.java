
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
import com.pts.entitys.Proyects;
import com.pts.validation.ProyectsValidation;
import com.pts.mapper.ProyectsMapper;
import com.pts.service.ProyectsService;
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
import com.pts.pojo.ProyectsPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/proyects")
public class ProyectsController {

    @Autowired
    ProyectsService proyectsService;

    @Autowired
    ProyectsValidation proyectsValidationService;

    @Autowired
   ProyectsMapper proyectsMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Gettitleproyect/{titleproyect}")
        private  ResponseEntity<EntityRespone> findByTitleProyect(@PathVariable("titleproyect") String  titleproyect) {
        String busca = (String) proyectsValidationService.validation(titleproyect);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(proyectsService.findByTitleProyect(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdescription/{description}")
        private  ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String  description) {
        String busca = (String) proyectsValidationService.validation(description);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(proyectsService.findByDescription(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Gettitleproyectcontain/{titleproyect}")
        private ResponseEntity<EntityRespone> findByTitleProyectContain(@PathVariable("titleproyect") String  titleproyect) {
              String busca = (String) proyectsValidationService.validation(titleproyect);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(proyectsService.findByTitleProyectContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdescriptioncontain/{description}")
        private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String  description) {
              String busca = (String) proyectsValidationService.validation(description);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(proyectsService.findByDescriptionContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetProyects/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(proyectsService.findById(proyectsValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllProyects")
        private  ResponseEntity<EntityRespone> getAllProyects(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(proyectsService.getAllProyects());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveProyects(@RequestBody ProyectsPojo  proyects){ 
            return proyectsService.saveProyects(proyectsMapper.pojoToEntity(proyectsValidationService.valida(proyects)) ); }





        @DeleteMapping("/deleteProyects/{id}")
            private boolean deleteProyects(@PathVariable("id") String id) {
            return proyectsService.deleteProyects(proyectsValidationService.valida_id(id)); }

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


