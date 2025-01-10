
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
import java.util.Objects;



@Entity
@Table(name = "fichasueldo")
public class FichaSueldo implements Serializable {

private static final long serialVersionUID = -3881392946420691554L;

		@Column(name = "tasaHoraFeriado", updatable = true, nullable = true, length = 200)
		private Long tasaHoraFeriado;


		@Column(name = "tasaHora", updatable = true, nullable = true, length = 200)
		private Long tasaHora;


		@Column(name = "tasaDia", updatable = true, nullable = true, length = 200)
		private Long tasaDia;


		@Column(name = "horasJornadaDia", updatable = true, nullable = true, length = 200)
		private Long horasJornadaDia;


		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "tipoDePago", updatable = true, nullable = true, length = 200)
		private String tipoDePago;


		@Column(name = "jornadasemanalDias", updatable = true, nullable = true, length = 20)
		private Long jornadasemanalDias;


		@Column(name = "sueldoIntegral", updatable = true, nullable = true, length = 200)
		private Long sueldoIntegral;


		@Column(name = "totalAnual", updatable = true, nullable = true, length = 200)
		private Long totalAnual;


		@Column(name = "tasaHoraExtra", updatable = true, nullable = true, length = 200)
		private Long tasaHoraExtra;


		public Long getTasaHoraFeriado() { 
			return tasaHoraFeriado;
		}
		public void setTasaHoraFeriado(Long  tasaHoraFeriado) {
			this.tasaHoraFeriado = tasaHoraFeriado;
		}
		public Long getTasaHora() { 
			return tasaHora;
		}
		public void setTasaHora(Long  tasaHora) {
			this.tasaHora = tasaHora;
		}
		public Long getTasaDia() { 
			return tasaDia;
		}
		public void setTasaDia(Long  tasaDia) {
			this.tasaDia = tasaDia;
		}
		public Long getHorasJornadaDia() { 
			return horasJornadaDia;
		}
		public void setHorasJornadaDia(Long  horasJornadaDia) {
			this.horasJornadaDia = horasJornadaDia;
		}
		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getTipoDePago() { 
			return tipoDePago;
		}
		public void setTipoDePago(String  tipoDePago) {
			this.tipoDePago = tipoDePago;
		}
		public Long getJornadasemanalDias() { 
			return jornadasemanalDias;
		}
		public void setJornadasemanalDias(Long  jornadasemanalDias) {
			this.jornadasemanalDias = jornadasemanalDias;
		}
		public Long getSueldoIntegral() { 
			return sueldoIntegral;
		}
		public void setSueldoIntegral(Long  sueldoIntegral) {
			this.sueldoIntegral = sueldoIntegral;
		}
		public Long getTotalAnual() { 
			return totalAnual;
		}
		public void setTotalAnual(Long  totalAnual) {
			this.totalAnual = totalAnual;
		}
		public Long getTasaHoraExtra() { 
			return tasaHoraExtra;
		}
		public void setTasaHoraExtra(Long  tasaHoraExtra) {
			this.tasaHoraExtra = tasaHoraExtra;
		}
			public boolean equalsFichaSueldo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaSueldo fichasueldo = (FichaSueldo) o;
						return 			Objects.equals(tasaHoraFeriado, fichasueldo.tasaHoraFeriado) ||
			Objects.equals(tasaHora, fichasueldo.tasaHora) ||
			Objects.equals(tasaDia, fichasueldo.tasaDia) ||
			Objects.equals(horasJornadaDia, fichasueldo.horasJornadaDia) ||
			Objects.equals(id, fichasueldo.id) ||
			Objects.equals(tipoDePago, fichasueldo.tipoDePago) ||
			Objects.equals(jornadasemanalDias, fichasueldo.jornadasemanalDias) ||
			Objects.equals(sueldoIntegral, fichasueldo.sueldoIntegral) ||
			Objects.equals(totalAnual, fichasueldo.totalAnual) ||
			Objects.equals(tasaHoraExtra, fichasueldo.tasaHoraExtra);

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

