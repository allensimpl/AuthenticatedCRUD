package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {

}
