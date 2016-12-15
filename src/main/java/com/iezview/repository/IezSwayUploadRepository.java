package com.iezview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iezview.domain.IezSwayUpload;

public interface IezSwayUploadRepository extends CrudRepository<IezSwayUpload, Long> {

	@Query("select p from IezSwayUpload p where p.id= :id")
	IezSwayUpload findById(@Param("id") Long id);

	@Query("select p from IezSwayUpload p where p.type= :type and p.iezSwayProject.id=:projectId")
	List<IezSwayUpload> findByType(@Param("projectId") String projectId, @Param("type") String type);
}
