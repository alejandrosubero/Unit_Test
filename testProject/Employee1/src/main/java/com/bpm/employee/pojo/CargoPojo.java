
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

package com.bpm.employee.pojo;


import java.util.Objects;


public class CargoPojo {
    private Long idCargo;
    private String code;
    private String name;
    private String areaDivision;
    private String subAreaDivision;
    private String description;

    public CargoPojo() {
    }

    public CargoPojo(Long idCargo, String code, String name, String areaDivision, String subAreaDivision, String description) {
        this.idCargo = idCargo;
        this.code = code;
        this.name = name;
        this.areaDivision = areaDivision;
        this.subAreaDivision = subAreaDivision;
        this.description = description;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaOrDivision() {
        return areaDivision;
    }

    public void setAreaOrDivision(String areaOrDivision) {
        this.areaDivision = areaOrDivision;
    }

    public String getSubAreaOrDivision() {
        return subAreaDivision;
    }

    public void setSubAreaOrDivision(String subAreaOrDivision) {
        this.subAreaDivision = subAreaOrDivision;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoPojo cargopojo = (CargoPojo) o;
        return Objects.equals(idCargo, cargopojo.idCargo) ||
                Objects.equals(code, cargopojo.code) ||
                Objects.equals(description, cargopojo.description) ||
                Objects.equals(name, cargopojo.name) ||
                Objects.equals(areaDivision, cargopojo.areaDivision) ||
                Objects.equals(subAreaDivision, cargopojo.subAreaDivision);

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

