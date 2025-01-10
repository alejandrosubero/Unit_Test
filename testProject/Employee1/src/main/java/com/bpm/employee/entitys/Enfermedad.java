
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
@Table(name = "enfermedad")
public class Enfermedad implements Serializable {

private static final long serialVersionUID = -6243775711358168401L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "name", updatable = true, nullable = true, length = 200)
		private String name;


		@Column(name = "dateEnfermedad", updatable = true, nullable = true, length = 200)
		private Date dateEnfermedad;


		@Column(name = "dianostico", updatable = true, nullable = true, length = 2000)
		private String dianostico;


		@Column(name = "notas", updatable = true, nullable = true, length = 2000)
		private String notas;


		@Column(name = "cronica", updatable = true, nullable = true, length = 200)
		private Boolean cronica;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getName() { 
			return name;
		}
		public void setName(String  name) {
			this.name = name;
		}
		public Date getDateEnfermedad() { 
			return dateEnfermedad;
		}
		public void setDateEnfermedad(Date  dateEnfermedad) {
			this.dateEnfermedad = dateEnfermedad;
		}
		public String getDianostico() { 
			return dianostico;
		}
		public void setDianostico(String  dianostico) {
			this.dianostico = dianostico;
		}
		public String getNotas() { 
			return notas;
		}
		public void setNotas(String  notas) {
			this.notas = notas;
		}
		public Boolean getCronica() { 
			return cronica;
		}
		public void setCronica(Boolean  cronica) {
			this.cronica = cronica;
		}
			public boolean equalsEnfermedad(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Enfermedad enfermedad = (Enfermedad) o;
						return 			Objects.equals(id, enfermedad.id) ||
			Objects.equals(name, enfermedad.name) ||
			Objects.equals(dateEnfermedad, enfermedad.dateEnfermedad) ||
			Objects.equals(dianostico, enfermedad.dianostico) ||
			Objects.equals(notas, enfermedad.notas) ||
			Objects.equals(cronica, enfermedad.cronica);

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

