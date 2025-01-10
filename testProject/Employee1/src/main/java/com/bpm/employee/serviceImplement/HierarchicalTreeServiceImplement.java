
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

import com.bpm.employee.entitys.HierarchicalTree;
import com.bpm.employee.mapper.HierarchicalTreeMapper;
import com.bpm.employee.pojo.HierarchicalTreePojo;
import com.bpm.employee.service.HierarchicalTreeService;
import com.bpm.employee.repository.HierarchicalTreeRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class HierarchicalTreeServiceImplement implements HierarchicalTreeService {

    protected static final Log logger = LogFactory.getLog(HierarchicalTreeServiceImplement.class);
    @Autowired
    private HierarchicalTreeRepository hierarchicaltreerepository;

    @Autowired
    private HierarchicalTreeMapper hierarchicaltreeMapper;




    @Override
    public HierarchicalTreePojo findByPositioName(String positioName) {
        logger.info("Starting getHierarchicalTree");
        HierarchicalTree hierarchicaltreeEntity = new HierarchicalTree();
        Optional<HierarchicalTree> fileOptional1 = hierarchicaltreerepository.findByPositioName(positioName);
        if (fileOptional1.isPresent()) {
            try {
                hierarchicaltreeEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return hierarchicaltreeMapper.entityToPojo(hierarchicaltreeEntity);
    }

    @Override
    public HierarchicalTreePojo findByPositioCode(String positioCode) {
        logger.info("Starting findByPositioCode");
        HierarchicalTree hierarchicaltreeEntity = new HierarchicalTree();
        Optional<HierarchicalTree> fileOptional1 = hierarchicaltreerepository.findByPositioCode(positioCode);
        if (fileOptional1.isPresent()) {
            try {
                hierarchicaltreeEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return hierarchicaltreeMapper.entityToPojo(hierarchicaltreeEntity);
    }

    @Override
    public HierarchicalTreePojo findByPositionNumber(Integer positionNumber) {
        logger.info("Starting getHierarchicalTree");
        HierarchicalTree hierarchicaltreeEntity = new HierarchicalTree();
        Optional<HierarchicalTree> fileOptional1 = hierarchicaltreerepository.findByPositionNumber(positionNumber);
        if (fileOptional1.isPresent()) {
            try {
                hierarchicaltreeEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return hierarchicaltreeMapper.entityToPojo(hierarchicaltreeEntity);
    }

    @Override
    public HierarchicalTreePojo findByAreaOrDivision(String areaOrDivision) {
        logger.info("Starting getHierarchicalTree");
        HierarchicalTree hierarchicaltreeEntity = new HierarchicalTree();
        Optional<HierarchicalTree> fileOptional1 = hierarchicaltreerepository.findByAreaDivision(areaOrDivision);
        if (fileOptional1.isPresent()) {
            try {
                hierarchicaltreeEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return hierarchicaltreeMapper.entityToPojo(hierarchicaltreeEntity);
    }

    @Override
    public HierarchicalTreePojo findBySubAreaOrDivision(String subAreaOrDivision) {
        logger.info("Starting getHierarchicalTree");
        HierarchicalTree hierarchicaltreeEntity = new HierarchicalTree();
        Optional<HierarchicalTree> fileOptional1 = hierarchicaltreerepository.findBySubAreaDivision(subAreaOrDivision);

        if (fileOptional1.isPresent()) {
            try {
                hierarchicaltreeEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return hierarchicaltreeMapper.entityToPojo(hierarchicaltreeEntity);
    }


    @Override
    public List<HierarchicalTreePojo> getAllHierarchicalTree() {
        logger.info("execute> getAllHierarchicalTreeGet allProyect");
        List<HierarchicalTreePojo> listaHierarchicalTree = new ArrayList<HierarchicalTreePojo>();
        hierarchicaltreerepository.findAll().forEach(hierarchicaltree -> listaHierarchicalTree.add(hierarchicaltreeMapper.entityToPojo(hierarchicaltree)));
        return listaHierarchicalTree;
    }


    @Override
    public boolean saveHierarchicalTree(HierarchicalTree hierarchicaltree) {
        logger.info("Save Proyect");
        try {
            hierarchicaltreerepository.save(hierarchicaltree);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean updateHierarchicalTree(HierarchicalTree hierarchicaltree) {
        logger.info("Update ENTITY");
        boolean clave = false;
        HierarchicalTree empre = hierarchicaltreerepository.findById(hierarchicaltree.getId()).get();
        empre = hierarchicaltree;
        try {
            hierarchicaltreerepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public HierarchicalTreePojo findById(Long id) {
        return hierarchicaltreeMapper.entityToPojo(hierarchicaltreerepository.findById(id).get());
    }


    @Override
    public boolean saveOrUpdateHierarchicalTree(HierarchicalTree hierarchicaltree) {
        logger.info("Update Proyect");
        boolean clave = false;
        Optional<HierarchicalTree> fileOptional2 = null;
        if(hierarchicaltree.getId() != null){
            fileOptional2 = hierarchicaltreerepository.findById(hierarchicaltree.getId());
        }

        if (fileOptional2 != null && fileOptional2.isPresent()) {
            clave = this.updateHierarchicalTree(hierarchicaltree);
            logger.info(" is update");
        } else {
            clave = this.saveHierarchicalTree(this.setNewHierarchicalTreeConfiguration(hierarchicaltree));
            logger.info(" is save a new HierarchicalTree");
        }
        return clave;
    }

    private HierarchicalTree setNewHierarchicalTreeConfiguration(HierarchicalTree hierarchicaltree){

        hierarchicaltree.setActivo(true);

        if(hierarchicaltree.getSubAreaOrDivision() == null){
            hierarchicaltree.setSubAreaOrDivision("none");
        }

        hierarchicaltree.setAreaOrDivision(hierarchicaltree.getAreaOrDivision().toUpperCase());
        hierarchicaltree.setSubAreaOrDivision(hierarchicaltree.getSubAreaOrDivision().toUpperCase());
        hierarchicaltree.setPositioCode(hierarchicaltree.getPositioCode().toUpperCase());
        hierarchicaltree.setPositioName(hierarchicaltree.getPositioName().toUpperCase());
        logger.info("The new HierarchicalTree was prepare...");
        return hierarchicaltree;
    }


    @Override
    public boolean saveListHierarchicalTree(List<HierarchicalTree> hierarchicaltree) {
        Boolean response = false;
        if(hierarchicaltree != null){
            for (HierarchicalTree element :hierarchicaltree) {
                response = this.saveOrUpdateHierarchicalTree(element);
            }
        }
        return response;
    }

    @Override
    public HierarchicalTreePojo findByAreaDivisionAndSubAreaDivisionAndPositionCode(String areaOrDivision, String subAreaDivision, String positioCode) {
        HierarchicalTreePojo response = null;
        if(areaOrDivision != null && subAreaDivision!= null && positioCode != null){
            response = hierarchicaltreeMapper.entityToPojo(
                    this.hierarchicaltreerepository.findByAreaDivisionAndSubAreaDivisionAndPositioCode(
                            areaOrDivision,
                            subAreaDivision,
                            positioCode).get()
            );
        }
        return response;
    }


    @Override
    public List<HierarchicalTreePojo> findByAreaDivisionAndSubAreaDivisionAndPositionNumber(String areaOrDivision, String subAreaDivision, Integer positionNumber) {
        List<HierarchicalTreePojo>  response = null;
        
        if(areaOrDivision != null && subAreaDivision!= null && !subAreaDivision.equals("NONE") && positionNumber != null){
            response = hierarchicaltreeMapper.entityListToPojoList(
                    this.hierarchicaltreerepository.findByAreaDivisionAndSubAreaDivisionAndPositionNumber(areaOrDivision, subAreaDivision, positionNumber));
        } else {
        	  response = hierarchicaltreeMapper.entityListToPojoList(
        	this.hierarchicaltreerepository.findByAreaDivisionAndPositionNumber(areaOrDivision, positionNumber)
        	);
        }
        return response;
    }

    @Override
    public List<HierarchicalTreePojo> findByHierarchyOfAreas(Integer hierarchyOfAreas) {
        return hierarchicaltreeMapper.entityListToPojoList(hierarchicaltreerepository.findByHierarchyOfAreas(hierarchyOfAreas));
    }


    @Override
    public List<HierarchicalTreePojo> findByPositioNameContaining(String positioname) {
        logger.info("Execute PositioName Containing");
        List<HierarchicalTreePojo> listaHierarchicalTree = new ArrayList<HierarchicalTreePojo>();
        List<HierarchicalTree> listaSHierarchicalTree = hierarchicaltreerepository.findByPositioNameContaining(positioname);
        listaSHierarchicalTree.forEach(hierarchicaltreex -> listaHierarchicalTree.add(hierarchicaltreeMapper.entityToPojo(hierarchicaltreex)));
        return listaHierarchicalTree;
    }


    @Override
    public List<HierarchicalTreePojo> findByPositionNumberContaining(Integer positionnumber) {
        logger.info("Execute PositionNumber Containing");
        List<HierarchicalTreePojo> listaHierarchicalTree = new ArrayList<HierarchicalTreePojo>();
        List<HierarchicalTree> listaSHierarchicalTree = hierarchicaltreerepository.findByPositionNumberContaining(positionnumber);
        listaSHierarchicalTree.forEach(hierarchicaltreex -> listaHierarchicalTree.add(hierarchicaltreeMapper.entityToPojo(hierarchicaltreex)));
        return listaHierarchicalTree;
    }


    @Override
    public List<HierarchicalTreePojo> findByAreaOrDivisionContaining(String areaordivision) {
        logger.info("Execute AreaOrDivision Containing");
        List<HierarchicalTreePojo> listaHierarchicalTree = new ArrayList<HierarchicalTreePojo>();
        List<HierarchicalTree> listaSHierarchicalTree = hierarchicaltreerepository.findByAreaDivisionContaining(areaordivision);
        listaSHierarchicalTree.forEach(hierarchicaltreex -> listaHierarchicalTree.add(hierarchicaltreeMapper.entityToPojo(hierarchicaltreex)));
        return listaHierarchicalTree;
    }


    @Override
    public List<HierarchicalTreePojo> findBySubAreaOrDivisionContaining(String subareaordivision) {
        logger.info("Execute SubAreaOrDivision Containing");
        List<HierarchicalTreePojo> listaHierarchicalTree = new ArrayList<HierarchicalTreePojo>();
        List<HierarchicalTree> listaSHierarchicalTree = hierarchicaltreerepository.findBySubAreaDivisionContaining(subareaordivision);
        listaSHierarchicalTree.forEach(hierarchicaltreex -> listaHierarchicalTree.add(hierarchicaltreeMapper.entityToPojo(hierarchicaltreex)));
        return listaHierarchicalTree;
    }

	@Override
	public List<Long> findMaxPositionNumberAreaDivisionAndPositioCode(String areaDivision) {
		return hierarchicaltreerepository.findMaxPositionNumberAreaDivisionAndPositioCode(areaDivision);
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