package com.yash.EventManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.EventManagement.entity.Events;
import com.yash.EventManagement.entity.Userr;
import com.yash.EventManagement.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/test")
	public String testApi() {
		return "Hello";
	}

	@GetMapping("/all")
	public ResponseEntity<List<Events>> allEvents() {
		List<Events> events = userService.allEvents();
		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Events> addEvents(@RequestBody Events event) {
		Events eventt = userService.addEvents(event);
		return new ResponseEntity<>(eventt, HttpStatus.OK);
	}

	@PostMapping("/book/{eventId}")
	public ResponseEntity<String> bookEvent(@PathVariable Integer eventId, Authentication authentication) {
		String username = authentication.getName();
		userService.bookEvent(eventId, username);
		return ResponseEntity.ok("Event booked successfully!");
	}

	@GetMapping("/events/{eventId}")
	public ResponseEntity<List<Userr>> getRegisteredUsers(@PathVariable Integer eventId) {
		List<Userr> users = userService.getRegisteredUsers(eventId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}