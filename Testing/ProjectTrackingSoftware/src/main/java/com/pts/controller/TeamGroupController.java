package com.pts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.mapper.MapperEntityRespone;
import com.pts.pojo.EntityRespone;
import com.pts.service.TeamGroupService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/team")
public class TeamGroupController {

	@Autowired
	private TeamGroupService teamGroupService;

	@Autowired
	private MapperEntityRespone mapperEntityRespone;

	@GetMapping("/save")
	public ResponseEntity<Boolean> greeting(@RequestHeader("team") String team,
			@RequestHeader("description") String description) {
		Boolean response = teamGroupService.saveTeamGroup(team, description);
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}

	@GetMapping("/GetAllTeam")
	private ResponseEntity<EntityRespone> getAllUser() {
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(teamGroupService.getAllTeam());
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@GetMapping("/get/{team}")
	private ResponseEntity<EntityRespone> findByTeam(@PathVariable("team") String team) {
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(teamGroupService.findByTeam(team));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",
					e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/delete/{team}")
	private ResponseEntity<EntityRespone> delete(@PathVariable("team") String team) {
		try {
			teamGroupService.deleteTeam(team);
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj("send to delete");
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Error happen", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
