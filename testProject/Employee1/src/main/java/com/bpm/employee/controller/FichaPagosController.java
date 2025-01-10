
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
import com.bpm.employee.mapper.FichaPagosMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.DeduccionPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaPagosPojo;
import com.bpm.employee.validation.DeduccionValidation;
import com.bpm.employee.validation.FichaPagosValidation;
import com.bpm.employee.service.FichaPagosService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichapagos")
public class FichaPagosController {

    @Autowired
    FichaPagosService fichapagosService;

    @Autowired
    FichaPagosValidation fichapagosValidationService;

    @Autowired
    FichaPagosMapper fichapagosMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

    @Autowired
    DeduccionValidation deduccionValidationService;

    @Autowired
    DeduccionMapper deduccionMapper;



        @GetMapping("/Getfechapago/{fechapago}")
        private  ResponseEntity<EntityRespone> findByFechaPago(@PathVariable("fechapago") Date  fechapago) {
        Date busca = (Date) fichapagosValidationService.validation(fechapago);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapagosService.findByFechaPago(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getperiodopagado/{periodopagado}")
        private  ResponseEntity<EntityRespone> findByPeriodoPagado(@PathVariable("periodopagado") String  periodopagado) {
        String busca = (String) fichapagosValidationService.validation(periodopagado);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapagosService.findByPeriodoPagado(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdiastrabajados/{diastrabajados}")
        private  ResponseEntity<EntityRespone> findByDiasTrabajados(@PathVariable("diastrabajados") Long  diastrabajados) {
        Long busca = (Long) fichapagosValidationService.validation(diastrabajados);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapagosService.findByDiasTrabajados(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gethorastrabajadas/{horastrabajadas}")
        private  ResponseEntity<EntityRespone> findByHorasTrabajadas(@PathVariable("horastrabajadas") Double  horastrabajadas) {
        Double busca = (Double) fichapagosValidationService.validation(horastrabajadas);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapagosService.findByHorasTrabajadas(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getcantidadpagada/{cantidadpagada}")
        private  ResponseEntity<EntityRespone> findByCantidadPagada(@PathVariable("cantidadpagada") Long  cantidadpagada) {
        Long busca = (Long) fichapagosValidationService.validation(cantidadpagada);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapagosService.findByCantidadPagada(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getfechapagocontain/{fechapago}")
        private ResponseEntity<EntityRespone> findByFechaPagoContain(@PathVariable("fechapago") Date  fechapago) {
              Date busca = (Date) fichapagosValidationService.validation(fechapago);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapagosService.findByFechaPagoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getperiodopagadocontain/{periodopagado}")
        private ResponseEntity<EntityRespone> findByPeriodoPagadoContain(@PathVariable("periodopagado") String  periodopagado) {
              String busca = (String) fichapagosValidationService.validation(periodopagado);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapagosService.findByPeriodoPagadoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdiastrabajadoscontain/{diastrabajados}")
        private ResponseEntity<EntityRespone> findByDiasTrabajadosContain(@PathVariable("diastrabajados") Long  diastrabajados) {
              Long busca = (Long) fichapagosValidationService.validation(diastrabajados);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapagosService.findByDiasTrabajadosContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gethorastrabajadascontain/{horastrabajadas}")
        private ResponseEntity<EntityRespone> findByHorasTrabajadasContain(@PathVariable("horastrabajadas") Double  horastrabajadas) {
              Double busca = (Double) fichapagosValidationService.validation(horastrabajadas);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapagosService.findByHorasTrabajadasContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getcantidadpagadacontain/{cantidadpagada}")
        private ResponseEntity<EntityRespone> findByCantidadPagadaContain(@PathVariable("cantidadpagada") Long  cantidadpagada) {
              Long busca = (Long) fichapagosValidationService.validation(cantidadpagada);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapagosService.findByCantidadPagadaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetFichaPagos/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapagosService.findById(fichapagosValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaPagos")
        private  ResponseEntity<EntityRespone> getAllFichaPagos(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapagosService.getAllFichaPagos());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaPagos(@RequestBody FichaPagosPojo fichapagos){
            return fichapagosService.saveFichaPagos(fichapagosMapper.pojoToEntity(fichapagosValidationService.valida(fichapagos)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaPagos(@RequestBody FichaPagosPojo  fichapagos){ 
        fichapagosService.updateFichaPagos(fichapagosMapper.pojoToEntity(fichapagosValidationService.valida(fichapagos)));
            return fichapagos.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaPagos(@RequestBody FichaPagosPojo  fichapagos){ 
            return fichapagosService.saveOrUpdateFichaPagos(fichapagosMapper.pojoToEntity(fichapagosValidationService.valida(fichapagos)) ); }



        @PostMapping("/finddeducciones")
        private ResponseEntity<EntityRespone> findRelacionDeduccion(@RequestBody DeduccionPojo deduccion){
           EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapagosService.findByRelacionDeduccion(deduccionMapper.pojoToEntity(deduccionValidationService.valida(deduccion))));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
}
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


