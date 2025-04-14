package com.pts.serviceImplement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pts.entitys.CodigoReportSecuence;
import com.pts.entitys.User;
import com.pts.mapper.CodigoReportSecuenceMapper;
import com.pts.pojo.CodigoReportSecuencePojo;
import com.pts.repository.CodigoReportSecuenceRepository;
import com.pts.service.CodigoReportSecuenceService;

@Service
public class CodigoReportSecuenceServiceImplement implements CodigoReportSecuenceService {

	protected static final Log logger = LogFactory.getLog(CodigoReportSecuenceServiceImplement.class);

	@Autowired
	private CodigoReportSecuenceRepository repository;

	@Autowired
	private CodigoReportSecuenceMapper mapper;

	
	@Override
	public boolean saveCodigoReportSecuence(CodigoReportSecuence newCodigo) {
		try {
			repository.save(newCodigo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CodigoReportSecuencePojo findByCodigo(String Codigo) {
		return mapper.entityToPojo(repository.findByCodigo(Codigo));
	}

	
	@Override
	@Transactional
	public String generateNewCodigoReport(String codigo) {
			CodigoReportSecuence codigoReport = repository.findByCodigo(codigo);
			Long newSecuencia = codigoReport.getSecuencia();
			newSecuencia++;
			repository.setCodigoReportSecuence(newSecuencia, codigo);
			codigoReport.setSecuencia(newSecuencia);
			return codigoReport(codigoReport);
	}

	
	@Override
	public boolean deleteCodigoReportSecuence(String codigo) {
		logger.info("Delete saveCodigoReportSecuence");
		boolean clave = false;
		try {
			repository.deleteById(this.findByCodigo(codigo).getIdCodigoReportSecuence());
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}
		return clave;
	}

	
	@Override
	public List<String> getAll() {
		logger.info("Get codes reports");
		List<String> lista = new ArrayList<String>();
		repository.findAll().forEach(codeReport -> lista.add(codigoReport(codeReport)));
		return lista;
	}

	
	private String codigoReport(CodigoReportSecuence codeReport) {	
		return codeReport.getCodigo() + "-" + codeReport.getSecuencia();
	}
	
	
	
}











