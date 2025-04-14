package com.pts.serviceImplement;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pts.entitys.TeamGroup;
import com.pts.entitys.User;
import com.pts.pojo.TeamGroupPojo;
import com.pts.repository.TeamGroupRepositorie;
import com.pts.service.TeamGroupService;

@Service
public class TeamGroupServiceImplement implements TeamGroupService {

	@Autowired
	private TeamGroupRepositorie repositorie;
	
	
	
	@Override
	public List<TeamGroupPojo> getAllTeam() {
		try {
			List<TeamGroupPojo> listaTeam = new ArrayList<TeamGroupPojo>();
			repositorie.findAll().forEach(team -> listaTeam.add(new ModelMapper().map(team, TeamGroupPojo.class)));
			return listaTeam;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	  
	
	@Override
	public Boolean saveTeamGroup(String team, String description) {
		try {
			if (this.findByTeam(team) == null) {
				repositorie.save(new TeamGroup(team, description));
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public TeamGroup findByTeam(String team) {
		try {
			TeamGroup teamExist = repositorie.findByTeam(team);
			return teamExist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public void deleteTeam(String team) {
		TeamGroup teamExist = this.findByTeam(team);
		if (teamExist != null) {
			repositorie.delete(teamExist);
		}	
	}
	
	
}
