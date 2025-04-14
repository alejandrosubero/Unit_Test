package com.pts.controller;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pts.pojo.AssociatedProyectPojo;
import com.pts.pojo.CommentPojo;
import com.pts.pojo.ReportPojo;
import com.pts.pojo.TimeAssignedPojo;
import com.pts.pojo.TimeReportPojo;
import com.pts.pojo.TypeReportPojo;
import com.pts.service.CodigoReportSecuenceService;
import com.pts.service.SequencesReportService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class ProjectTrackingSoftwareController {

	
	 @Value("${codeSecuence}")
	 private String codeSecuence;
	
	@Autowired
	private SequencesReportService sequencesReportService;
	
	@Autowired
	private CodigoReportSecuenceService codigoReportSecuenceService;
	
	 
	
	@GetMapping("/start")
	public String startTest() {
		
		Gson gson = new Gson();
		
		ReportPojo rep = new ReportPojo();
		
		rep.setid(123445L);
		Long idReport = sequencesReportService.getSecuence(codeSecuence);
		rep.setIdReport(idReport);
		rep.setUserCode("a0c33aeb-4c13-4834-8260-9f520b7a54a8");
		rep. setUserAssigned("alejandro");
		rep.setClient(" ");
		
		TypeReportPojo typeReport = new TypeReportPojo();
		typeReport.setIdtypereort(25L);
		typeReport.setTypeCode("MDS");
		typeReport.setDescripton("bla bla bla");
		typeReport.setType("PROYECT");
		rep.setTypeReport(typeReport);	
		
		String codigo = codigoReportSecuenceService.generateNewCodigoReport(rep.getTypeReport().getTypeCode());
		
		rep.setCodeReports(codigo);
		rep.setTitle("error de generacion de id");
		rep.setDescription("Ocurrio un problema en la generacion del id del reporte");
		rep. setState("NOT TREATED");
		rep.setStateReport("NOT TREATED");
		rep.setTimeDedicate(0L);
		rep. setStartDate(new Date());
		rep.setCommitmentDate(new Date());
		rep.setPriority("Alta");
		rep.setLinkAcceso("link");

		List<CommentPojo> comentarios = new ArrayList<CommentPojo>();
		rep.setComentarios(comentarios);
		
		List<AssociatedProyectPojo> associatedProyects = new ArrayList<AssociatedProyectPojo>();		
		rep.setAssociatedProyects(associatedProyects);
		
		List<TimeReportPojo> times = new ArrayList<TimeReportPojo>();
		TimeReportPojo ti = new TimeReportPojo();
		ti.setIdTimeReport(1L);
		ti.setTimeDedicate(new Date());
		ti.setTimeDedicateTotal(new Date());
		ti.setIdReport(idReport);
		ti.setUserCode("a0c33aeb-4c13-4834-8260-9f520b7a54a8");
		ti.setTimeDedicateIn("TimeDedicateIn");
		ti.setCommenst("comentarios de tiempo");
		times.add(ti);
		rep.setTimes(times);
		
		
		List<TimeAssignedPojo> assigmeds = new ArrayList<TimeAssignedPojo>();
		TimeAssignedPojo asse = new TimeAssignedPojo();
//		asse.setIdtimeassigned(67L);
//		asse.setAssigneddate(new Date());
//		asse.setStateassigned("NOT TREATED");
//		asse.setStatereport("NOT TREATED");
//		asse.setUsercode("a0c33aeb-4c13-4834-8260-9f520b7a54a8");
//		asse.setUserassigned("alejandro");
//		asse.setIdreport(idReport);
		assigmeds.add(asse);
		rep. setAssigmeds(assigmeds);

		String json = gson.toJson(rep);
		
		System.out.println(json);
		
		
		return " <h1>!!!!!!!!!!!!!!!!!Hello Mundo!!!!!!!!!!!!</h1>"+ 
		"<br>" + json +

		"<h2> !!!!!!!!!!!Estoy funcionando!!!!!!!!! </h2>"; 
	}
}
