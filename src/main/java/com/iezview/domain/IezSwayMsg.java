package com.iezview.domain; 

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the iez_sway_msg database table.
 * 
 */
@Entity
@Table(name="iez_sway_msg")
@NamedQuery(name="IezSwayMsg.findAll", query="SELECT i FROM IezSwayMsg i")
public class IezSwayMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String content;

	private String receive;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private String title;

	private int status;
	
	
	public IezSwayMsg() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceive() {
		return this.receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}