

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


package com.bpm.employee.mapper;

import com.bpm.employee.entitys.HierarchicalTree;
import com.bpm.employee.pojo.HierarchicalTreePojo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;

@Component
public class HierarchicalTreeMapper {

    public HierarchicalTreePojo entityToPojo(HierarchicalTree entity) {
//        ModelMapper modelMapper = new ModelMapper();
        HierarchicalTreePojo pojo = null;
        if (entity != null) {
           return new ModelMapper().map(entity, HierarchicalTreePojo.class);
        }
        return pojo;
    }

    public HierarchicalTree pojoToEntity(HierarchicalTreePojo pojo) {
//        ModelMapper modelMapper = new ModelMapper();
        HierarchicalTree entity = null;
        if (pojo != null) {
            return new ModelMapper().map(pojo, HierarchicalTree.class);
        }
        return entity;
    }

    public  List<HierarchicalTreePojo> entityListToPojoList(List<HierarchicalTree> entitys) {
        List<HierarchicalTreePojo> pojos = new ArrayList<HierarchicalTreePojo>();
        if (entitys != null) {
            entitys.stream().forEach(entity -> pojos.add(this.entityToPojo(entity)));
        }
        return pojos;
    }

    public  List<HierarchicalTree> pojoListToEntityList(List<HierarchicalTreePojo> pojos) {
        List<HierarchicalTree> entitys = new ArrayList<HierarchicalTree>();
        if (pojos != null) {
            pojos.stream().forEach(pojo -> entitys.add(this.pojoToEntity(pojo)));
        }
        return entitys;
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


