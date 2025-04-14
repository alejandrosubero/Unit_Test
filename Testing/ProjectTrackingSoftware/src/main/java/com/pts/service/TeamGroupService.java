package com.pts.service;

import java.util.List;

import com.pts.entitys.TeamGroup;
import com.pts.pojo.TeamGroupPojo;

public interface TeamGroupService {

	public Boolean saveTeamGroup(String Team, String description);
	public TeamGroup findByTeam(String team);
	public void deleteTeam(String team);
	public List<TeamGroupPojo> getAllTeam();
	
}



