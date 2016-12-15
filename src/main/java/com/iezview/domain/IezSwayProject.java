package com.iezview.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the iez_sway_project database table.
 * 
 */
@Entity
@Table(name="iez_sway_project")
@NamedQuery(name="IezSwayProject.findAll", query="SELECT i FROM IezSwayProject i")
public class IezSwayProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String author;

	@Column(name="author_id")
	private int authorId;

	@Column(name="is_button")
	private int isButton;

	private String name;

	private String progress;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to IezSwayUpload
	@OneToMany(mappedBy="iezSwayProject", cascade={CascadeType.ALL})
	private List<IezSwayUpload> iezSwayUploads;

	public IezSwayProject() {
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

	public int getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getIsButton() {
		return this.isButton;
	}

	public void setIsButton(int isButton) {
		this.isButton = isButton;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProgress() {
		return this.progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<IezSwayUpload> getIezSwayUploads() {
		return this.iezSwayUploads;
	}

	public void setIezSwayUploads(List<IezSwayUpload> iezSwayUploads) {
		this.iezSwayUploads = iezSwayUploads;
	}

	public IezSwayUpload addIezSwayUpload(IezSwayUpload iezSwayUpload) {
		getIezSwayUploads().add(iezSwayUpload);
		iezSwayUpload.setIezSwayProject(this);

		return iezSwayUpload;
	}

	public IezSwayUpload removeIezSwayUpload(IezSwayUpload iezSwayUpload) {
		getIezSwayUploads().remove(iezSwayUpload);
		iezSwayUpload.setIezSwayProject(null);

		return iezSwayUpload;
	}

}