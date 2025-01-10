
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



public class FichaHorasExtrasPojo implements Serializable {

private static final long serialVersionUID = 565846828137086328L;

		private Long id;

		private Date fecha;

		private Long horas;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Date getFecha() { 
			return fecha;
		}
		public void setFecha(Date  fecha) {
			this.fecha = fecha;
		}
		public Long getHoras() { 
			return horas;
		}
		public void setHoras(Long  horas) {
			this.horas = horas;
		}
			public boolean equalsFichaHorasExtrasPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					FichaHorasExtrasPojo fichahorasextraspojo = (FichaHorasExtrasPojo) o;
						return 			Objects.equals(id, fichahorasextraspojo.id) ||
			Objects.equals(fecha, fichahorasextraspojo.fecha) ||
			Objects.equals(horas, fichahorasextraspojo.horas);

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

