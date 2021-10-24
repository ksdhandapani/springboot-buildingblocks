package com.itsdhandapani.restservices.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "users")
/**
 * @JsonIgnore is used at field level where as @JsonIgnoreProperties is used at
 *             the class level
 */
//@JsonIgnoreProperties({"firstName","lastname"})
/**
 * @JsonFilter is used when we want to use MappingJsonValue
 */
//@JsonFilter(value = "userFilter")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@JsonView(Views.External.class)
	private Long id;
	@NotBlank(message = "Username is a mandatory field")
	@Column(name = "user_name", length = 100, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;
	@Size(min = 2, message = "The firstName should have atleast 2 characters")
	@Column(name = "first_name", length = 100, nullable = false)
	@JsonView(Views.External.class)
	private String firstName;
	@Column(name = "last_name", length = 100, nullable = false)
	@JsonView(Views.External.class)
	private String lastName;
	@Column(name = "email", length = 100, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String email;
	@Column(name = "role", length = 100, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;
	@Column(name = "ssn", length = 100, nullable = false, unique = true)
	/**
	 * If we add the @JsonIgnore annotation, in GET calls, this field will be
	 * skipped but we will face problems in POST and PUT calls, we can make it work
	 * by setting the nullable flag to true
	 */
	// @JsonIgnore
	@JsonView(Views.Internal.class)
	private String ssn;
	/*
	 * One user can have multiple orders The mappedBy value should be the field name
	 * of User we have in Order entity, here it is user
	 */
	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private Set<Order> orders;

	@Column(name = "address")
	private String address;

	public User() {

	}

	public User(Long id, String username, String firstName, String lastName, String email, String role, String ssn,
			Set<Order> orders, String address) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Required for bean logging
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + ", address=" + address
				+ "]";
	}

}
