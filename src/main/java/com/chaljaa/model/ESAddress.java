package com.chaljaa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "es_addresses")
public class ESAddress extends ESEntityBase {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", unique = true, nullable = false)
	private Integer id;

	@NotEmpty
	@Column(name = "address_line1")
	private String addressLine1;

	@NotEmpty
	@Column(name = "address_line2")
	private String addressLine2;

	@NotEmpty
	@Column(name = "city")
	private String city;

	@NotEmpty
	@Column(name = "pincode")
	private String pincode;

	@NotEmpty
	@Column(name = "state")
	private String state;

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@NotEmpty
	@Column(name = "country")
	private String country;

	@NotEmpty
	@Column(name = "other_address_details")
	private String otherDetails;

	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	private ESStudent student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public ESAddress() {
		// TODO Auto-generated constructor stub
	}
}
