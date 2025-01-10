
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
import java.util.Date;
import java.util.Objects;



@Entity
@Table(name = "fichafaltas")
public class FichaFaltas implements Serializable {

private static final long serialVersionUID = -5828754215317273175L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "fechaFalta", updatable = true, nullable = true, length = 200)
		private Date fechaFalta;


		@Column(name = "razon", updatable = true, nullable = true, length = 1000)
		private String razon;


		@Column(name = "retorno", updatable = true, nullable = true, length = 200)
		private Date retorno;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Date getFechaFalta() { 
			return fechaFalta;
		}
		public void setFechaFalta(Date  fechaFalta) {
			this.fechaFalta = fechaFalta;
		}
		public String getRazon() { 
			return razon;
		}
		public void setRazon(String  razon) {
			this.razon = razon;
		}
		public Date getRetorno() { 
			return retorno;
		}
		public void setRetorno(Date  retorno) {
			this.retorno = retorno;
		}
			public boolean equalsFichaFaltas(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaFaltas fichafaltas = (FichaFaltas) o;
						return 			Objects.equals(id, fichafaltas.id) ||
			Objects.equals(fechaFalta, fichafaltas.fechaFalta) ||
			Objects.equals(razon, fichafaltas.razon) ||
			Objects.equals(retorno, fichafaltas.retorno);

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

