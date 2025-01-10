
/*
Create on Sat Oct 07 21:00:29 EDT 2023
*Copyright (C) 123.
@author yo
@author yo
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Employee’s hamblet </p>
*/


package com.bpm.employee.controller.client;

import com.bpm.employee.mapper.HierarchicalTreeMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.HierarchicalTreePojo;
import com.bpm.employee.validation.HierarchicalTreeValidation;
import com.bpm.employee.service.HierarchicalTreeService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hierarchicaltree")
public class HierarchicalTreeController {


    @Autowired
    private HierarchicalTreeService hierarchicaltreeService;

    @Autowired
    private HierarchicalTreeValidation hierarchicaltreeValidationService;

    @Autowired
    private HierarchicalTreeMapper hierarchicaltreeMapper;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;


    @PostMapping("/save/or/update")
    private boolean saveOrUpdateHierarchicalTree(@RequestBody HierarchicalTreePojo hierarchicaltree) {
        return hierarchicaltreeService.saveOrUpdateHierarchicalTree(
                hierarchicaltreeMapper.pojoToEntity(
                        hierarchicaltreeValidationService.valida(hierarchicaltree))
        );
    }

    @PostMapping("/save/or/update/list")
    private boolean saveOrUpdateHierarchicalTree(@RequestBody List<HierarchicalTreePojo> hierarchicaltree) {
        return hierarchicaltreeService.saveListHierarchicalTree(
                hierarchicaltreeMapper.pojoListToEntityList(
                        hierarchicaltreeValidationService.validaList(hierarchicaltree)
                ));
    }

    @GetMapping("/Getpositioname/{positioname}")
    private ResponseEntity<EntityRespone> findByPositioName(@PathVariable("positioname") String positioname) {
        String busca = (String) hierarchicaltreeValidationService.validation(positioname);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(hierarchicaltreeService.findByPositioName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getpositionnumber/{positionnumber}")
    private ResponseEntity<EntityRespone> findByPositionNumber(@PathVariable("positionnumber") Integer positionnumber) {
        Integer busca = (Integer) hierarchicaltreeValidationService.validation(positionnumber);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(hierarchicaltreeService.findByPositionNumber(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getareaordivision/{areaordivision}")
    private ResponseEntity<EntityRespone> findByAreaOrDivision(@PathVariable("areaordivision") String areaordivision) {
        String busca = (String) hierarchicaltreeValidationService.validation(areaordivision);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(hierarchicaltreeService.findByAreaOrDivision(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getsubareaordivision/{subareaordivision}")
    private ResponseEntity<EntityRespone> findBySubAreaOrDivision(@PathVariable("subareaordivision") String subareaordivision) {
        String busca = (String) hierarchicaltreeValidationService.validation(subareaordivision);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(hierarchicaltreeService.findBySubAreaOrDivision(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/Getpositionamecontain/{positioname}")
    private ResponseEntity<EntityRespone> findByPositioNameContain(@PathVariable("positioname") String positioname) {
        String busca = (String) hierarchicaltreeValidationService.validation(positioname);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(hierarchicaltreeService.findByPositioNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getpositionnumbercontain/{positionnumber}")
    private ResponseEntity<EntityRespone> findByPositionNumberContain(@PathVariable("positionnumber") Integer positionnumber) {
        Integer busca = (Integer) hierarchicaltreeValidationService.validation(positionnumber);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(hierarchicaltreeService.findByPositionNumberContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getareaordivisioncontain/{areaordivision}")
    private ResponseEntity<EntityRespone> findByAreaOrDivisionContain(@PathVariable("areaordivision") String areaordivision) {
        String busca = (String) hierarchicaltreeValidationService.validation(areaordivision);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(hierarchicaltreeService.findByAreaOrDivisionContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getsubareaordivisioncontain/{subareaordivision}")
    private ResponseEntity<EntityRespone> findBySubAreaOrDivisionContain(@PathVariable("subareaordivision") String subareaordivision) {
        String busca = (String) hierarchicaltreeValidationService.validation(subareaordivision);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(hierarchicaltreeService.findBySubAreaOrDivisionContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetHierarchicalTree/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(hierarchicaltreeService.findById(hierarchicaltreeValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetAllHierarchicalTree")
    private ResponseEntity<EntityRespone> getAllHierarchicalTree() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(hierarchicaltreeService.getAllHierarchicalTree());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


//		@PostMapping("/save")
//		private Boolean  saveHierarchicalTree(@RequestBody HierarchicalTreePojo  hierarchicaltree){
//			return hierarchicaltreeService.saveHierarchicalTree(hierarchicaltreeMapper.pojoToEntity(hierarchicaltreeValidationService.valida(hierarchicaltree)) ); }
//
//
//
//		@PostMapping("/Update")
//		private Long UpdateHierarchicalTree(@RequestBody HierarchicalTreePojo  hierarchicaltree){
//			hierarchicaltreeService.updateHierarchicalTree(hierarchicaltreeMapper.pojoToEntity(hierarchicaltreeValidationService.valida(hierarchicaltree)));
//			return hierarchicaltree.getId(); }


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


