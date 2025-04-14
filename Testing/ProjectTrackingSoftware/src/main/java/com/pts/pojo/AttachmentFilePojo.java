package com.pts.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class AttachmentFilePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1786071730394599773L;

	
	private Long idAttachmentFile;

	private String name;
	 
	private String path;
	
	private Date uploadDate;
	
	private String userCode;

    private byte[] bic;
	
	private Long idReport;

	
	public AttachmentFilePojo() {
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
		AttachmentFilePojo other = (AttachmentFilePojo) obj;
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

	
	
	
}
