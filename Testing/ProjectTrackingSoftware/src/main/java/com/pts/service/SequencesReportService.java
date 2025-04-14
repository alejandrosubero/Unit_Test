package com.pts.service;

import java.util.List;

import com.pts.pojo.SequencesReportPojo;

public interface SequencesReportService {

	public List<SequencesReportPojo> getAllSecuences();
	
	public SequencesReportPojo findByTitle(String title);
	
	public Long getSecuence(String code);
	
	public void generateNewSecunce(String title, String code);
	
}
