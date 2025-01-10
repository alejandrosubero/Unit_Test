
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
import java.util.Date;
import java.util.Objects;


public class FichaPagosPojo implements Serializable {

private static final long serialVersionUID = 1117979672949173461L;

		private Long id;

		private Date fechaPago;

		private String periodoPagado;

		private Long diasTrabajados;

		private Double horasTrabajadas;

		private Long cantidadPagada;

		private DeduccionPojo deducciones;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Date getFechaPago() { 
			return fechaPago;
		}
		public void setFechaPago(Date  fechaPago) {
			this.fechaPago = fechaPago;
		}
		public String getPeriodoPagado() { 
			return periodoPagado;
		}
		public void setPeriodoPagado(String  periodoPagado) {
			this.periodoPagado = periodoPagado;
		}
		public Long getDiasTrabajados() { 
			return diasTrabajados;
		}
		public void setDiasTrabajados(Long  diasTrabajados) {
			this.diasTrabajados = diasTrabajados;
		}
		public Double getHorasTrabajadas() { 
			return horasTrabajadas;
		}
		public void setHorasTrabajadas(Double  horasTrabajadas) {
			this.horasTrabajadas = horasTrabajadas;
		}
		public Long getCantidadPagada() { 
			return cantidadPagada;
		}
		public void setCantidadPagada(Long  cantidadPagada) {
			this.cantidadPagada = cantidadPagada;
		}
		public DeduccionPojo getdeducciones() { 
			return deducciones;
		}
		public void setdeducciones(DeduccionPojo  deducciones) {
			this.deducciones = deducciones;
		}
			public boolean equalsFichaPagosPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaPagosPojo fichapagospojo = (FichaPagosPojo) o;
						return 			Objects.equals(id, fichapagospojo.id) ||
			Objects.equals(fechaPago, fichapagospojo.fechaPago) ||
			Objects.equals(periodoPagado, fichapagospojo.periodoPagado) ||
			Objects.equals(diasTrabajados, fichapagospojo.diasTrabajados) ||
			Objects.equals(horasTrabajadas, fichapagospojo.horasTrabajadas) ||
			Objects.equals(cantidadPagada, fichapagospojo.cantidadPagada);

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

