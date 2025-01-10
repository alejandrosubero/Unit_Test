
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

package com.bpm.employee.entitys;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(generator = "sequence_cargo_id_generator")
    @SequenceGenerator(name = "sequence_cargo_id_generator", initialValue = 50, allocationSize = 1000)
    @Column(name = "idCargo", updatable = true, nullable = false, length = 25)
    private Long idCargo;

    @Column(name = "code", updatable = true, nullable = true, length = 200)
    private String code;

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "areaDivision", updatable = true, nullable = true, length = 200)
    private String areaDivision;

    @Column(name = "subAreaDivision", updatable = true, nullable = true, length = 200)
    private String subAreaDivision;

    public Cargo() {
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
        Cargo cargo = (Cargo) o;
        return Objects.equals(idCargo, cargo.idCargo) ||
                Objects.equals(code, cargo.code) ||
                Objects.equals(description, cargo.description) ||
                Objects.equals(name, cargo.name) ||
                Objects.equals(areaDivision, cargo.areaDivision) ||
                Objects.equals(subAreaDivision, cargo.subAreaDivision);

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

