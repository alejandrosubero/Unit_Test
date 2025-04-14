package com.pts.serviceImplement;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pts.entitys.SequencesReport;
import com.pts.pojo.SequencesReportPojo;
import com.pts.repository.SequencesReportRepository;
import com.pts.service.SequencesReportService;

@Service
public class SequencesReportServiceImplement implements SequencesReportService {

	@Autowired
	private SequencesReportRepository repository;
	
	
	@Override
	public void generateNewSecunce(String title, String codeSecuence) {
		SequencesReport sequencesReport = new SequencesReport(title, codeSecuence);
		sequencesReport.calculateSecuence();
		repository.save(sequencesReport);
	}
	
	
	@Override
	public List<SequencesReportPojo> getAllSecuences() {
		List<SequencesReportPojo> secuences = new ArrayList<SequencesReportPojo>();
		repository.findAll().forEach(secuenceReport -> secuences.add(this.entityToPojo(secuenceReport)));
		return secuences;
	}

	@Override
	public SequencesReportPojo findByTitle(String title) {
		return this.entityToPojo(repository.findByTitle(title));
	}

	
	@Override
	public Long getSecuence(String code) {
		SequencesReport secuencia = repository.findByCodeSecuence(code);
		Long sec = 0L;
	
		if (secuencia != null &&
				secuencia.getCodeSecuence() !=null &&
				secuencia.getActualSecuence() != null &&
				secuencia.getNextSecuence() != null) {
			
		sec = this.entityToPojo(secuencia).getNextSecuence();
		secuencia.calculateSecuence();
		repository.save(secuencia);
		} else {
			this.generateNewSecunce("Secunce_Automatic", code);
			getSecuence(code);
		}
		
		return sec;
	}

	
	public SequencesReportPojo entityToPojo(SequencesReport entity) {
		ModelMapper modelMapper = new ModelMapper();
		SequencesReportPojo pojo = null;

		if (entity != null) {
			pojo = modelMapper.map(entity, SequencesReportPojo.class);
		}
		return pojo;
	}
	
	

}
