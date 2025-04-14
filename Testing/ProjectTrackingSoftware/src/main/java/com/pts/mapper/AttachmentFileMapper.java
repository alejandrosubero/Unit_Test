package com.pts.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pts.entitys.AttachmentFile;
import com.pts.pojo.AttachmentFilePojo;

@Component
public class AttachmentFileMapper {

	public AttachmentFile pojoToEntity(AttachmentFilePojo pojo) {
		ModelMapper modelMapper = new ModelMapper();
		AttachmentFile entity = null;

		if (pojo != null) {
			entity = modelMapper.map(pojo, AttachmentFile.class);
		}
		return entity;
	}

	
	public AttachmentFilePojo entityToPojo(AttachmentFile entity) {
		ModelMapper modelMapper = new ModelMapper();
		AttachmentFilePojo pojo = null;

		if (entity != null) {
			pojo = modelMapper.map(entity, AttachmentFilePojo.class);
		}
		return pojo;
	}

}
