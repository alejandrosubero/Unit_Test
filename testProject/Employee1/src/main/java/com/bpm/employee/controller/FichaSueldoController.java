
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
import com.bpm.employee.mapper.FichaSueldoMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaSueldoPojo;
import com.bpm.employee.validation.FichaSueldoValidation;
import com.bpm.employee.service.FichaSueldoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichasueldo")
public class FichaSueldoController {

    @Autowired
    FichaSueldoService fichasueldoService;

    @Autowired
    FichaSueldoValidation fichasueldoValidationService;

    @Autowired
    FichaSueldoMapper fichasueldoMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Gettasahoraferiado/{tasahoraferiado}")
        private  ResponseEntity<EntityRespone> findByTasaHoraFeriado(@PathVariable("tasahoraferiado") Long  tasahoraferiado) {
        Long busca = (Long) fichasueldoValidationService.validation(tasahoraferiado);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByTasaHoraFeriado(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettasahora/{tasahora}")
        private  ResponseEntity<EntityRespone> findByTasaHora(@PathVariable("tasahora") Long  tasahora) {
        Long busca = (Long) fichasueldoValidationService.validation(tasahora);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByTasaHora(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettasadia/{tasadia}")
        private  ResponseEntity<EntityRespone> findByTasaDia(@PathVariable("tasadia") Long  tasadia) {
        Long busca = (Long) fichasueldoValidationService.validation(tasadia);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByTasaDia(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gethorasjornadadia/{horasjornadadia}")
        private  ResponseEntity<EntityRespone> findByHorasJornadaDia(@PathVariable("horasjornadadia") Long  horasjornadadia) {
        Long busca = (Long) fichasueldoValidationService.validation(horasjornadadia);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByHorasJornadaDia(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettipodepago/{tipodepago}")
        private  ResponseEntity<EntityRespone> findByTipoDePago(@PathVariable("tipodepago") String  tipodepago) {
        String busca = (String) fichasueldoValidationService.validation(tipodepago);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByTipoDePago(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getjornadasemanaldias/{jornadasemanaldias}")
        private  ResponseEntity<EntityRespone> findByJornadasemanalDias(@PathVariable("jornadasemanaldias") Long  jornadasemanaldias) {
        Long busca = (Long) fichasueldoValidationService.validation(jornadasemanaldias);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByJornadasemanalDias(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getsueldointegral/{sueldointegral}")
        private  ResponseEntity<EntityRespone> findBySueldoIntegral(@PathVariable("sueldointegral") Long  sueldointegral) {
        Long busca = (Long) fichasueldoValidationService.validation(sueldointegral);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findBySueldoIntegral(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettotalanual/{totalanual}")
        private  ResponseEntity<EntityRespone> findByTotalAnual(@PathVariable("totalanual") Long  totalanual) {
        Long busca = (Long) fichasueldoValidationService.validation(totalanual);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByTotalAnual(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettasahoraextra/{tasahoraextra}")
        private  ResponseEntity<EntityRespone> findByTasaHoraExtra(@PathVariable("tasahoraextra") Long  tasahoraextra) {
        Long busca = (Long) fichasueldoValidationService.validation(tasahoraextra);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findByTasaHoraExtra(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Gettasahoraferiadocontain/{tasahoraferiado}")
        private ResponseEntity<EntityRespone> findByTasaHoraFeriadoContain(@PathVariable("tasahoraferiado") Long  tasahoraferiado) {
              Long busca = (Long) fichasueldoValidationService.validation(tasahoraferiado);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByTasaHoraFeriadoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettasahoracontain/{tasahora}")
        private ResponseEntity<EntityRespone> findByTasaHoraContain(@PathVariable("tasahora") Long  tasahora) {
              Long busca = (Long) fichasueldoValidationService.validation(tasahora);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByTasaHoraContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettasadiacontain/{tasadia}")
        private ResponseEntity<EntityRespone> findByTasaDiaContain(@PathVariable("tasadia") Long  tasadia) {
              Long busca = (Long) fichasueldoValidationService.validation(tasadia);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByTasaDiaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gethorasjornadadiacontain/{horasjornadadia}")
        private ResponseEntity<EntityRespone> findByHorasJornadaDiaContain(@PathVariable("horasjornadadia") Long  horasjornadadia) {
              Long busca = (Long) fichasueldoValidationService.validation(horasjornadadia);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByHorasJornadaDiaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettipodepagocontain/{tipodepago}")
        private ResponseEntity<EntityRespone> findByTipoDePagoContain(@PathVariable("tipodepago") String  tipodepago) {
              String busca = (String) fichasueldoValidationService.validation(tipodepago);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByTipoDePagoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getjornadasemanaldiascontain/{jornadasemanaldias}")
        private ResponseEntity<EntityRespone> findByJornadasemanalDiasContain(@PathVariable("jornadasemanaldias") Long  jornadasemanaldias) {
              Long busca = (Long) fichasueldoValidationService.validation(jornadasemanaldias);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByJornadasemanalDiasContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getsueldointegralcontain/{sueldointegral}")
        private ResponseEntity<EntityRespone> findBySueldoIntegralContain(@PathVariable("sueldointegral") Long  sueldointegral) {
              Long busca = (Long) fichasueldoValidationService.validation(sueldointegral);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findBySueldoIntegralContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettotalanualcontain/{totalanual}")
        private ResponseEntity<EntityRespone> findByTotalAnualContain(@PathVariable("totalanual") Long  totalanual) {
              Long busca = (Long) fichasueldoValidationService.validation(totalanual);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByTotalAnualContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettasahoraextracontain/{tasahoraextra}")
        private ResponseEntity<EntityRespone> findByTasaHoraExtraContain(@PathVariable("tasahoraextra") Long  tasahoraextra) {
              Long busca = (Long) fichasueldoValidationService.validation(tasahoraextra);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.findByTasaHoraExtraContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetFichaSueldo/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichasueldoService.findById(fichasueldoValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaSueldo")
        private  ResponseEntity<EntityRespone> getAllFichaSueldo(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichasueldoService.getAllFichaSueldo());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaSueldo(@RequestBody FichaSueldoPojo fichasueldo){
            return fichasueldoService.saveFichaSueldo(fichasueldoMapper.pojoToEntity(fichasueldoValidationService.valida(fichasueldo)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaSueldo(@RequestBody FichaSueldoPojo  fichasueldo){ 
        fichasueldoService.updateFichaSueldo(fichasueldoMapper.pojoToEntity(fichasueldoValidationService.valida(fichasueldo)));
            return fichasueldo.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaSueldo(@RequestBody FichaSueldoPojo  fichasueldo){ 
            return fichasueldoService.saveOrUpdateFichaSueldo(fichasueldoMapper.pojoToEntity(fichasueldoValidationService.valida(fichasueldo)) ); }


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


