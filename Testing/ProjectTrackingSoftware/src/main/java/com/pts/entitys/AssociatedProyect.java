
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

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "associatedproyect")
public class AssociatedProyect implements Serializable {

	private static final long serialVersionUID = 8577714768873588282L;

	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
	@Column(name = "idAssociatedProyect", updatable = true, nullable = false, length = 25)
	private Long idAssociatedProyect;

	@Column(name = "idProyect", updatable = true, nullable = true, length = 200)
	private Long idProyect;

	@Column(name = "report_id")
	private Long idReport;

	public AssociatedProyect() {
	}

	public Long getIdproyect() {
		return idProyect;
	}

	public void setIdproyect(Long idProyect) {
		this.idProyect = idProyect;
	}

	public Long getIdreport() {
		return idReport;
	}

	public void setIdreport(Long idReport) {
		this.idReport = idReport;
	}

	public Long getIdassociatedproyect() {
		return idAssociatedProyect;
	}

	public void setIdassociatedproyect(Long idAssociatedProyect) {
		this.idAssociatedProyect = idAssociatedProyect;
	}

	public boolean equalsAssociatedProyect(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AssociatedProyect associatedproyect = (AssociatedProyect) o;
		return Objects.equals(idProyect, associatedproyect.idProyect)
				|| Objects.equals(idReport, associatedproyect.idReport)
				|| Objects.equals(idAssociatedProyect, associatedproyect.idAssociatedProyect);

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
