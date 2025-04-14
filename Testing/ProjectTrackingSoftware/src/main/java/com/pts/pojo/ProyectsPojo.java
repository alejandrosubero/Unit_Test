
/*
Create on Sun Nov 07 14:17:04 ART 2021
*Copyright (C) 121.
@author Alejandro Subero
@author Subero Alejandro
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: This project tracking software </p>
*/

package com.pts.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;



public class ProyectsPojo implements Serializable {

private static final long serialVersionUID = 6994512381574801080L;

		private Long idProyect;

		private String titleProyect;

		private String description;

		public Long getIdproyect() { 
			return idProyect;
		}
		public void setIdproyect(Long  idProyect) {
			this.idProyect = idProyect;
		}
		public String getTitleproyect() { 
			return titleProyect;
		}
		public void setTitleproyect(String  titleProyect) {
			this.titleProyect = titleProyect;
		}
		public String getDescription() { 
			return description;
		}
		public void setDescription(String  description) {
			this.description = description;
		}
			public boolean equalsProyectsPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					ProyectsPojo proyectspojo = (ProyectsPojo) o;
						return 			Objects.equals(idProyect, proyectspojo.idProyect) ||
			Objects.equals(titleProyect, proyectspojo.titleProyect) ||
			Objects.equals(description, proyectspojo.description);

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

