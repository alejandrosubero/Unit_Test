
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

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class TimeAssignedPojo implements Serializable {

	private static final long serialVersionUID = -9035384744686908746L;


	private Long idTimeAssigned;

	
	private Date assignedDate;

	
	private String stateAssigned;// assigned - no assignment

	
	private String stateReport;

	
	private String userCode;

	
	private Long idReport;

	
	private String userAssigned;

	
	public TimeAssignedPojo() {
		super();
	}


	public Long getIdTimeAssigned() {
		return idTimeAssigned;
	}


	public void setIdTimeAssigned(Long idTimeAssigned) {
		this.idTimeAssigned = idTimeAssigned;
	}


	public Date getAssignedDate() {
		return assignedDate;
	}


	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}


	public String getStateAssigned() {
		return stateAssigned;
	}


	public void setStateAssigned(String stateAssigned) {
		this.stateAssigned = stateAssigned;
	}


	public String getStateReport() {
		return stateReport;
	}


	public void setStateReport(String stateReport) {
		this.stateReport = stateReport;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public Long getIdReport() {
		return idReport;
	}


	public void setIdReport(Long idReport) {
		this.idReport = idReport;
	}


	public String getUserAssigned() {
		return userAssigned;
	}


	public void setUserAssigned(String userAssigned) {
		this.userAssigned = userAssigned;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignedDate == null) ? 0 : assignedDate.hashCode());
		result = prime * result + ((idReport == null) ? 0 : idReport.hashCode());
		result = prime * result + ((idTimeAssigned == null) ? 0 : idTimeAssigned.hashCode());
		result = prime * result + ((stateAssigned == null) ? 0 : stateAssigned.hashCode());
		result = prime * result + ((stateReport == null) ? 0 : stateReport.hashCode());
		result = prime * result + ((userAssigned == null) ? 0 : userAssigned.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeAssignedPojo other = (TimeAssignedPojo) obj;
		if (assignedDate == null) {
			if (other.assignedDate != null)
				return false;
		} else if (!assignedDate.equals(other.assignedDate))
			return false;
		if (idReport == null) {
			if (other.idReport != null)
				return false;
		} else if (!idReport.equals(other.idReport))
			return false;
		if (idTimeAssigned == null) {
			if (other.idTimeAssigned != null)
				return false;
		} else if (!idTimeAssigned.equals(other.idTimeAssigned))
			return false;
		if (stateAssigned == null) {
			if (other.stateAssigned != null)
				return false;
		} else if (!stateAssigned.equals(other.stateAssigned))
			return false;
		if (stateReport == null) {
			if (other.stateReport != null)
				return false;
		} else if (!stateReport.equals(other.stateReport))
			return false;
		if (userAssigned == null) {
			if (other.userAssigned != null)
				return false;
		} else if (!userAssigned.equals(other.userAssigned))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		return true;
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
