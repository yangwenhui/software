package com.iezview.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the iez_sway_role database table.
 * 
 */
@Entity
@Table(name = "iez_sway_role")
@NamedQuery(name = "IezSwayRole.findAll", query = "SELECT i FROM IezSwayRole i")
public class IezSwayRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// bi-directional many-to-many association to IezSwayUser
	@ManyToMany(mappedBy = "iezSwayRoles")
	private List<IezSwayUser> iezSwayUsers;

	public IezSwayRole() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IezSwayUser> getIezSwayUsers() {
		return this.iezSwayUsers;
	}

	public void setIezSwayUsers(List<IezSwayUser> iezSwayUsers) {
		this.iezSwayUsers = iezSwayUsers;
	}

}