package com.pts.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pts.entitys.CodigoReportSecuence;

@Repository("CodigoReportSecuenceRepository")
public interface CodigoReportSecuenceRepository extends CrudRepository< CodigoReportSecuence, Long>{
	
	public CodigoReportSecuence findByCodigo(String Codigo);
	
	@Modifying
	@Query("update CodigoReportSecuence u set u.secuencia = ?1 where u.codigo = ?2")
	void setCodigoReportSecuence(Long secuencia, String codigo);
	

	
}
