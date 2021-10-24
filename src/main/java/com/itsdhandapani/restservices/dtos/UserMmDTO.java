package com.itsdhandapani.restservices.dtos;

import java.util.Set;

import com.itsdhandapani.restservices.entities.Order;

public class UserMmDTO {
	private Long id;
	private String username;
	private String firstName;
	private Set<Order> orders;

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

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	

}
