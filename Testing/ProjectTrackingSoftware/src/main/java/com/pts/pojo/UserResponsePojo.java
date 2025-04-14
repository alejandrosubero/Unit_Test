package com.pts.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pts.entitys.TeamGroup;


public class UserResponsePojo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -533375585482971038L;

	
	private Long idUser;

	private String userCode;

	private String userFirsName;

	private String userLastName;

	private String fullName;

	private String userName;

	private String mail;

	private String imagen;

	private String rol;

	private List<TeamGroup> teamGroup = new ArrayList<TeamGroup>();

	
	public UserResponsePojo() {
	}

	
	public UserResponsePojo(UserPojo user) {
		this.idUser= user.getIdUser();
		this.userCode = user.getUserCode();
		this.userFirsName = user.getUserFirsName();
		this.userLastName=user.getUserLastName();
		this.fullName=user.getFullName();
		this.userName=user.getUserName();
		this.mail=user.getMail();
		this.imagen=user.getImagen();
		this.rol=user.getRol();
		this.teamGroup= user.getTeamGroup();
	}


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getUserFirsName() {
		return userFirsName;
	}


	public void setUserFirsName(String userFirsName) {
		this.userFirsName = userFirsName;
	}


	public String getUserLastName() {
		return userLastName;
	}


	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public List<TeamGroup> getTeamGroup() {
		return teamGroup;
	}


	public void setTeamGroup(List<TeamGroup> teamGroup) {
		this.teamGroup = teamGroup;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result + ((teamGroup == null) ? 0 : teamGroup.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((userFirsName == null) ? 0 : userFirsName.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserResponsePojo other = (UserResponsePojo) obj;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		if (teamGroup == null) {
			if (other.teamGroup != null)
				return false;
		} else if (!teamGroup.equals(other.teamGroup))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (userFirsName == null) {
			if (other.userFirsName != null)
				return false;
		} else if (!userFirsName.equals(other.userFirsName))
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
		
	
	
	
}
