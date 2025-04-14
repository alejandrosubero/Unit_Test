package com.pts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pts.entitys.SequencesReport;

@Repository
public interface SequencesReportRepository extends CrudRepository<SequencesReport, Long>{

	public SequencesReport findByTitle(String title);
	public SequencesReport findByCodeSecuence(String codeSecuence);
	
}
