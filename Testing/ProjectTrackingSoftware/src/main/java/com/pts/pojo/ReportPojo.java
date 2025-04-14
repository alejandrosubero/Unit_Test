
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.pts.entitys.AssociatedProyect;
import com.pts.entitys.Comment;
import com.pts.entitys.TimeAssigned;
import com.pts.entitys.TimeReport;
import com.pts.entitys.TypeReport;

public class ReportPojo implements Serializable {

	private static final long serialVersionUID = 7740839724652958342L;

	private Long idReport;

	private String userCode;

	private String userAssigned;

	private String client;

	private String codeReports;

	private String title;

	private String description;

	private String state;

	private String stateReport;

	private Long timeDedicate;

	private Date startDate;

	private Date commitmentDate;

	private String priority;

	private String linkAcceso;

	private TypeReportPojo typeReport;

	private List<CommentPojo> comentarios = new ArrayList<CommentPojo>();
	
	private List<AssociatedProyectPojo> associatedProyects= new ArrayList<AssociatedProyectPojo>();

	private List<TimeReportPojo> times = new ArrayList<TimeReportPojo>();

	private List<TimeAssignedPojo> assigmeds= new ArrayList<TimeAssignedPojo>();

		
	
	public ReportPojo() {
	}
	

	public void setid(Long id) {
		this.idReport = id;
	}


	public Long getIdReport() {
		return idReport;
	}


	public void setIdReport(Long idReport) {
		this.idReport = idReport;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getUserAssigned() {
		return userAssigned;
	}


	public void setUserAssigned(String userAssigned) {
		this.userAssigned = userAssigned;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getCodeReports() {
		return codeReports;
	}


	public void setCodeReports(String codeReports) {
		this.codeReports = codeReports;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getStateReport() {
		return stateReport;
	}


	public void setStateReport(String stateReport) {
		this.stateReport = stateReport;
	}


	public Long getTimeDedicate() {
		return timeDedicate;
	}


	public void setTimeDedicate(Long timeDedicate) {
		this.timeDedicate = timeDedicate;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getCommitmentDate() {
		return commitmentDate;
	}


	public void setCommitmentDate(Date commitmentDate) {
		this.commitmentDate = commitmentDate;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getLinkAcceso() {
		return linkAcceso;
	}


	public void setLinkAcceso(String linkAcceso) {
		this.linkAcceso = linkAcceso;
	}


	public TypeReportPojo getTypeReport() {
		return typeReport;
	}


	public void setTypeReport(TypeReportPojo typeReport) {
		this.typeReport = typeReport;
	}


	public List<CommentPojo> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<CommentPojo> comentarios) {
		this.comentarios = comentarios;
	}


	public List<AssociatedProyectPojo> getAssociatedProyects() {
		return associatedProyects;
	}


	public void setAssociatedProyects(List<AssociatedProyectPojo> associatedProyects) {
		this.associatedProyects = associatedProyects;
	}


	public List<TimeReportPojo> getTimes() {
		return times;
	}


	public void setTimes(List<TimeReportPojo> times) {
		this.times = times;
	}


	public List<TimeAssignedPojo> getAssigmeds() {
		return assigmeds;
	}


	public void setAssigmeds(List<TimeAssignedPojo> assigmeds) {
		this.assigmeds = assigmeds;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assigmeds == null) ? 0 : assigmeds.hashCode());
		result = prime * result + ((associatedProyects == null) ? 0 : associatedProyects.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((codeReports == null) ? 0 : codeReports.hashCode());
		result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + ((commitmentDate == null) ? 0 : commitmentDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idReport == null) ? 0 : idReport.hashCode());
		result = prime * result + ((linkAcceso == null) ? 0 : linkAcceso.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((stateReport == null) ? 0 : stateReport.hashCode());
		result = prime * result + ((timeDedicate == null) ? 0 : timeDedicate.hashCode());
		result = prime * result + ((times == null) ? 0 : times.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((typeReport == null) ? 0 : typeReport.hashCode());
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
		ReportPojo other = (ReportPojo) obj;
		if (assigmeds == null) {
			if (other.assigmeds != null)
				return false;
		} else if (!assigmeds.equals(other.assigmeds))
			return false;
		if (associatedProyects == null) {
			if (other.associatedProyects != null)
				return false;
		} else if (!associatedProyects.equals(other.associatedProyects))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (codeReports == null) {
			if (other.codeReports != null)
				return false;
		} else if (!codeReports.equals(other.codeReports))
			return false;
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (commitmentDate == null) {
			if (other.commitmentDate != null)
				return false;
		} else if (!commitmentDate.equals(other.commitmentDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idReport == null) {
			if (other.idReport != null)
				return false;
		} else if (!idReport.equals(other.idReport))
			return false;
		if (linkAcceso == null) {
			if (other.linkAcceso != null)
				return false;
		} else if (!linkAcceso.equals(other.linkAcceso))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (stateReport == null) {
			if (other.stateReport != null)
				return false;
		} else if (!stateReport.equals(other.stateReport))
			return false;
		if (timeDedicate == null) {
			if (other.timeDedicate != null)
				return false;
		} else if (!timeDedicate.equals(other.timeDedicate))
			return false;
		if (times == null) {
			if (other.times != null)
				return false;
		} else if (!times.equals(other.times))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (typeReport == null) {
			if (other.typeReport != null)
				return false;
		} else if (!typeReport.equals(other.typeReport))
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


	@Override
	public String toString() {
		return "ReportPojo [idReport=" + idReport + ", userCode=" + userCode + ", userAssigned=" + userAssigned
				+ ", client=" + client + ", codeReports=" + codeReports + ", title=" + title + ", description="
				+ description + ", state=" + state + ", stateReport=" + stateReport + ", timeDedicate=" + timeDedicate
				+ ", startDate=" + startDate + ", commitmentDate=" + commitmentDate + ", priority=" + priority
				+ ", linkAcceso=" + linkAcceso + ", typeReport=" + typeReport + ", comentarios=" + comentarios
				+ ", associatedProyects=" + associatedProyects + ", times=" + times + ", assigmeds=" + assigmeds + "]";
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
