package com.iezview.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the iez_sway_upload database table.
 * 
 */
@Entity
@Table(name="iez_sway_upload")
@NamedQuery(name="IezSwayUpload.findAll", query="SELECT i FROM IezSwayUpload i")
public class IezSwayUpload implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String author;

	private String name;

	private String path;

	private String suffix;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private String type;

	//bi-directional many-to-one association to IezSwayProject
	@ManyToOne
	@JoinColumn(name="project_id")
	private IezSwayProject iezSwayProject;

	public IezSwayUpload() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IezSwayProject getIezSwayProject() {
		return this.iezSwayProject;
	}

	public void setIezSwayProject(IezSwayProject iezSwayProject) {
		this.iezSwayProject = iezSwayProject;
	}

}