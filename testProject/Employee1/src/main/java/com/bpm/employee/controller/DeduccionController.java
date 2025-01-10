
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
import com.bpm.employee.mapper.DeduccionMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.DeduccionPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.service.DeduccionService;
import com.bpm.employee.validation.DeduccionValidation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/deduccion")
public class DeduccionController {

    @Autowired
    DeduccionService deduccionService;

    @Autowired
    DeduccionValidation deduccionValidationService;

    @Autowired
    DeduccionMapper deduccionMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getimpuestofederalrenta/{impuestofederalrenta}")
        private  ResponseEntity<EntityRespone> findByImpuestoFederalRenta(@PathVariable("impuestofederalrenta") Long  impuestofederalrenta) {
        Long busca = (Long) deduccionValidationService.validation(impuestofederalrenta);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(deduccionService.findByImpuestoFederalRenta(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getsocialsecurity/{socialsecurity}")
        private  ResponseEntity<EntityRespone> findBySocialSecurity(@PathVariable("socialsecurity") Long  socialsecurity) {
        Long busca = (Long) deduccionValidationService.validation(socialsecurity);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(deduccionService.findBySocialSecurity(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getmedicare/{medicare}")
        private  ResponseEntity<EntityRespone> findByMedicare(@PathVariable("medicare") Long  medicare) {
        Long busca = (Long) deduccionValidationService.validation(medicare);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(deduccionService.findByMedicare(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getimpuestofederalrentacontain/{impuestofederalrenta}")
        private ResponseEntity<EntityRespone> findByImpuestoFederalRentaContain(@PathVariable("impuestofederalrenta") Long  impuestofederalrenta) {
              Long busca = (Long) deduccionValidationService.validation(impuestofederalrenta);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(deduccionService.findByImpuestoFederalRentaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getsocialsecuritycontain/{socialsecurity}")
        private ResponseEntity<EntityRespone> findBySocialSecurityContain(@PathVariable("socialsecurity") Long  socialsecurity) {
              Long busca = (Long) deduccionValidationService.validation(socialsecurity);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(deduccionService.findBySocialSecurityContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getmedicarecontain/{medicare}")
        private ResponseEntity<EntityRespone> findByMedicareContain(@PathVariable("medicare") Long  medicare) {
              Long busca = (Long) deduccionValidationService.validation(medicare);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(deduccionService.findByMedicareContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetDeduccion/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(deduccionService.findById(deduccionValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllDeduccion")
        private  ResponseEntity<EntityRespone> getAllDeduccion(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(deduccionService.getAllDeduccion());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveDeduccion(@RequestBody DeduccionPojo deduccion){
            return deduccionService.saveDeduccion(deduccionMapper.pojoToEntity(deduccionValidationService.valida(deduccion)) ); }



        @PostMapping("/Update")
        private Long UpdateDeduccion(@RequestBody DeduccionPojo  deduccion){ 
        deduccionService.updateDeduccion(deduccionMapper.pojoToEntity(deduccionValidationService.valida(deduccion)));
            return deduccion.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateDeduccion(@RequestBody DeduccionPojo  deduccion){ 
            return deduccionService.saveOrUpdateDeduccion(deduccionMapper.pojoToEntity(deduccionValidationService.valida(deduccion)) ); }


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


