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
public abstract class ESEntityBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Version
	@Column(name = "version")
	private Integer version = 0;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified", nullable = false)
	private Date dateTimeModified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified_by", length = 50, nullable = false)
	private String modifiedBy;

	@Column(name = "created_by", length = 50, nullable = false)
	private String createdBy;

	public ESEntityBase(Boolean active, Date created, Date modified,
			String createdBy, String modifiedBy, Integer version) {
		this.active = active;
		this.createdBy = createdBy;
		this.created = created;
		this.modifiedBy = modifiedBy;
		this.version = version;
		this.dateTimeModified = modified;
	}

	public ESEntityBase() {

	}

	public Integer getVersion() {
		return version;
	}

	public Boolean getActive() {
        return active;
    }
    
	public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateTimeModified() {
		return new Date();
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
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
