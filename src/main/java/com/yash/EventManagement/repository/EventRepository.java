package com.yash.EventManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.EventManagement.entity.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer> {

}
