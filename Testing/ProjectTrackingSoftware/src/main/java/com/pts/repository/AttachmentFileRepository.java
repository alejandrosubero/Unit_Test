package com.pts.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pts.entitys.AttachmentFile;

@Repository("attachmentFileRepository")
public interface AttachmentFileRepository extends CrudRepository< AttachmentFile, Long>  {

	public AttachmentFile findByName(String name);
	
	public AttachmentFile findByUploadDate(Date uploadDate);
	
	public List<AttachmentFile> findByUserCode(String userCode);
	
}
