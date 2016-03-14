package com.chaljaa.tos;

import java.util.Date;
import java.util.Random;

import com.chaljaa.model.ESAddress;
import com.chaljaa.model.ESMedical;
import com.chaljaa.model.ESStudentInfo;

public class ESStudentTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String userCode;

	private String firstName;

	private String middleName;

	private String lastName;

	private ESAddress address;

	private ESStudentInfo studentInfo;

	private ESMedical medical;

	private String phone;

	private String email;

	private String emergencyContact;

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

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMedical(ESMedical medical) {
		this.medical = medical;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public ESStudentTO(Boolean active, Date created, Date modified,
			String createdBy, String modifiedBy, Integer Version,
			ESMedical medical, String firstName, String middleName,
			String lastName, ESStudentInfo studentInfo, ESAddress esAddress,
			String usercode) {

		this.address = esAddress;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.medical = medical;
		this.studentInfo = studentInfo;
		this.userCode = usercode;

	}

	public ESStudentTO() {
		studentInfo = new ESStudentInfo();
		medical = new ESMedical();
		address = new ESAddress();
	}

}
