
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
import com.bpm.employee.mapper.FichaPermisosMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaPermisosPojo;
import com.bpm.employee.validation.FichaPermisosValidation;
import com.bpm.employee.service.FichaPermisosService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichapermisos")
public class FichaPermisosController {

    @Autowired
    FichaPermisosService fichapermisosService;

    @Autowired
    FichaPermisosValidation fichapermisosValidationService;

    @Autowired
    FichaPermisosMapper fichapermisosMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getfechasalida/{fechasalida}")
        private  ResponseEntity<EntityRespone> findByFechaSalida(@PathVariable("fechasalida") Date  fechasalida) {
        Date busca = (Date) fichapermisosValidationService.validation(fechasalida);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapermisosService.findByFechaSalida(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getfecharetorno/{fecharetorno}")
        private  ResponseEntity<EntityRespone> findByFechaRetorno(@PathVariable("fecharetorno") Date  fecharetorno) {
        Date busca = (Date) fichapermisosValidationService.validation(fecharetorno);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapermisosService.findByFechaRetorno(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gethorasalida/{horasalida}")
        private  ResponseEntity<EntityRespone> findByHoraSalida(@PathVariable("horasalida") Date  horasalida) {
        Date busca = (Date) fichapermisosValidationService.validation(horasalida);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapermisosService.findByHoraSalida(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gethoraretorno/{horaretorno}")
        private  ResponseEntity<EntityRespone> findByHoraRetorno(@PathVariable("horaretorno") Date  horaretorno) {
        Date busca = (Date) fichapermisosValidationService.validation(horaretorno);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapermisosService.findByHoraRetorno(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettipopermiso/{tipopermiso}")
        private  ResponseEntity<EntityRespone> findByTipoPermiso(@PathVariable("tipopermiso") String  tipopermiso) {
        String busca = (String) fichapermisosValidationService.validation(tipopermiso);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapermisosService.findByTipoPermiso(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getmotivo/{motivo}")
        private  ResponseEntity<EntityRespone> findByMotivo(@PathVariable("motivo") String  motivo) {
        String busca = (String) fichapermisosValidationService.validation(motivo);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapermisosService.findByMotivo(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getfechasalidacontain/{fechasalida}")
        private ResponseEntity<EntityRespone> findByFechaSalidaContain(@PathVariable("fechasalida") Date  fechasalida) {
              Date busca = (Date) fichapermisosValidationService.validation(fechasalida);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapermisosService.findByFechaSalidaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getfecharetornocontain/{fecharetorno}")
        private ResponseEntity<EntityRespone> findByFechaRetornoContain(@PathVariable("fecharetorno") Date  fecharetorno) {
              Date busca = (Date) fichapermisosValidationService.validation(fecharetorno);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapermisosService.findByFechaRetornoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gethorasalidacontain/{horasalida}")
        private ResponseEntity<EntityRespone> findByHoraSalidaContain(@PathVariable("horasalida") Date  horasalida) {
              Date busca = (Date) fichapermisosValidationService.validation(horasalida);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapermisosService.findByHoraSalidaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gethoraretornocontain/{horaretorno}")
        private ResponseEntity<EntityRespone> findByHoraRetornoContain(@PathVariable("horaretorno") Date  horaretorno) {
              Date busca = (Date) fichapermisosValidationService.validation(horaretorno);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapermisosService.findByHoraRetornoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettipopermisocontain/{tipopermiso}")
        private ResponseEntity<EntityRespone> findByTipoPermisoContain(@PathVariable("tipopermiso") String  tipopermiso) {
              String busca = (String) fichapermisosValidationService.validation(tipopermiso);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapermisosService.findByTipoPermisoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getmotivocontain/{motivo}")
        private ResponseEntity<EntityRespone> findByMotivoContain(@PathVariable("motivo") String  motivo) {
              String busca = (String) fichapermisosValidationService.validation(motivo);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapermisosService.findByMotivoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetFichaPermisos/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichapermisosService.findById(fichapermisosValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaPermisos")
        private  ResponseEntity<EntityRespone> getAllFichaPermisos(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichapermisosService.getAllFichaPermisos());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaPermisos(@RequestBody FichaPermisosPojo fichapermisos){
            return fichapermisosService.saveFichaPermisos(fichapermisosMapper.pojoToEntity(fichapermisosValidationService.valida(fichapermisos)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaPermisos(@RequestBody FichaPermisosPojo  fichapermisos){ 
        fichapermisosService.updateFichaPermisos(fichapermisosMapper.pojoToEntity(fichapermisosValidationService.valida(fichapermisos)));
            return fichapermisos.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaPermisos(@RequestBody FichaPermisosPojo  fichapermisos){ 
            return fichapermisosService.saveOrUpdateFichaPermisos(fichapermisosMapper.pojoToEntity(fichapermisosValidationService.valida(fichapermisos)) ); }


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


