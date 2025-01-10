
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
import java.util.Objects;



public class FichaSueldoPojo implements Serializable {

private static final long serialVersionUID = -551539859851699176L;

		private Long tasaHoraFeriado;

		private Long tasaHora;

		private Long tasaDia;

		private Long horasJornadaDia;

		private Long id;

		private String tipoDePago;

		private Long jornadasemanalDias;

		private Long sueldoIntegral;

		private Long totalAnual;

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
			public boolean equalsFichaSueldoPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaSueldoPojo fichasueldopojo = (FichaSueldoPojo) o;
						return 			Objects.equals(tasaHoraFeriado, fichasueldopojo.tasaHoraFeriado) ||
			Objects.equals(tasaHora, fichasueldopojo.tasaHora) ||
			Objects.equals(tasaDia, fichasueldopojo.tasaDia) ||
			Objects.equals(horasJornadaDia, fichasueldopojo.horasJornadaDia) ||
			Objects.equals(id, fichasueldopojo.id) ||
			Objects.equals(tipoDePago, fichasueldopojo.tipoDePago) ||
			Objects.equals(jornadasemanalDias, fichasueldopojo.jornadasemanalDias) ||
			Objects.equals(sueldoIntegral, fichasueldopojo.sueldoIntegral) ||
			Objects.equals(totalAnual, fichasueldopojo.totalAnual) ||
			Objects.equals(tasaHoraExtra, fichasueldopojo.tasaHoraExtra);

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

