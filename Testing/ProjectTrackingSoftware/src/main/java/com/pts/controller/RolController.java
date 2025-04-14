
/*
Create on Sun Nov 07 14:17:04 ART 2021
*Copyright (C) 121.
@author Alejandro Subero
@author Subero Alejandro
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: This project tracking software </p>
*/



package com.pts.controller;
import com.pts.entitys.Rol;
import com.pts.validation.RolValidation;
import com.pts.mapper.RolMapper;
import com.pts.service.RolService;
import com.pts.mapper.MapperEntityRespone;
import com.pts.pojo.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.pts.pojo.RolPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    RolService rolService;

    @Autowired
    RolValidation rolValidationService;

    @Autowired
   RolMapper rolMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getrol/{rol}")
        private  ResponseEntity<EntityRespone> findByRol(@PathVariable("rol") String  rol) {
        String busca = (String) rolValidationService.validation(rol);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(rolService.findByRol(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdescription/{description}")
        private  ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String  description) {
        String busca = (String) rolValidationService.validation(description);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(rolService.findByDescription(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getrolcontain/{rol}")
        private ResponseEntity<EntityRespone> findByRolContain(@PathVariable("rol") String  rol) {
              String busca = (String) rolValidationService.validation(rol);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(rolService.findByRolContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdescriptioncontain/{description}")
        private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String  description) {
              String busca = (String) rolValidationService.validation(description);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(rolService.findByDescriptionContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetRol/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(rolService.findById(rolValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllRol")
        private  ResponseEntity<EntityRespone> getAllRol(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(rolService.getAllRol());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveRol(@RequestBody RolPojo  rol){ 
            return rolService.saveRol(rolMapper.pojoToEntity(rolValidationService.valida(rol)) ); }





        @DeleteMapping("/deleteRol/{id}")
            private boolean deleteRol(@PathVariable("id") String id) {
            return rolService.deleteRol(rolValidationService.valida_id(id)); }

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


