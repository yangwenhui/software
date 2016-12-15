package com.iezview.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iez_sway_ip database table.
 * 
 */
@Entity
@Table(name="iez_sway_ip")
@NamedQuery(name="IezSwayIp.findAll", query="SELECT i FROM IezSwayIp i")
public class IezSwayIp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String ip;

	private int total;

	public IezSwayIp() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}