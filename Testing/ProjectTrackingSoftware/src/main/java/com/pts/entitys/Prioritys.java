
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

package com.pts.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "prioritys")
public class Prioritys implements Serializable {

	private static final long serialVersionUID = 5934772779138017404L;

	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
	@Column(name = "idPriority", updatable = true, nullable = false, length = 25)
	private Long idPriority;

	@Column(name = "value", updatable = true, nullable = true, length = 200)
	private String value;

	@Column(name = "description", updatable = true, nullable = true, length = 200)
	private String description;

	public Long getIdpriority() {
		return idPriority;
	}

	public void setIdpriority(Long idPriority) {
		this.idPriority = idPriority;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equalsPrioritys(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Prioritys prioritys = (Prioritys) o;
		return Objects.equals(idPriority, prioritys.idPriority) || Objects.equals(value, prioritys.value)
				|| Objects.equals(description, prioritys.description);

	}
}
/*
 * Copyright (C) 2008 Google Inc. Licensed to the Apache Software Foundation
 * (ASF) under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to You under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
