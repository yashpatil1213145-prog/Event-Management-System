package com.yash.EventManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.EventManagement.entity.Userr;
import com.yash.EventManagement.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<Userr> register(@RequestBody Userr user) {

		Userr userr = authService.register(user);

		return ResponseEntity.ok(userr);
	}

	@PostMapping("/login")
	public ResponseEntity<Userr> loginUser(@RequestBody Userr user) {
		Userr loggedInUser = authService.loginUser(user);
		return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
	}
}
