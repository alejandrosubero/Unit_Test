
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
import com.bpm.employee.mapper.FichaFeriadosTrabajadosMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaFeriadosTrabajadosPojo;
import com.bpm.employee.validation.FichaFeriadosTrabajadosValidation;
import com.bpm.employee.service.FichaFeriadosTrabajadosService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichaferiadostrabajados")
public class FichaFeriadosTrabajadosController {

    @Autowired
    FichaFeriadosTrabajadosService fichaferiadostrabajadosService;

    @Autowired
    FichaFeriadosTrabajadosValidation fichaferiadostrabajadosValidationService;

    @Autowired
    FichaFeriadosTrabajadosMapper fichaferiadostrabajadosMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getfecha/{fecha}")
        private  ResponseEntity<EntityRespone> findByFecha(@PathVariable("fecha") Date  fecha) {
        Date busca = (Date) fichaferiadostrabajadosValidationService.validation(fecha);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichaferiadostrabajadosService.findByFecha(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gethoras/{horas}")
        private  ResponseEntity<EntityRespone> findByHoras(@PathVariable("horas") Long  horas) {
        Long busca = (Long) fichaferiadostrabajadosValidationService.validation(horas);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichaferiadostrabajadosService.findByHoras(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getfechacontain/{fecha}")
        private ResponseEntity<EntityRespone> findByFechaContain(@PathVariable("fecha") Date  fecha) {
              Date busca = (Date) fichaferiadostrabajadosValidationService.validation(fecha);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichaferiadostrabajadosService.findByFechaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gethorascontain/{horas}")
        private ResponseEntity<EntityRespone> findByHorasContain(@PathVariable("horas") Long  horas) {
              Long busca = (Long) fichaferiadostrabajadosValidationService.validation(horas);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichaferiadostrabajadosService.findByHorasContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetFichaFeriadosTrabajados/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichaferiadostrabajadosService.findById(fichaferiadostrabajadosValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaFeriadosTrabajados")
        private  ResponseEntity<EntityRespone> getAllFichaFeriadosTrabajados(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichaferiadostrabajadosService.getAllFichaFeriadosTrabajados());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaFeriadosTrabajados(@RequestBody FichaFeriadosTrabajadosPojo fichaferiadostrabajados){
            return fichaferiadostrabajadosService.saveFichaFeriadosTrabajados(fichaferiadostrabajadosMapper.pojoToEntity(fichaferiadostrabajadosValidationService.valida(fichaferiadostrabajados)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaFeriadosTrabajados(@RequestBody FichaFeriadosTrabajadosPojo  fichaferiadostrabajados){ 
        fichaferiadostrabajadosService.updateFichaFeriadosTrabajados(fichaferiadostrabajadosMapper.pojoToEntity(fichaferiadostrabajadosValidationService.valida(fichaferiadostrabajados)));
            return fichaferiadostrabajados.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaFeriadosTrabajados(@RequestBody FichaFeriadosTrabajadosPojo  fichaferiadostrabajados){ 
            return fichaferiadostrabajadosService.saveOrUpdateFichaFeriadosTrabajados(fichaferiadostrabajadosMapper.pojoToEntity(fichaferiadostrabajadosValidationService.valida(fichaferiadostrabajados)) ); }


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


