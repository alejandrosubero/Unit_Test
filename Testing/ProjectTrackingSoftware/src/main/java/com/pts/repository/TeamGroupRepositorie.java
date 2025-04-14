package com.pts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pts.entitys.TeamGroup;

@Repository
public interface TeamGroupRepositorie extends CrudRepository< TeamGroup, Long>{
	public TeamGroup findByTeam(String team);
}
