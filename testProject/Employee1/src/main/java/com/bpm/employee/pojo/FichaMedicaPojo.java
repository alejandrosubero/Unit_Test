
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
import java.util.ArrayList;
import java.util.Objects;


public class FichaMedicaPojo implements Serializable {

private static final long serialVersionUID = -4343659384292304054L;

		private Long id;

		private Long peso;

		private Long altura;

		private String genero;

		private String etnia;

		private Long edad;

		private String seguroMedicoComp;

		private String seguroMedicoPersonal;

		private   List<AlergiaPojo>alergias= new ArrayList<>();


		private   List<EnfermedadPojo>enfermedades= new ArrayList<>();


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Long getPeso() { 
			return peso;
		}
		public void setPeso(Long  peso) {
			this.peso = peso;
		}
		public Long getAltura() { 
			return altura;
		}
		public void setAltura(Long  altura) {
			this.altura = altura;
		}
		public String getGenero() { 
			return genero;
		}
		public void setGenero(String  genero) {
			this.genero = genero;
		}
		public String getEtnia() { 
			return etnia;
		}
		public void setEtnia(String  etnia) {
			this.etnia = etnia;
		}
		public Long getEdad() { 
			return edad;
		}
		public void setEdad(Long  edad) {
			this.edad = edad;
		}
		public String getSeguroMedicoComp() { 
			return seguroMedicoComp;
		}
		public void setSeguroMedicoComp(String  seguroMedicoComp) {
			this.seguroMedicoComp = seguroMedicoComp;
		}
		public String getSeguroMedicoPersonal() { 
			return seguroMedicoPersonal;
		}
		public void setSeguroMedicoPersonal(String  seguroMedicoPersonal) {
			this.seguroMedicoPersonal = seguroMedicoPersonal;
		}
		public List<AlergiaPojo> getalergias() { 
			return alergias;
		}
		public void setalergias( List<AlergiaPojo>  alergias) {
			this.alergias = alergias;
		}
		public List<EnfermedadPojo> getenfermedades() { 
			return enfermedades;
		}
		public void setenfermedades( List<EnfermedadPojo>  enfermedades) {
			this.enfermedades = enfermedades;
		}
			public boolean equalsFichaMedicaPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaMedicaPojo fichamedicapojo = (FichaMedicaPojo) o;
						return 			Objects.equals(id, fichamedicapojo.id) ||
			Objects.equals(peso, fichamedicapojo.peso) ||
			Objects.equals(altura, fichamedicapojo.altura) ||
			Objects.equals(genero, fichamedicapojo.genero) ||
			Objects.equals(etnia, fichamedicapojo.etnia) ||
			Objects.equals(edad, fichamedicapojo.edad) ||
			Objects.equals(seguroMedicoComp, fichamedicapojo.seguroMedicoComp) ||
			Objects.equals(seguroMedicoPersonal, fichamedicapojo.seguroMedicoPersonal);

}}
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

