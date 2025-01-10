
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
import com.bpm.employee.mapper.EnfermedadMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EnfermedadPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.validation.EnfermedadValidation;
import com.bpm.employee.service.EnfermedadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/enfermedad")
public class EnfermedadController {

    @Autowired
    EnfermedadService enfermedadService;

    @Autowired
    EnfermedadValidation enfermedadValidationService;

    @Autowired
    EnfermedadMapper enfermedadMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getname/{name}")
        private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
        String busca = (String) enfermedadValidationService.validation(name);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(enfermedadService.findByName(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdateenfermedad/{dateenfermedad}")
        private  ResponseEntity<EntityRespone> findByDateEnfermedad(@PathVariable("dateenfermedad") Date  dateenfermedad) {
        Date busca = (Date) enfermedadValidationService.validation(dateenfermedad);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(enfermedadService.findByDateEnfermedad(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdianostico/{dianostico}")
        private  ResponseEntity<EntityRespone> findByDianostico(@PathVariable("dianostico") String  dianostico) {
        String busca = (String) enfermedadValidationService.validation(dianostico);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(enfermedadService.findByDianostico(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getnotas/{notas}")
        private  ResponseEntity<EntityRespone> findByNotas(@PathVariable("notas") String  notas) {
        String busca = (String) enfermedadValidationService.validation(notas);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(enfermedadService.findByNotas(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getcronica/{cronica}")
        private  ResponseEntity<EntityRespone> findByCronica(@PathVariable("cronica") Boolean  cronica) {
        Boolean busca = (Boolean) enfermedadValidationService.validation(cronica);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(enfermedadService.findByCronica(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getnamecontain/{name}")
        private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
              String busca = (String) enfermedadValidationService.validation(name);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(enfermedadService.findByNameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdateenfermedadcontain/{dateenfermedad}")
        private ResponseEntity<EntityRespone> findByDateEnfermedadContain(@PathVariable("dateenfermedad") Date  dateenfermedad) {
              Date busca = (Date) enfermedadValidationService.validation(dateenfermedad);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(enfermedadService.findByDateEnfermedadContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdianosticocontain/{dianostico}")
        private ResponseEntity<EntityRespone> findByDianosticoContain(@PathVariable("dianostico") String  dianostico) {
              String busca = (String) enfermedadValidationService.validation(dianostico);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(enfermedadService.findByDianosticoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getnotascontain/{notas}")
        private ResponseEntity<EntityRespone> findByNotasContain(@PathVariable("notas") String  notas) {
              String busca = (String) enfermedadValidationService.validation(notas);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(enfermedadService.findByNotasContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getcronicacontain/{cronica}")
        private ResponseEntity<EntityRespone> findByCronicaContain(@PathVariable("cronica") Boolean  cronica) {
              Boolean busca = (Boolean) enfermedadValidationService.validation(cronica);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(enfermedadService.findByCronicaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetEnfermedad/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(enfermedadService.findById(enfermedadValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllEnfermedad")
        private  ResponseEntity<EntityRespone> getAllEnfermedad(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(enfermedadService.getAllEnfermedad());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveEnfermedad(@RequestBody EnfermedadPojo enfermedad){
            return enfermedadService.saveEnfermedad(enfermedadMapper.pojoToEntity(enfermedadValidationService.valida(enfermedad)) ); }



        @PostMapping("/Update")
        private Long UpdateEnfermedad(@RequestBody EnfermedadPojo  enfermedad){ 
        enfermedadService.updateEnfermedad(enfermedadMapper.pojoToEntity(enfermedadValidationService.valida(enfermedad)));
            return enfermedad.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateEnfermedad(@RequestBody EnfermedadPojo  enfermedad){ 
            return enfermedadService.saveOrUpdateEnfermedad(enfermedadMapper.pojoToEntity(enfermedadValidationService.valida(enfermedad)) ); }


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


