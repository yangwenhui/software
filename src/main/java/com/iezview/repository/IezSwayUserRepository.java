package com.iezview.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iezview.domain.IezSwayUser;

public interface IezSwayUserRepository extends CrudRepository<IezSwayUser, Long> {

	@Query("select u from IezSwayUser u where u.username= :username and u.password= :password")
	IezSwayUser findByNameAndPassword(@Param("username") String username, @Param("password") String password);

	@Query("select u from IezSwayUser u where u.username= :username")
	IezSwayUser getByUsername(@Param("username") String username);

}
