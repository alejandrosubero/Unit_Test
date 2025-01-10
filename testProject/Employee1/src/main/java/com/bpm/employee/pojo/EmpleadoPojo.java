
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

package com.bpm.employee.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;


public class EmpleadoPojo implements Serializable {

    private static final long serialVersionUID = 2522700285565520569L;
    private Long id;
    private String socialSecurityNumber;
    private String name;
    private String lastName;
    private String fullName;
    private String address;
    private String phoneNumber;
    private CargoPojo position;
    private String numeroEmpleado;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private String causaEgreso;
    private Long totalDiasTrabajados;
    private Long totalHorasMes;
    private Long totalHorasYear;
    private Long totalHorasExtrasYear;
    private Date dateLastPay;
    private Long totalHorasFeriadoYear;
    private FichaMedicaPojo informacionMedica;
    private Boolean relief;
    private Boolean activo;
    private Boolean vacation;
    private Boolean medicalRest;
    private ComputerSheetPojo computerSheet;

    private List<FichaDesempenoPojo> listaDesempeno = new ArrayList<>();
    private List<FichaPermisosPojo> permisos = new ArrayList<>();
    private FichaSueldoPojo sueldo;
    private List<FichaPagosPojo> pagos = new ArrayList<>();
    private List<FichaFaltasPojo> faltas = new ArrayList<>();


    public EmpleadoPojo() {
        this.relief= false;
        this.vacation= false;
        this.medicalRest = false;
    }

    public ComputerSheetPojo getComputerSheet() {
        return computerSheet;
    }

    public void setComputerSheet(ComputerSheetPojo computerSheet) {
        this.computerSheet = computerSheet;
    }

    public Boolean isRelief() {
        return relief;
    }

    public void setRelief(Boolean relief) {
        this.relief = relief;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean isVacation() {
        return vacation;
    }

    public void setVacation(Boolean vacation) {
        this.vacation = vacation;
    }

    public Boolean isMedicalRest() {
        return medicalRest;
    }

    public void setMedicalRest(Boolean medicalRest) {
        this.medicalRest = medicalRest;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getCausaEgreso() {
        return causaEgreso;
    }

    public void setCausaEgreso(String causaEgreso) {
        this.causaEgreso = causaEgreso;
    }

    public Long getTotalDiasTrabajados() {
        return totalDiasTrabajados;
    }

    public void setTotalDiasTrabajados(Long totalDiasTrabajados) {
        this.totalDiasTrabajados = totalDiasTrabajados;
    }

    public Long getTotalHorasMes() {
        return totalHorasMes;
    }

    public void setTotalHorasMes(Long totalHorasMes) {
        this.totalHorasMes = totalHorasMes;
    }

    public Long getTotalHorasYear() {
        return totalHorasYear;
    }

    public void setTotalHorasYear(Long totalHorasYear) {
        this.totalHorasYear = totalHorasYear;
    }

    public Long getTotalHorasExtrasYear() {
        return totalHorasExtrasYear;
    }

    public void setTotalHorasExtrasYear(Long totalHorasExtrasYear) {
        this.totalHorasExtrasYear = totalHorasExtrasYear;
    }

    public Date getDateLastPay() {
        return dateLastPay;
    }

    public void setDateLastPay(Date dateLastPay) {
        this.dateLastPay = dateLastPay;
    }

    public Long getTotalHorasFeriadoYear() {
        return totalHorasFeriadoYear;
    }

    public void setTotalHorasFeriadoYear(Long totalHorasFeriadoYear) {
        this.totalHorasFeriadoYear = totalHorasFeriadoYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FichaDesempenoPojo> getlistaDesempeno() {
        return listaDesempeno;
    }

    public void setlistaDesempeno(List<FichaDesempenoPojo> listaDesempeno) {
        this.listaDesempeno = listaDesempeno;
    }

    public List<FichaPermisosPojo> getpermisos() {
        return permisos;
    }

    public void setpermisos(List<FichaPermisosPojo> permisos) {
        this.permisos = permisos;
    }

    public FichaSueldoPojo getsueldo() {
        return sueldo;
    }

    public void setsueldo(FichaSueldoPojo sueldo) {
        this.sueldo = sueldo;
    }

    public List<FichaPagosPojo> getpagos() {
        return pagos;
    }

    public void setpagos(List<FichaPagosPojo> pagos) {
        this.pagos = pagos;
    }

    public List<FichaFaltasPojo> getfaltas() {
        return faltas;
    }

    public void setfaltas(List<FichaFaltasPojo> faltas) {
        this.faltas = faltas;
    }

    public FichaMedicaPojo getinformacionMedica() {
        return informacionMedica;
    }

    public void setinformacionMedica(FichaMedicaPojo informacionMedica) {
        this.informacionMedica = informacionMedica;
    }

    public CargoPojo getPosition() {
        return position;
    }

    public void setPosition(CargoPojo position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoPojo that = (EmpleadoPojo) o;
        return Objects.equals(socialSecurityNumber, that.socialSecurityNumber) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(fullName, that.fullName) && Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(position, that.position) && Objects.equals(numeroEmpleado, that.numeroEmpleado) && Objects.equals(fechaIngreso, that.fechaIngreso) && Objects.equals(fechaEgreso, that.fechaEgreso) && Objects.equals(causaEgreso, that.causaEgreso) && Objects.equals(totalDiasTrabajados, that.totalDiasTrabajados) && Objects.equals(totalHorasMes, that.totalHorasMes) && Objects.equals(totalHorasYear, that.totalHorasYear) && Objects.equals(totalHorasExtrasYear, that.totalHorasExtrasYear) && Objects.equals(dateLastPay, that.dateLastPay) && Objects.equals(totalHorasFeriadoYear, that.totalHorasFeriadoYear) && Objects.equals(id, that.id) && Objects.equals(listaDesempeno, that.listaDesempeno) && Objects.equals(permisos, that.permisos) && Objects.equals(sueldo, that.sueldo) && Objects.equals(pagos, that.pagos) && Objects.equals(faltas, that.faltas) && Objects.equals(informacionMedica, that.informacionMedica) && Objects.equals(relief, that.relief) && Objects.equals(activo, that.activo) && Objects.equals(vacation, that.vacation) && Objects.equals(medicalRest, that.medicalRest) && Objects.equals(computerSheet, that.computerSheet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurityNumber, name, lastName, fullName, address, phoneNumber, position, numeroEmpleado, fechaIngreso, fechaEgreso, causaEgreso, totalDiasTrabajados, totalHorasMes, totalHorasYear, totalHorasExtrasYear, dateLastPay, totalHorasFeriadoYear, id, listaDesempeno, permisos, sueldo, pagos, faltas, informacionMedica, relief, activo, vacation, medicalRest, computerSheet);
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

