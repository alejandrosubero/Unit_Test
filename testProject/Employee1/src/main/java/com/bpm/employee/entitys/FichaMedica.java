
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
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "fichamedica")
public class FichaMedica implements Serializable {

private static final long serialVersionUID = -5168115538611509704L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "peso", updatable = true, nullable = true, length = 200)
		private Long peso;


		@Column(name = "altura", updatable = true, nullable = true, length = 200)
		private Long altura;


		@Column(name = "genero", updatable = true, nullable = true, length = 200)
		private String genero;


		@Column(name = "etnia", updatable = true, nullable = true, length = 200)
		private String etnia;


		@Column(name = "edad", updatable = true, nullable = true, length = 200)
		private Long edad;


		@Column(name = "seguroMedicoComp", updatable = true, nullable = true, length = 200)
		private String seguroMedicoComp;


		@Column(name = "seguroMedicoPersonal", updatable = true, nullable = true, length = 200)
		private String seguroMedicoPersonal;


		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)

		private   List<Alergia>alergias= new ArrayList<>();

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)

		private   List<Enfermedad>enfermedades= new ArrayList<>();

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
		public List<Alergia> getalergias() { 
			return alergias;
		}
		public void setalergias( List<Alergia>  alergias) {
			this.alergias = alergias;
		}
		public List<Enfermedad> getenfermedades() { 
			return enfermedades;
		}
		public void setenfermedades( List<Enfermedad>  enfermedades) {
			this.enfermedades = enfermedades;
		}
			public boolean equalsFichaMedica(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaMedica fichamedica = (FichaMedica) o;
						return 			Objects.equals(id, fichamedica.id) ||
			Objects.equals(peso, fichamedica.peso) ||
			Objects.equals(altura, fichamedica.altura) ||
			Objects.equals(genero, fichamedica.genero) ||
			Objects.equals(etnia, fichamedica.etnia) ||
			Objects.equals(edad, fichamedica.edad) ||
			Objects.equals(seguroMedicoComp, fichamedica.seguroMedicoComp) ||
			Objects.equals(seguroMedicoPersonal, fichamedica.seguroMedicoPersonal);

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

