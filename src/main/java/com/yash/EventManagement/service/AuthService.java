package com.yash.EventManagement.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yash.EventManagement.entity.Userr;
import com.yash.EventManagement.enums.Role;
import com.yash.EventManagement.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public Userr register(Userr user) {
		if (user.getConfirmPassword() == null || !user.getPassword().equals(user.getConfirmPassword())) {
			throw new RuntimeException("Passwords do not match");
		}

		if (userRepo.existsByUsername(user.getUsername())) {
			throw new RuntimeException("Username is already taken");
		}

		if (userRepo.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email is already registered");
		}

		Userr userr = new Userr();
		userr.setEmail(user.getEmail());
		userr.setUsername(user.getUsername());
		userr.setPassword(passwordEncoder.encode(user.getPassword()));
		userr.setRole(Role.ROLE_USER);

		return userRepo.save(userr);
	}

	public Userr loginUser(Userr user) {
		Userr existingUser = userRepo.findByUsername(user.getUsername())
				.orElseThrow(() -> new RuntimeException("Invalid username or password"));

		if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}

		existingUser.setPassword(null);
		return existingUser;
	}
}