
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
import com.bpm.employee.mapper.FichaHorasExtrasMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaHorasExtrasPojo;
import com.bpm.employee.validation.FichaHorasExtrasValidation;
import com.bpm.employee.service.FichaHorasExtrasService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichahorasextras")
public class FichaHorasExtrasController {

    @Autowired
    FichaHorasExtrasService fichahorasextrasService;

    @Autowired
    FichaHorasExtrasValidation fichahorasextrasValidationService;

    @Autowired
    FichaHorasExtrasMapper fichahorasextrasMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getfecha/{fecha}")
        private  ResponseEntity<EntityRespone> findByFecha(@PathVariable("fecha") Date  fecha) {
        Date busca = (Date) fichahorasextrasValidationService.validation(fecha);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichahorasextrasService.findByFecha(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gethoras/{horas}")
        private  ResponseEntity<EntityRespone> findByHoras(@PathVariable("horas") Long  horas) {
        Long busca = (Long) fichahorasextrasValidationService.validation(horas);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichahorasextrasService.findByHoras(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getfechacontain/{fecha}")
        private ResponseEntity<EntityRespone> findByFechaContain(@PathVariable("fecha") Date  fecha) {
              Date busca = (Date) fichahorasextrasValidationService.validation(fecha);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichahorasextrasService.findByFechaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gethorascontain/{horas}")
        private ResponseEntity<EntityRespone> findByHorasContain(@PathVariable("horas") Long  horas) {
              Long busca = (Long) fichahorasextrasValidationService.validation(horas);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichahorasextrasService.findByHorasContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetFichaHorasExtras/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichahorasextrasService.findById(fichahorasextrasValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaHorasExtras")
        private  ResponseEntity<EntityRespone> getAllFichaHorasExtras(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichahorasextrasService.getAllFichaHorasExtras());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaHorasExtras(@RequestBody FichaHorasExtrasPojo fichahorasextras){
            return fichahorasextrasService.saveFichaHorasExtras(fichahorasextrasMapper.pojoToEntity(fichahorasextrasValidationService.valida(fichahorasextras)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaHorasExtras(@RequestBody FichaHorasExtrasPojo  fichahorasextras){ 
        fichahorasextrasService.updateFichaHorasExtras(fichahorasextrasMapper.pojoToEntity(fichahorasextrasValidationService.valida(fichahorasextras)));
            return fichahorasextras.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaHorasExtras(@RequestBody FichaHorasExtrasPojo  fichahorasextras){ 
            return fichahorasextrasService.saveOrUpdateFichaHorasExtras(fichahorasextrasMapper.pojoToEntity(fichahorasextrasValidationService.valida(fichahorasextras)) ); }


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


