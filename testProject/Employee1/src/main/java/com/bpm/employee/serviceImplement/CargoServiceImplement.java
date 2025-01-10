
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


package com.bpm.employee.serviceImplement;

import com.bpm.employee.entitys.Cargo;
import com.bpm.employee.mapper.CargoMapper;
import com.bpm.employee.pojo.CargoPojo;
import com.bpm.employee.service.CargoService;
import com.bpm.employee.repository.CargoRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class CargoServiceImplement implements CargoService {

    protected static final Log logger = LogFactory.getLog(CargoServiceImplement.class);
    @Autowired
    private CargoRepository cargorepository;

    @Autowired
    private CargoMapper cargoMapper;


    @Override
    public CargoPojo findByCode(String code) {

        logger.info("Starting getCargo");
        Cargo cargoEntity = new Cargo();
        Optional<Cargo> fileOptional1 = cargorepository.findByCode(code);

        if (fileOptional1.isPresent()) {

            try {
                cargoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return cargoMapper.entityToPojo(cargoEntity);
    }

    @Override
    public CargoPojo findByDescription(String description) {

        logger.info("Starting getCargo");
        Cargo cargoEntity = new Cargo();
        Optional<Cargo> fileOptional1 = cargorepository.findByDescription(description);

        if (fileOptional1.isPresent()) {

            try {
                cargoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return cargoMapper.entityToPojo(cargoEntity);
    }

    @Override
    public CargoPojo findByName(String name) {

        logger.info("Starting getCargo");
        Cargo cargoEntity = new Cargo();
        Optional<Cargo> fileOptional1 = cargorepository.findByName(name);

        if (fileOptional1.isPresent()) {

            try {
                cargoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return cargoMapper.entityToPojo(cargoEntity);
    }

    @Override
    public CargoPojo findByAreaOrDivision(String areaOrDivision) {

        logger.info("Starting getCargo");
        Cargo cargoEntity = new Cargo();
        Optional<Cargo> fileOptional1 = cargorepository.findByAreaDivision(areaOrDivision);

        if (fileOptional1.isPresent()) {

            try {
                cargoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return cargoMapper.entityToPojo(cargoEntity);
    }

    @Override
    public CargoPojo findBySubAreaOrDivision(String subAreaOrDivision) {

        logger.info("Starting getCargo");
        Cargo cargoEntity = new Cargo();
        Optional<Cargo> fileOptional1 = cargorepository.findBySubAreaDivision(subAreaOrDivision);

        if (fileOptional1.isPresent()) {

            try {
                cargoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return cargoMapper.entityToPojo(cargoEntity);
    }


    @Override
    public List<CargoPojo> getAllCargo() {
        logger.info("execute> getAllCargoGet allProyect");
        List<CargoPojo> listaCargo = new ArrayList<CargoPojo>();
        cargorepository.findAll().forEach(cargo -> listaCargo.add(cargoMapper.entityToPojo(cargo)));
        return listaCargo;
    }


    @Override
    public boolean saveCargo(Cargo cargo) {
        logger.info("Save Proyect");


        try {
            cargorepository.save(cargo);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean updateCargo(Cargo cargo) {
        logger.info("Update ENTITY");
        boolean clave = false;
        Cargo empre = cargorepository.findById(cargo.getIdCargo()).get();
        empre = cargo;

        try {
            cargorepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }

        return clave;
    }


    @Override
    public CargoPojo findById(Long id) {
        return cargoMapper.entityToPojo(cargorepository.findById(id).get());
    }


    @Override
    public boolean saveOrUpdateCargo(Cargo cargo) {
        logger.info("Update Proyect");
        boolean clave = false;
        Optional<Cargo> fileOptional2 = cargorepository.findById(cargo.getIdCargo());
        if (fileOptional2.isPresent()) {
            clave = this.updateCargo(cargo);
            logger.info(" is update");
        } else {
            clave = this.saveCargo(cargo);
            logger.info(" is save");
        }
        return clave;
    }


    @Override
    public List<CargoPojo> findByCodeContaining(String code) {
        logger.info("Execute Code Containing");
        List<CargoPojo> listaCargo = new ArrayList<CargoPojo>();
        List<Cargo> listaSCargo = cargorepository.findByCodeContaining(code);
        listaSCargo.forEach(cargox -> listaCargo.add(cargoMapper.entityToPojo(cargox)));
        return listaCargo;
    }


    @Override
    public List<CargoPojo> findByDescriptionContaining(String description) {
        logger.info("Execute Description Containing");
        List<CargoPojo> listaCargo = new ArrayList<CargoPojo>();
        List<Cargo> listaSCargo = cargorepository.findByDescriptionContaining(description);
        listaSCargo.forEach(cargox -> listaCargo.add(cargoMapper.entityToPojo(cargox)));
        return listaCargo;
    }


    @Override
    public List<CargoPojo> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<CargoPojo> listaCargo = new ArrayList<CargoPojo>();
        List<Cargo> listaSCargo = cargorepository.findByNameContaining(name);
        listaSCargo.forEach(cargox -> listaCargo.add(cargoMapper.entityToPojo(cargox)));
        return listaCargo;
    }


    @Override
    public List<CargoPojo> findByAreaOrDivisionContaining(String areaordivision) {
        logger.info("Execute AreaOrDivision Containing");
        List<CargoPojo> listaCargo = new ArrayList<CargoPojo>();
        List<Cargo> listaSCargo = cargorepository.findByAreaDivisionContaining(areaordivision);
        listaSCargo.forEach(cargox -> listaCargo.add(cargoMapper.entityToPojo(cargox)));
        return listaCargo;
    }


    @Override
    public List<CargoPojo> findBySubAreaOrDivisionContaining(String subareaordivision) {
        logger.info("Execute SubAreaOrDivision Containing");
        List<CargoPojo> listaCargo = new ArrayList<CargoPojo>();
        List<Cargo> listaSCargo = cargorepository.findBySubAreaDivisionContaining(subareaordivision);
        listaSCargo.forEach(cargox -> listaCargo.add(cargoMapper.entityToPojo(cargox)));
        return listaCargo;
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


}
