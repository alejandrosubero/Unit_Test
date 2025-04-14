package com.pts.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "teamGroup")
public class TeamGroup {

	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 5, allocationSize=10000)
	@Column(name = "idTeamGroup", updatable = true, nullable = false, length = 25)
	private Long idTeamGroup;

	@Column(name = "team", updatable = true, nullable = true, length = 200)
	private String team;
	
	@Column(name = "description", updatable = true, nullable = true, length = 200)
	private String description;


	public  TeamGroup() { }
	
	
	public TeamGroup(String team, String description) {
		super();
		this.team = team;
		this.description = description;
	}
	
	
	public TeamGroup(Long id, String team, String description) {
		super();
		this.idTeamGroup = id;
		this.team = team;
		this.description = description;
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
		TeamGroup other = (TeamGroup) obj;
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
