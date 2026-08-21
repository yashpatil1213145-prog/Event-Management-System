package com.yash.EventManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.EventManagement.entity.Userr;

@Repository
public interface UserRepository extends JpaRepository<Userr, Integer> {

	Optional<Userr> findByUsername(String username);

	Optional<Userr> findByPassword(String password);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}
