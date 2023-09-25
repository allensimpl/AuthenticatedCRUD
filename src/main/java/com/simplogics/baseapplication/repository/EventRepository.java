package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query(value = "SELECT COUNT(*) FROM event WHERE ev_code = :eventCode",nativeQuery = true)
    int countEventCode(int eventCode);
}
