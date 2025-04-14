package com.pts.service;

import java.util.List;

import com.pts.entitys.CodigoReportSecuence;
import com.pts.pojo.CodigoReportSecuencePojo;


public interface CodigoReportSecuenceService {
	
	public boolean saveCodigoReportSecuence(CodigoReportSecuence newCodigo);
	
	public CodigoReportSecuencePojo findByCodigo(String Codigo);
	
	public String generateNewCodigoReport(String codigo);
	
	 public boolean deleteCodigoReportSecuence(String codigo);

	 public List<String> getAll();
}
