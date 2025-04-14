package com.pts.pojo;

import java.io.Serializable;
import java.sql.Date;

public class CommentPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8630905480597409636L;
	
	
	private Long idComment;
	private String userCode;
	private Long idReport;
	private Date dateCreate;
	private Date dateEdit;
	private String comments;

	
	public CommentPojo() {
	}

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
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

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateEdit() {
		return dateEdit;
	}

	public void setDateEdit(Date dateEdit) {
		this.dateEdit = dateEdit;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((dateEdit == null) ? 0 : dateEdit.hashCode());
		result = prime * result + ((idComment == null) ? 0 : idComment.hashCode());
		result = prime * result + ((idReport == null) ? 0 : idReport.hashCode());
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
		CommentPojo other = (CommentPojo) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (dateEdit == null) {
			if (other.dateEdit != null)
				return false;
		} else if (!dateEdit.equals(other.dateEdit))
			return false;
		if (idComment == null) {
			if (other.idComment != null)
				return false;
		} else if (!idComment.equals(other.idComment))
			return false;
		if (idReport == null) {
			if (other.idReport != null)
				return false;
		} else if (!idReport.equals(other.idReport))
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
		return "CommentPojo [idComment=" + idComment + ", userCode=" + userCode + ", idReport=" + idReport
				+ ", dateCreate=" + dateCreate + ", dateEdit=" + dateEdit + ", comments=" + comments + "]";
	}

	
	
	
}
