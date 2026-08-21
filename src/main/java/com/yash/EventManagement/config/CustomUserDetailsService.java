package com.yash.EventManagement.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yash.EventManagement.entity.Userr;
import com.yash.EventManagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepo;

	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("username not found " + username));
		return new CustomUserDetails(user);
	}

}
