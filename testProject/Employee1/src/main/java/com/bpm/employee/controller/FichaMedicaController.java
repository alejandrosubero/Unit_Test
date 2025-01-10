
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
import com.bpm.employee.entitys.FichaMedica;
import com.bpm.employee.mapper.AlergiaMapper;
import com.bpm.employee.mapper.EnfermedadMapper;
import com.bpm.employee.mapper.FichaMedicaMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.AlergiaPojo;
import com.bpm.employee.pojo.EnfermedadPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.FichaMedicaPojo;
import com.bpm.employee.validation.AlergiaValidation;
import com.bpm.employee.validation.EnfermedadValidation;
import com.bpm.employee.validation.FichaMedicaValidation;
import com.bpm.employee.service.FichaMedicaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fichamedica")
public class FichaMedicaController {

    @Autowired
    FichaMedicaService fichamedicaService;

    @Autowired
    FichaMedicaValidation fichamedicaValidationService;

    @Autowired
    FichaMedicaMapper fichamedicaMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

    @Autowired
    AlergiaValidation alergiaValidationService;

    @Autowired
    AlergiaMapper alergiaMapper;

    @Autowired
    EnfermedadValidation enfermedadValidationService;

    @Autowired
    EnfermedadMapper enfermedadMapper;



        @GetMapping("/Getpeso/{peso}")
        private  ResponseEntity<EntityRespone> findByPeso(@PathVariable("peso") Long  peso) {
        Long busca = (Long) fichamedicaValidationService.validation(peso);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findByPeso(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getaltura/{altura}")
        private  ResponseEntity<EntityRespone> findByAltura(@PathVariable("altura") Long  altura) {
        Long busca = (Long) fichamedicaValidationService.validation(altura);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findByAltura(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getgenero/{genero}")
        private  ResponseEntity<EntityRespone> findByGenero(@PathVariable("genero") String  genero) {
        String busca = (String) fichamedicaValidationService.validation(genero);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findByGenero(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getetnia/{etnia}")
        private  ResponseEntity<EntityRespone> findByEtnia(@PathVariable("etnia") String  etnia) {
        String busca = (String) fichamedicaValidationService.validation(etnia);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findByEtnia(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getedad/{edad}")
        private  ResponseEntity<EntityRespone> findByEdad(@PathVariable("edad") Long  edad) {
        Long busca = (Long) fichamedicaValidationService.validation(edad);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findByEdad(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getseguromedicocomp/{seguromedicocomp}")
        private  ResponseEntity<EntityRespone> findBySeguroMedicoComp(@PathVariable("seguromedicocomp") String  seguromedicocomp) {
        String busca = (String) fichamedicaValidationService.validation(seguromedicocomp);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findBySeguroMedicoComp(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getseguromedicopersonal/{seguromedicopersonal}")
        private  ResponseEntity<EntityRespone> findBySeguroMedicoPersonal(@PathVariable("seguromedicopersonal") String  seguromedicopersonal) {
        String busca = (String) fichamedicaValidationService.validation(seguromedicopersonal);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findBySeguroMedicoPersonal(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getpesocontain/{peso}")
        private ResponseEntity<EntityRespone> findByPesoContain(@PathVariable("peso") Long  peso) {
              Long busca = (Long) fichamedicaValidationService.validation(peso);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.findByPesoContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getalturacontain/{altura}")
        private ResponseEntity<EntityRespone> findByAlturaContain(@PathVariable("altura") Long  altura) {
              Long busca = (Long) fichamedicaValidationService.validation(altura);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.findByAlturaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getgenerocontain/{genero}")
        private ResponseEntity<EntityRespone> findByGeneroContain(@PathVariable("genero") String  genero) {
              String busca = (String) fichamedicaValidationService.validation(genero);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.findByGeneroContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getetniacontain/{etnia}")
        private ResponseEntity<EntityRespone> findByEtniaContain(@PathVariable("etnia") String  etnia) {
              String busca = (String) fichamedicaValidationService.validation(etnia);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.findByEtniaContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getedadcontain/{edad}")
        private ResponseEntity<EntityRespone> findByEdadContain(@PathVariable("edad") Long  edad) {
              Long busca = (Long) fichamedicaValidationService.validation(edad);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.findByEdadContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getseguromedicocompcontain/{seguromedicocomp}")
        private ResponseEntity<EntityRespone> findBySeguroMedicoCompContain(@PathVariable("seguromedicocomp") String  seguromedicocomp) {
              String busca = (String) fichamedicaValidationService.validation(seguromedicocomp);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.findBySeguroMedicoCompContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getseguromedicopersonalcontain/{seguromedicopersonal}")
        private ResponseEntity<EntityRespone> findBySeguroMedicoPersonalContain(@PathVariable("seguromedicopersonal") String  seguromedicopersonal) {
              String busca = (String) fichamedicaValidationService.validation(seguromedicopersonal);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.findBySeguroMedicoPersonalContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetFichaMedica/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(fichamedicaService.findById(fichamedicaValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllFichaMedica")
        private  ResponseEntity<EntityRespone> getAllFichaMedica(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(fichamedicaService.getAllFichaMedica());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveFichaMedica(@RequestBody FichaMedicaPojo fichamedica){
            return fichamedicaService.saveFichaMedica(fichamedicaMapper.pojoToEntity(fichamedicaValidationService.valida(fichamedica)) ); }



        @PostMapping("/Update")
        private Long UpdateFichaMedica(@RequestBody FichaMedicaPojo  fichamedica){ 
        fichamedicaService.updateFichaMedica(fichamedicaMapper.pojoToEntity(fichamedicaValidationService.valida(fichamedica)));
            return fichamedica.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateFichaMedica(@RequestBody FichaMedicaPojo  fichamedica){ 
            return fichamedicaService.saveOrUpdateFichaMedica(fichamedicaMapper.pojoToEntity(fichamedicaValidationService.valida(fichamedica)) ); }



        @PostMapping("/Get_alergias_contain/")
        private List<FichaMedica> findByAlergia(@RequestBody AlergiaPojo alergia){
            return fichamedicaService.findByAlergiaContaining(alergiaMapper.pojoToEntity(alergiaValidationService.valida(alergia))); }


        @PostMapping("/Get_enfermedades_contain/")
        private List<FichaMedica> findByEnfermedad(@RequestBody EnfermedadPojo enfermedad){
            return fichamedicaService.findByEnfermedadContaining(enfermedadMapper.pojoToEntity(enfermedadValidationService.valida(enfermedad))); }

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


