package com.pts.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pts.entitys.CodigoReportSecuence;
import com.pts.pojo.CodigoReportSecuencePojo;

@Component
public class CodigoReportSecuenceMapper {

	
	public CodigoReportSecuence pojoToEntity(CodigoReportSecuencePojo pojo) {
		ModelMapper modelMapper = new ModelMapper();
		CodigoReportSecuence entity = null;

		if ( pojo != null) {
   		entity = modelMapper.map(pojo, CodigoReportSecuence.class);
		}
	return  entity;
}
	
	
    public CodigoReportSecuencePojo entityToPojo(CodigoReportSecuence entity) {
 		ModelMapper modelMapper = new ModelMapper();
 		CodigoReportSecuencePojo pojo = null;

		if ( entity != null) {
   		pojo = modelMapper.map(entity, CodigoReportSecuencePojo.class);
		}
	return  pojo;
}
	
	
}
