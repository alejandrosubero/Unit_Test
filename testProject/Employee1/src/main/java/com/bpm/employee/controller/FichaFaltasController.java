
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



package com.bpm.employee.controller;
import com.bpm.employee.mapper.FichaFaltasMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaFaltasPojo;
import com.bpm.employee.validation.FichaFaltasValidation;
import com.bpm.employee.service.FichaFaltasService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichafaltas")
public class FichaFaltasController {

    @Autowired
    FichaFaltasService fichafaltasService;

    @Autowired
    FichaFaltasValidation fichafaltasValidationService;

    @Autowired
    FichaFaltasMapper fichafaltasMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getfechafalta/{fechafalta}")
        private  ResponseEntity<EntityRespone> findByFechaFalta(@PathVariable("fechafalta") Date  fechafalta) {
        Date busca = (Date) fichafaltasValidationService.validation(fechafalta);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichafaltasService.findByFechaFalta(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getrazon/{razon}")
        private  ResponseEntity<EntityRespone> findByRazon(@PathVariable("razon") String  razon) {
        String busca = (String) fichafaltasValidationService.validation(razon);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichafaltasService.findByRazon(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getretorno/{retorno}")
        private  ResponseEntity<EntityRespone> findByRetorno(@PathVariable("retorno") Date  retorno) {
        Date busca = (Date) fichafaltasValidationService.validation(retorno);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichafaltasService.findByRetorno(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getfechafaltacontain/{fechafalta}")
        private ResponseEntity<EntityRespone> findByFechaFaltaContain(@PathVariable("fechafalta") Date  fechafalta) {
              Date busca = (Date) fichafaltasValidationService.validation(fechafalta);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichafaltasService.findByFechaFaltaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getrazoncontain/{razon}")
        private ResponseEntity<EntityRespone> findByRazonContain(@PathVariable("razon") String  razon) {
              String busca = (String) fichafaltasValidationService.validation(razon);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichafaltasService.findByRazonContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getretornocontain/{retorno}")
        private ResponseEntity<EntityRespone> findByRetornoContain(@PathVariable("retorno") Date  retorno) {
              Date busca = (Date) fichafaltasValidationService.validation(retorno);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichafaltasService.findByRetornoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetFichaFaltas/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichafaltasService.findById(fichafaltasValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaFaltas")
        private  ResponseEntity<EntityRespone> getAllFichaFaltas(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichafaltasService.getAllFichaFaltas());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaFaltas(@RequestBody FichaFaltasPojo fichafaltas){
            return fichafaltasService.saveFichaFaltas(fichafaltasMapper.pojoToEntity(fichafaltasValidationService.valida(fichafaltas)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaFaltas(@RequestBody FichaFaltasPojo  fichafaltas){ 
        fichafaltasService.updateFichaFaltas(fichafaltasMapper.pojoToEntity(fichafaltasValidationService.valida(fichafaltas)));
            return fichafaltas.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaFaltas(@RequestBody FichaFaltasPojo  fichafaltas){ 
            return fichafaltasService.saveOrUpdateFichaFaltas(fichafaltasMapper.pojoToEntity(fichafaltasValidationService.valida(fichafaltas)) ); }


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


