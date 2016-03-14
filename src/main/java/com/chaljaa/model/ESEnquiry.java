package com.chaljaa.model;

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
@Table(name = "es_enquiry")
public class ESEnquiry extends ESEntityBase {

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

}
