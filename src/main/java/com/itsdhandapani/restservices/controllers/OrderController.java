package com.itsdhandapani.restservices.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsdhandapani.restservices.entities.Order;
import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.OrderNotFoundException;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.repositories.OrderRepository;
import com.itsdhandapani.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping(path = "/{userId}/orders")
	public Set<Order> getAllOrdersByUserId(@PathVariable("userId") Long userId) throws UserNotFoundException {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			return optionalUser.get().getOrders();
		} else {
			throw new UserNotFoundException("User with the id '" + userId + "' not found");
		}
	}

	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable("userId") Long userId, @RequestBody Order order)
			throws UserNotFoundException {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User foundUser = optionalUser.get();
			order.setUser(foundUser);
			Order savedOrder = this.orderRepository.save(order);
			return savedOrder;
		} else {
			throw new UserNotFoundException("User with the id '" + userId + "' not found");
		}

	}

	@GetMapping(path = "/{userId}/orders/{orderId}")
	Order getOrderByUserIdAndOrderId(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId)
			throws OrderNotFoundException, UserNotFoundException {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
			if (optionalOrder.isPresent()) {
				User associatedUser = optionalOrder.get().getUser();
				if (associatedUser.getId().equals(userId)) {
					Order foundOrder = optionalOrder.get();
					return foundOrder;
				} else {
					throw new OrderNotFoundException(
							"Order with id '" + orderId + "' not found for the user with userId '" + userId + "'");
				}

			} else {
				throw new OrderNotFoundException(
						"Order with id '" + orderId + "' not found");
			}
		} else {
			throw new UserNotFoundException("User with the id '" + userId + "' not found");
		}
	}
}
