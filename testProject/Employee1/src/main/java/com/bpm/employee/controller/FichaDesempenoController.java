
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
import com.bpm.employee.mapper.FichaDesempenoMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaDesempenoPojo;
import com.bpm.employee.validation.FichaDesempenoValidation;
import com.bpm.employee.service.FichaDesempenoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichadesempeno")
public class FichaDesempenoController {

    @Autowired
    FichaDesempenoService fichadesempenoService;

    @Autowired
    FichaDesempenoValidation fichadesempenoValidationService;

    @Autowired
    FichaDesempenoMapper fichadesempenoMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;





        @GetMapping("/GetFichaDesempeno/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichadesempenoService.findById(fichadesempenoValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaDesempeno")
        private  ResponseEntity<EntityRespone> getAllFichaDesempeno(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichadesempenoService.getAllFichaDesempeno());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaDesempeno(@RequestBody FichaDesempenoPojo fichadesempeno){
            return fichadesempenoService.saveFichaDesempeno(fichadesempenoMapper.pojoToEntity(fichadesempenoValidationService.valida(fichadesempeno)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaDesempeno(@RequestBody FichaDesempenoPojo  fichadesempeno){ 
        fichadesempenoService.updateFichaDesempeno(fichadesempenoMapper.pojoToEntity(fichadesempenoValidationService.valida(fichadesempeno)));
            return fichadesempeno.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaDesempeno(@RequestBody FichaDesempenoPojo  fichadesempeno){ 
            return fichadesempenoService.saveOrUpdateFichaDesempeno(fichadesempenoMapper.pojoToEntity(fichadesempenoValidationService.valida(fichadesempeno)) ); }


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


