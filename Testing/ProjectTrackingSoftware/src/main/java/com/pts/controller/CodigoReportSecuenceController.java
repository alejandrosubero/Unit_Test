package com.pts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.mapper.CodigoReportSecuenceMapper;
import com.pts.mapper.MapperEntityRespone;
import com.pts.pojo.CodigoReportSecuencePojo;
import com.pts.pojo.EntityRespone;
import com.pts.service.CodigoReportSecuenceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/codeReport")
public class CodigoReportSecuenceController {

	@Autowired
	private CodigoReportSecuenceService codigoReportSecuenceService;

	@Autowired
	private CodigoReportSecuenceMapper mapper;

	@Autowired
	private MapperEntityRespone mapperEntityRespone;

	
	
	@PostMapping("/saveNewCodeReport")
	private EntityRespone saveNewUser(@RequestBody CodigoReportSecuencePojo newSecuenceReport) {
		List<Object> response = new ArrayList<Object>();
		try {
			boolean create = codigoReportSecuenceService.saveCodigoReportSecuence(mapper.pojoToEntity(newSecuenceReport));
			response.add(create);
			String mensaje = create ? "The new Code report was have create" : "the code report cant not create";
			return new EntityRespone("", mensaje, response);
		} catch (Exception e) {
			return new EntityRespone("CodigoReportSecuence01", "Error: " + e, response);
		}
	}
	
	

	@GetMapping("/delete/{codigo}")
	private ResponseEntity<EntityRespone> delete(@PathVariable("codigo") String codigo) {
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(codigoReportSecuenceService.deleteCodigoReportSecuence(codigo));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (Exception e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(false, "CodigoReportSecuence02",
					"Error: " + e);
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/generate/{codigo}")
	private ResponseEntity<EntityRespone> generateNewCodigoReport(@PathVariable("codigo") String codigo) {
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(codigoReportSecuenceService.generateNewCodigoReport(codigo));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(false, "CodigoReportSecuence02",	"Error: " + e);
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/getAll")
	private ResponseEntity<EntityRespone> allCodigoReport() {
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(codigoReportSecuenceService.getAll());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (Exception e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(false, "CodigoReportSecuence03",	"Error: " + e);
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}





