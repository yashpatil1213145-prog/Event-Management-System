package com.yash.EventManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.EventManagement.entity.Events;
import com.yash.EventManagement.entity.Userr;
import com.yash.EventManagement.repository.EventRepository;
import com.yash.EventManagement.repository.UserRepository;

@Service
public class UserService {

	private final EventRepository eventRepository;
	private final UserRepository userRepository;

	public UserService(EventRepository eventRepository, UserRepository userRepository) {
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}

	public List<Events> allEvents() {
		return eventRepository.findAll();
	}

	public Events addEvents(Events event) {
		Events eventt = new Events();
		eventt.setEventname(event.getEventname());
		eventt.setDate(event.getDate());
		eventt.setDetails(event.getDetails());
		eventt.setLocation(event.getLocation());

		return eventRepository.save(eventt);
	}

	@Transactional
	public Events bookEvent(Integer eventId, String username) {
		Events event = eventRepository.findById(eventId)
				.orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));

		Userr user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found: " + username));

		if (!event.getRegisteredUsers().contains(user)) {
			event.getRegisteredUsers().add(user);
		}

		return eventRepository.save(event);
	}

	public List<Userr> getRegisteredUsers(Integer eventId) {
		Events event = eventRepository.findById(eventId)
				.orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));

		return event.getRegisteredUsers();
	}
}