package com.chaljaa.model;

import java.util.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "es_user")
public class ESStudent extends ESEntityBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "user_code")
	private String userCode;

	@NotEmpty
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotEmpty
	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@NotEmpty
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "address_id")
	private ESAddress address;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_info_id")
	private ESStudentInfo studentInfo;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_medical_id")
	private ESMedical medical;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserCode() {
		this.userCode = new Random().toString();
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	// public ESContact getMobileContact() {
	// return mobileContact;
	// }
	//
	// public void setMobileContact(ESContact contact) {
	// this.mobileContact = contact;
	// this.mobileContact.setType(ContactType.MOBILE);
	// }
	//
	// public ESContact getEmailContact() {
	// return emailContact;
	// }
	//
	// public void setEmailContact(ESContact contact) {
	// this.emailContact = contact;
	// this.emailContact.setType(ContactType.MOBILE);
	// }

	public ESAddress getAddress() {
		return address;
	}

	public void setAddress(ESAddress address) {
		this.address = address;
	}

	public ESStudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(ESStudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public ESMedical getMedical() {
		return medical;
	}

	public void setMedical(ESMedical medical) {
		this.medical = medical;
	}

	public ESStudent(Boolean active, Date created, Date modified,
			String createdBy, String modifiedBy, Integer Version,
			ESMedical medical, String firstName, String middleName,
			String lastName, ESStudentInfo studentInfo, ESAddress esAddress,
			String usercode) {
		super(active, created, modified, createdBy, modifiedBy, Version);
		this.address = esAddress;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.medical = medical;
		this.studentInfo = studentInfo;
		this.userCode = usercode;

	}

	public ESStudent() {
		studentInfo = new ESStudentInfo();
		medical = new ESMedical();
		address = new ESAddress();
	}

	// public Set<UserProfile> getUserProfiles() {
	// return userProfiles;
	// }
	//
	// public void setUserProfiles(Set<UserProfile> userProfiles) {
	// this.userProfiles = userProfiles;
	// }

}
