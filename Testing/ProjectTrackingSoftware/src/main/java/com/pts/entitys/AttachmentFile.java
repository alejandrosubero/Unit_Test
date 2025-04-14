package com.pts.entitys;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "attachmentFile")
public class AttachmentFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3613819119677875050L;
	
	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
	@Column(name = "idAttachmentFile", updatable = true, nullable = false, length = 25)
	private Long idAttachmentFile;
	
	@Column(name="name", updatable = true, nullable = true, length = 200)
	private String name;
	 
	@Column(name="path", updatable = true, nullable = true, length = 200)
	private String path;
	
	@Column(name="uploadDate", updatable = true, nullable = true, length = 200)
	private Date uploadDate;
	
	@Column(name = "userCode", updatable = true, nullable = true, length = 200)
	private String userCode;

	@Lob
    @Column(name="bic", updatable = true, nullable = true)
    private byte[] bic;
	
	@Column(name = "report_id")
	private Long idReport;

	
	public AttachmentFile() {
		
	}


	public Long getIdAttachmentFile() {
		return idAttachmentFile;
	}


	public void setIdAttachmentFile(Long idAttachmentFile) {
		this.idAttachmentFile = idAttachmentFile;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public byte[] getBic() {
		return bic;
	}


	public void setBic(byte[] bic) {
		this.bic = bic;
	}


	public Long getIdReport() {
		return idReport;
	}


	public void setIdReport(Long idReport) {
		this.idReport = idReport;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bic);
		result = prime * result + ((idAttachmentFile == null) ? 0 : idAttachmentFile.hashCode());
		result = prime * result + ((idReport == null) ? 0 : idReport.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
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
		AttachmentFile other = (AttachmentFile) obj;
		if (!Arrays.equals(bic, other.bic))
			return false;
		if (idAttachmentFile == null) {
			if (other.idAttachmentFile != null)
				return false;
		} else if (!idAttachmentFile.equals(other.idAttachmentFile))
			return false;
		if (idReport == null) {
			if (other.idReport != null)
				return false;
		} else if (!idReport.equals(other.idReport))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
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
		return "AttachmentFile [id=" + idAttachmentFile + ", name=" + name + ", path=" + path
				+ ", uploadDate=" + uploadDate + ", userCode=" + userCode + ", bic=" + Arrays.toString(bic)
				+ ", idReport=" + idReport + "]";
	}
	
	
	
	
	
	
}
