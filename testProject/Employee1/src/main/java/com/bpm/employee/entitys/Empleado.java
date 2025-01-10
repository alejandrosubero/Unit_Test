
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

package com.bpm.employee.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "employee")
public class Empleado implements Serializable {

    private static final long serialVersionUID = -6169463333971334844L;

    @Id
    @GeneratedValue(generator = "sequence_mat_e_id_generator")
    @SequenceGenerator(name = "sequence_mat_e_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "id", updatable = true, nullable = false, length = 25)
    private Long id;

    @Column(name = "socialSecurityNumber", updatable = true, nullable = true, length = 200)
    private String socialSecurityNumber;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "lastName", updatable = true, nullable = true, length = 200)
    private String lastName;

    @Column(name = "fullName", updatable = true, nullable = true, length = 200)
    private String fullName;

    @Column(name = "address", updatable = true, nullable = true, length = 200)
    private String address;

    @Column(name = "phoneNumber", updatable = true, nullable = true, length = 200)
    private String phoneNumber;

    @Column(name = "numeroEmpleado", updatable = true, nullable = true, length = 200)
    private String numeroEmpleado;

    @Column(name = "fechaIngreso", updatable = true, nullable = true, length = 200)
    private Date fechaIngreso;

    @Column(name = "fechaEgreso", updatable = true, nullable = true, length = 200)
    private Date fechaEgreso;

    @Column(name = "causaEgreso", updatable = true, nullable = true, length = 200)
    private String causaEgreso;

    @Column(name = "totalDiasTrabajados", updatable = true, nullable = true, length = 200)
    private Long totalDiasTrabajados;

    @Column(name = "totalHorasMes", updatable = true, nullable = true, length = 200)
    private Long totalHorasMes;

    @Column(name = "totalHorasYear", updatable = true, nullable = true, length = 200)
    private Long totalHorasYear;

    @Column(name = "totalHorasExtrasYear", updatable = true, nullable = true, length = 200)
    private Long totalHorasExtrasYear;

    @Column(name = "dateLastPay", updatable = true, nullable = true, length = 200)
    private Date dateLastPay;

    @Column(name = "totalHorasFeriadoYear", updatable = true, nullable = true, length = 200)
    private Long totalHorasFeriadoYear;

    @Column(name = "activo", updatable = true, nullable = true)
    private Boolean activo;

    @Column(name = "vacation", updatable = true, nullable = true)
    private Boolean vacation;

    @Column(name = "medicalRest", updatable = true, nullable = true)
    private Boolean medicalRest;

    @Column(name = "relief", updatable = true, nullable = true)
    private Boolean relief;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "employee_id_listaDesempeno")
    private List<FichaDesempeno> listaDesempeno = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "employee_id_permisos")
    private List<FichaPermisos> permisos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id_sueldo")
    private FichaSueldo sueldo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "employee_id_pagos")
    private List<FichaPagos> pagos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "employee_id_faltas")
    private List<FichaFaltas> faltas = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id_informacionMedica")
    private FichaMedica informacionMedica;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCargo")
    private Cargo position;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idComputerSheet")
    private ComputerSheet computerSheet;

    public Empleado() {
    }

    public ComputerSheet getComputerSheet() {
        return computerSheet;
    }

    public void setComputerSheet(ComputerSheet computerSheet) {
        this.computerSheet = computerSheet;
    }

    public Empleado(Empleado employee) {

//        if (employee.getId()!= null)
//        this.id = employee.getId();

        if (employee.getSocialSecurityNumber() != null)
            this.socialSecurityNumber = employee.getSocialSecurityNumber();

        if (employee.getName() != null)
            this.name = employee.getName();

        if (employee.getLastName() != null)
            this.lastName = employee.getLastName();

        if (employee.getFullName() != null)
            this.fullName = employee.getFullName();

        if (employee.getAddress() != null)
            this.address = employee.getAddress();

        if (employee.getPhoneNumber() != null)
            this.phoneNumber = employee.getPhoneNumber();

        if (employee.getNumeroEmpleado() != null)
            this.numeroEmpleado = employee.getNumeroEmpleado();

        if (employee.getFechaIngreso() != null)
            this.fechaIngreso = employee.getFechaIngreso();

        if (employee.getFechaEgreso() != null)
            this.fechaEgreso = employee.getFechaEgreso();

        if (employee.getCausaEgreso() != null)
            this.causaEgreso = employee.getCausaEgreso();

        if (employee.getTotalDiasTrabajados() != null)
            this.totalDiasTrabajados = employee.getTotalDiasTrabajados();

        if (employee.getTotalHorasMes() != null)
            this.totalHorasMes = employee.getTotalHorasMes();

        if (employee.getTotalHorasYear() != null)
            this.totalHorasYear = employee.getTotalHorasYear();

        if (employee.getTotalHorasExtrasYear() != null)
            this.totalHorasExtrasYear = employee.getTotalHorasExtrasYear();

        if (employee.getDateLastPay() != null)
            this.dateLastPay = employee.getDateLastPay();

        if (employee.getTotalHorasFeriadoYear() != null)
            this.totalHorasFeriadoYear = employee.getTotalHorasFeriadoYear();

        if (employee.getlistaDesempeno() != null)
            this.listaDesempeno = employee.getlistaDesempeno();

        if (employee.getpermisos() != null)
            this.permisos = employee.getpermisos();

        if (employee.getsueldo() != null)
            this.sueldo = employee.getsueldo();

        if (employee.getpagos() != null)
            this.pagos = employee.getpagos();

        if (employee.getfaltas() != null)
            this.faltas = employee.getfaltas();

        if (employee.getinformacionMedica() != null)
            this.informacionMedica = employee.getinformacionMedica();

        if (employee.getPosition() != null)
            this.position = employee.getPosition();
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

    public Cargo getPosition() {
        return position;
    }

    public void setPosition(Cargo position) {
        this.position = position;
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

    public List<FichaDesempeno> getlistaDesempeno() {
        return listaDesempeno;
    }

    public void setlistaDesempeno(List<FichaDesempeno> listaDesempeno) {
        this.listaDesempeno = listaDesempeno;
    }

    public List<FichaPermisos> getpermisos() {
        return permisos;
    }

    public void setpermisos(List<FichaPermisos> permisos) {
        this.permisos = permisos;
    }

    public FichaSueldo getsueldo() {
        return sueldo;
    }

    public void setsueldo(FichaSueldo sueldo) {
        this.sueldo = sueldo;
    }

    public List<FichaPagos> getpagos() {
        return pagos;
    }

    public void setpagos(List<FichaPagos> pagos) {
        this.pagos = pagos;
    }

    public List<FichaFaltas> getfaltas() {
        return faltas;
    }

    public void setfaltas(List<FichaFaltas> faltas) {
        this.faltas = faltas;
    }

    public FichaMedica getinformacionMedica() {
        return informacionMedica;
    }

    public void setinformacionMedica(FichaMedica informacionMedica) {
        this.informacionMedica = informacionMedica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id) &&
                Objects.equals(socialSecurityNumber, empleado.socialSecurityNumber) &&
                Objects.equals(name, empleado.name) &&
                Objects.equals(lastName, empleado.lastName) &&
                Objects.equals(fullName, empleado.fullName) &&
                Objects.equals(address, empleado.address) &&
                Objects.equals(phoneNumber, empleado.phoneNumber) &&
                Objects.equals(numeroEmpleado, empleado.numeroEmpleado) &&
                Objects.equals(fechaIngreso, empleado.fechaIngreso) &&
                Objects.equals(fechaEgreso, empleado.fechaEgreso) &&
                Objects.equals(causaEgreso, empleado.causaEgreso) &&
                Objects.equals(totalDiasTrabajados, empleado.totalDiasTrabajados) &&
                Objects.equals(totalHorasMes, empleado.totalHorasMes) &&
                Objects.equals(totalHorasYear, empleado.totalHorasYear) &&
                Objects.equals(totalHorasExtrasYear, empleado.totalHorasExtrasYear) &&
                Objects.equals(dateLastPay, empleado.dateLastPay) &&
                Objects.equals(totalHorasFeriadoYear, empleado.totalHorasFeriadoYear) &&
                Objects.equals(listaDesempeno, empleado.listaDesempeno) &&
                Objects.equals(permisos, empleado.permisos) &&
                Objects.equals(sueldo, empleado.sueldo) &&
                Objects.equals(pagos, empleado.pagos) &&
                Objects.equals(faltas, empleado.faltas) &&
                Objects.equals(informacionMedica, empleado.informacionMedica) &&
                Objects.equals(position, empleado.position) &&
                Objects.equals(activo, empleado.activo) &&
                Objects.equals(vacation, empleado.vacation) &&
                Objects.equals(medicalRest, empleado.medicalRest) &&
                Objects.equals(relief, empleado.relief);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, socialSecurityNumber, name, lastName, fullName, address, phoneNumber, numeroEmpleado, fechaIngreso, fechaEgreso, causaEgreso, totalDiasTrabajados, totalHorasMes, totalHorasYear, totalHorasExtrasYear, dateLastPay, totalHorasFeriadoYear, listaDesempeno, permisos, sueldo, pagos, faltas, informacionMedica, position, activo, vacation, medicalRest, relief);
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

