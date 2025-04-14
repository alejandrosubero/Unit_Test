package com.pts.pojo;

public class TeamGroupPojo {

	private Long idTeamGroup;
	private String team;
	private String description;
	
	
	public TeamGroupPojo() {
	}
	
	
	public Long getIdTeamGroup() {
		return idTeamGroup;
	}
	public void setIdTeamGroup(Long idTeamGroup) {
		this.idTeamGroup = idTeamGroup;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idTeamGroup == null) ? 0 : idTeamGroup.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		TeamGroupPojo other = (TeamGroupPojo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idTeamGroup == null) {
			if (other.idTeamGroup != null)
				return false;
		} else if (!idTeamGroup.equals(other.idTeamGroup))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}


	
	
	
	
}
