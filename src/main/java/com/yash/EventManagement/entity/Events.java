package com.yash.EventManagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Events {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String eventname;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String details;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private String location;

	@ManyToMany
	@JoinTable(name = "event_registration", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnoreProperties({ "password", "confirmPassword", "role" })
	private List<Userr> registeredUsers = new ArrayList<>();
}