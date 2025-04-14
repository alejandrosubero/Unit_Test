
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
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "timeassigned")
public class TimeAssigned implements Serializable {

	private static final long serialVersionUID = 6610616627140038246L;

	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
	@Column(name = "idTimeAssigned", updatable = true, nullable = false, length = 25)
	private Long idTimeAssigned;

	@Column(name = "assignedDate", updatable = true, nullable = true, length = 200)
	private Date assignedDate;

	@Column(name = "stateAssigned", updatable = true, nullable = true, length = 200)
	private String stateAssigned;// assigned - no assignment

	@Column(name = "stateReport", updatable = true, nullable = true, length = 200)
	private String stateReport;

	@Column(name = "userCode", updatable = true, nullable = true, length = 200)
	private String userCode;

	@Column(name = "report_id")
	private Long idReport;

	@Column(name = "userAssigned", updatable = true, nullable = true, length = 200)
	private String userAssigned;

	
	public TimeAssigned() {

	}
	
	
	public Long getIdtimeassigned() {
		return idTimeAssigned;
	}

	public void setIdtimeassigned(Long idTimeAssigned) {
		this.idTimeAssigned = idTimeAssigned;
	}

	public Date getAssigneddate() {
		return assignedDate;
	}

	public void setAssigneddate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getStateassigned() {
		return stateAssigned;
	}

	public void setStateassigned(String stateAssigned) {
		this.stateAssigned = stateAssigned;
	}

	public String getStatereport() {
		return stateReport;
	}

	public void setStatereport(String stateReport) {
		this.stateReport = stateReport;
	}

	public String getUsercode() {
		return userCode;
	}

	public void setUsercode(String userCode) {
		this.userCode = userCode;
	}

	public Long getIdreport() {
		return idReport;
	}

	public void setIdreport(Long idReport) {
		this.idReport = idReport;
	}

	public String getUserassigned() {
		return userAssigned;
	}

	public void setUserassigned(String userAssigned) {
		this.userAssigned = userAssigned;
	}

	public boolean equalsTimeAssigned(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TimeAssigned timeassigned = (TimeAssigned) o;
		return Objects.equals(idTimeAssigned, timeassigned.idTimeAssigned)
				|| Objects.equals(assignedDate, timeassigned.assignedDate)
				|| Objects.equals(stateAssigned, timeassigned.stateAssigned)
				|| Objects.equals(stateReport, timeassigned.stateReport)
				|| Objects.equals(userCode, timeassigned.userCode) || Objects.equals(idReport, timeassigned.idReport)
				|| Objects.equals(userAssigned, timeassigned.userAssigned);

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
