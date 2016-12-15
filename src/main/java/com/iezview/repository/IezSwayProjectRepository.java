package com.iezview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iezview.domain.IezSwayProject;

public interface IezSwayProjectRepository extends CrudRepository<IezSwayProject, Long> {

	@Query("select p from IezSwayProject p where p.author= :author")
	List<IezSwayProject> findByAuthor(@Param("author") String author);

	@Query("select p from IezSwayProject p where p.id= :id")
	IezSwayProject findById(@Param("id") Long id);

}
