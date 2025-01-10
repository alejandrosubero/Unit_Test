
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


package com.bpm.employee.service;

import java.util.List;

import com.bpm.employee.entitys.HierarchicalTree;
import com.bpm.employee.pojo.HierarchicalTreePojo;


public interface HierarchicalTreeService {

    public boolean saveListHierarchicalTree(List<HierarchicalTree> hierarchicaltree);
    public HierarchicalTreePojo findByPositioName(String positioName);

    public HierarchicalTreePojo findByPositioCode(String positioCode);

    public HierarchicalTreePojo findByPositionNumber(Integer positionNumber);

    public HierarchicalTreePojo findByAreaOrDivision(String areaOrDivision);

    public HierarchicalTreePojo findBySubAreaOrDivision(String subAreaOrDivision);

    public List<HierarchicalTreePojo> findByPositioNameContaining(String positioName);

    public List<HierarchicalTreePojo> findByPositionNumberContaining(Integer positionNumber);

    public List<HierarchicalTreePojo> findByAreaOrDivisionContaining(String areaOrDivision);

    public List<HierarchicalTreePojo> findBySubAreaOrDivisionContaining(String subAreaOrDivision);

    public HierarchicalTreePojo findById(Long id);

    public boolean saveHierarchicalTree(HierarchicalTree hierarchicaltree);

    public List<HierarchicalTreePojo> getAllHierarchicalTree();

    public boolean updateHierarchicalTree(HierarchicalTree hierarchicaltree);

    public boolean saveOrUpdateHierarchicalTree(HierarchicalTree hierarchicaltree);

    public HierarchicalTreePojo findByAreaDivisionAndSubAreaDivisionAndPositionCode(String areaOrDivision, String subAreaDivision, String positioCode);

    public List<HierarchicalTreePojo> findByAreaDivisionAndSubAreaDivisionAndPositionNumber(String areaOrDivision, String subAreaDivision, Integer positionNumber);

    public List<HierarchicalTreePojo> findByHierarchyOfAreas(Integer hierarchyOfAreas);
    
    public List<Long> findMaxPositionNumberAreaDivisionAndPositioCode(String areaDivision);

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


