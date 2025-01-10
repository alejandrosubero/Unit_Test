
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
@Table(name = "deduccion")
public class Deduccion implements Serializable {

private static final long serialVersionUID = -1093548663780461083L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "impuestoFederalRenta", updatable = true, nullable = true, length = 200)
		private Long impuestoFederalRenta;


		@Column(name = "socialSecurity", updatable = true, nullable = true, length = 200)
		private Long socialSecurity;


		@Column(name = "medicare", updatable = true, nullable = true, length = 200)
		private Long medicare;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Long getImpuestoFederalRenta() { 
			return impuestoFederalRenta;
		}
		public void setImpuestoFederalRenta(Long  impuestoFederalRenta) {
			this.impuestoFederalRenta = impuestoFederalRenta;
		}
		public Long getSocialSecurity() { 
			return socialSecurity;
		}
		public void setSocialSecurity(Long  socialSecurity) {
			this.socialSecurity = socialSecurity;
		}
		public Long getMedicare() { 
			return medicare;
		}
		public void setMedicare(Long  medicare) {
			this.medicare = medicare;
		}
			public boolean equalsDeduccion(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Deduccion deduccion = (Deduccion) o;
						return 			Objects.equals(id, deduccion.id) ||
			Objects.equals(impuestoFederalRenta, deduccion.impuestoFederalRenta) ||
			Objects.equals(socialSecurity, deduccion.socialSecurity) ||
			Objects.equals(medicare, deduccion.medicare);

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

