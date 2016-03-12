package com.chaljaa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@MappedSuperclass
public abstract class ESEntityBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Version
	@Column(name ="version")
	private Integer version =0;

	@Column(name ="active",nullable = false)
	private Boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified", nullable = false)
	private Date dateTimeModified;

	@Column(name ="modified_by",length =50, nullable = false)
	private String modifiedBy;

	@Column(name ="created_by",length =50, nullable = false)
	private String createdBy;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getDateTimeModified() {
		return dateTimeModified;
	}

	public void setDateTimeModified(Date dateTimeModified) {
		this.dateTimeModified = dateTimeModified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@PreUpdate
	protected void onPreUpdate() {
		dateTimeModified = new Date();
	}

}
