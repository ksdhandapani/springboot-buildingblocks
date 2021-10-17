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

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@NotBlank(message = "Username is a mandatory field")
	@Column(name = "user_name", length = 100, nullable = false, unique = true)
	private String username;
	@Size(min = 2, message = "The firstName should have atleast 2 characters")
	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastname;
	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;
	@Column(name = "role", length = 100, nullable = false)
	private String role;
	@Column(name = "ssn", length = 100, nullable = false, unique = true)
	private String ssn;
	/*
	 * One user can have multiple orders The mappedBy value should be the field name
	 * of User we have in Order entity, here it is user
	 */
	@OneToMany(mappedBy = "user")
	private Set<Order> orders;

	public User() {

	}

	public User(Long id, String username, String firstName, String lastname, String email, String role, String ssn) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	// Required for bean logging
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

}
