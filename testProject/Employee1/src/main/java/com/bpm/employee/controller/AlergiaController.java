
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
import com.bpm.employee.mapper.AlergiaMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.AlergiaPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.validation.AlergiaValidation;
import com.bpm.employee.service.AlergiaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alergia")
public class AlergiaController {

    @Autowired
    AlergiaService alergiaService;

    @Autowired
    AlergiaValidation alergiaValidationService;

    @Autowired
    AlergiaMapper alergiaMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Gettipo/{tipo}")
        private  ResponseEntity<EntityRespone> findByTipo(@PathVariable("tipo") String  tipo) {
        String busca = (String) alergiaValidationService.validation(tipo);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(alergiaService.findByTipo(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getcausal/{causal}")
        private  ResponseEntity<EntityRespone> findByCausal(@PathVariable("causal") String  causal) {
        String busca = (String) alergiaValidationService.validation(causal);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(alergiaService.findByCausal(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getmedicamento/{medicamento}")
        private  ResponseEntity<EntityRespone> findByMedicamento(@PathVariable("medicamento") String  medicamento) {
        String busca = (String) alergiaValidationService.validation(medicamento);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(alergiaService.findByMedicamento(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Gettipocontain/{tipo}")
        private ResponseEntity<EntityRespone> findByTipoContain(@PathVariable("tipo") String  tipo) {
              String busca = (String) alergiaValidationService.validation(tipo);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(alergiaService.findByTipoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getcausalcontain/{causal}")
        private ResponseEntity<EntityRespone> findByCausalContain(@PathVariable("causal") String  causal) {
              String busca = (String) alergiaValidationService.validation(causal);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(alergiaService.findByCausalContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getmedicamentocontain/{medicamento}")
        private ResponseEntity<EntityRespone> findByMedicamentoContain(@PathVariable("medicamento") String  medicamento) {
              String busca = (String) alergiaValidationService.validation(medicamento);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(alergiaService.findByMedicamentoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetAlergia/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(alergiaService.findById(alergiaValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllAlergia")
        private  ResponseEntity<EntityRespone> getAllAlergia(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(alergiaService.getAllAlergia());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveAlergia(@RequestBody AlergiaPojo alergia){
            return alergiaService.saveAlergia(alergiaMapper.pojoToEntity(alergiaValidationService.valida(alergia)) ); }



        @PostMapping("/Update")
        private Long UpdateAlergia(@RequestBody AlergiaPojo  alergia){ 
        alergiaService.updateAlergia(alergiaMapper.pojoToEntity(alergiaValidationService.valida(alergia)));
            return alergia.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateAlergia(@RequestBody AlergiaPojo  alergia){ 
            return alergiaService.saveOrUpdateAlergia(alergiaMapper.pojoToEntity(alergiaValidationService.valida(alergia)) ); }


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


