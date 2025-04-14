
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



public class PrioritysPojo implements Serializable {

private static final long serialVersionUID = 8881994119156730561L;

		private Long idPriority;

		private String value;

		private String description;

		public Long getIdpriority() { 
			return idPriority;
		}
		public void setIdpriority(Long  idPriority) {
			this.idPriority = idPriority;
		}
		public String getValue() { 
			return value;
		}
		public void setValue(String  value) {
			this.value = value;
		}
		public String getDescription() { 
			return description;
		}
		public void setDescription(String  description) {
			this.description = description;
		}
			public boolean equalsPrioritysPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					PrioritysPojo priorityspojo = (PrioritysPojo) o;
						return 			Objects.equals(idPriority, priorityspojo.idPriority) ||
			Objects.equals(value, priorityspojo.value) ||
			Objects.equals(description, priorityspojo.description);

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

