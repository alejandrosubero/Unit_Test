
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
@Table(name = "timereport")
public class TimeReport implements Serializable {

private static final long serialVersionUID = -2462812707329335139L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "idTimeReport", updatable = true, nullable = false, length = 25)
		private Long idTimeReport;


		@Column(name = "timeDedicate", updatable = true, nullable = true, length = 200)
		private Date timeDedicate;


		@Column(name = "timeDedicateTotal", updatable = true, nullable = true, length = 200)
		private Date timeDedicateTotal;


		@Column(name = "report_id")
		private Long idReport;


		@Column(name = "userCode", updatable = true, nullable = true, length = 200)
		private String userCode;

		@Column(name = "timeDedicateIn", updatable = true, nullable = true, length = 200)
		private String timeDedicateIn;
		
		
		@Column(name = "comments", updatable = true, nullable = true, length = 4000)
		private String commenst;
		
		
		public  TimeReport() {	}


		public Long getIdTimeReport() {
			return idTimeReport;
		}


		public void setIdTimeReport(Long idTimeReport) {
			this.idTimeReport = idTimeReport;
		}


		public Date getTimeDedicate() {
			return timeDedicate;
		}


		public void setTimeDedicate(Date timeDedicate) {
			this.timeDedicate = timeDedicate;
		}


		public Date getTimeDedicateTotal() {
			return timeDedicateTotal;
		}


		public void setTimeDedicateTotal(Date timeDedicateTotal) {
			this.timeDedicateTotal = timeDedicateTotal;
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


		public String getTimeDedicateIn() {
			return timeDedicateIn;
		}


		public void setTimeDedicateIn(String timeDedicateIn) {
			this.timeDedicateIn = timeDedicateIn;
		}


		public String getCommenst() {
			return commenst;
		}


		public void setCommenst(String commenst) {
			this.commenst = commenst;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((commenst == null) ? 0 : commenst.hashCode());
			result = prime * result + ((idReport == null) ? 0 : idReport.hashCode());
			result = prime * result + ((idTimeReport == null) ? 0 : idTimeReport.hashCode());
			result = prime * result + ((timeDedicate == null) ? 0 : timeDedicate.hashCode());
			result = prime * result + ((timeDedicateIn == null) ? 0 : timeDedicateIn.hashCode());
			result = prime * result + ((timeDedicateTotal == null) ? 0 : timeDedicateTotal.hashCode());
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
			TimeReport other = (TimeReport) obj;
			if (commenst == null) {
				if (other.commenst != null)
					return false;
			} else if (!commenst.equals(other.commenst))
				return false;
			if (idReport == null) {
				if (other.idReport != null)
					return false;
			} else if (!idReport.equals(other.idReport))
				return false;
			if (idTimeReport == null) {
				if (other.idTimeReport != null)
					return false;
			} else if (!idTimeReport.equals(other.idTimeReport))
				return false;
			if (timeDedicate == null) {
				if (other.timeDedicate != null)
					return false;
			} else if (!timeDedicate.equals(other.timeDedicate))
				return false;
			if (timeDedicateIn == null) {
				if (other.timeDedicateIn != null)
					return false;
			} else if (!timeDedicateIn.equals(other.timeDedicateIn))
				return false;
			if (timeDedicateTotal == null) {
				if (other.timeDedicateTotal != null)
					return false;
			} else if (!timeDedicateTotal.equals(other.timeDedicateTotal))
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

