
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



public class FichaPermisosPojo implements Serializable {

private static final long serialVersionUID = -3819993428527870277L;

		private Long id;

		private Date fechaSalida;

		private Date fechaRetorno;

		private Date horaSalida;

		private Date horaRetorno;

		private String tipoPermiso;

		private String motivo;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Date getFechaSalida() { 
			return fechaSalida;
		}
		public void setFechaSalida(Date  fechaSalida) {
			this.fechaSalida = fechaSalida;
		}
		public Date getFechaRetorno() { 
			return fechaRetorno;
		}
		public void setFechaRetorno(Date  fechaRetorno) {
			this.fechaRetorno = fechaRetorno;
		}
		public Date getHoraSalida() { 
			return horaSalida;
		}
		public void setHoraSalida(Date  horaSalida) {
			this.horaSalida = horaSalida;
		}
		public Date getHoraRetorno() { 
			return horaRetorno;
		}
		public void setHoraRetorno(Date  horaRetorno) {
			this.horaRetorno = horaRetorno;
		}
		public String getTipoPermiso() { 
			return tipoPermiso;
		}
		public void setTipoPermiso(String  tipoPermiso) {
			this.tipoPermiso = tipoPermiso;
		}
		public String getMotivo() { 
			return motivo;
		}
		public void setMotivo(String  motivo) {
			this.motivo = motivo;
		}
			public boolean equalsFichaPermisosPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaPermisosPojo fichapermisospojo = (FichaPermisosPojo) o;
						return 			Objects.equals(id, fichapermisospojo.id) ||
			Objects.equals(fechaSalida, fichapermisospojo.fechaSalida) ||
			Objects.equals(fechaRetorno, fichapermisospojo.fechaRetorno) ||
			Objects.equals(horaSalida, fichapermisospojo.horaSalida) ||
			Objects.equals(horaRetorno, fichapermisospojo.horaRetorno) ||
			Objects.equals(tipoPermiso, fichapermisospojo.tipoPermiso) ||
			Objects.equals(motivo, fichapermisospojo.motivo);

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

