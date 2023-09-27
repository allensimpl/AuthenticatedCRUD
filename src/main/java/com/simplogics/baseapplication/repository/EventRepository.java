package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query(value = "SELECT COUNT(*) FROM event WHERE ev_code = :eventCode",nativeQuery = true)
    int countEventCode(int eventCode);

    @Query(value = "SELECT COUNT(*) FROM event WHERE id = :id",nativeQuery = true)
    int countEventID(int id);

    @Query(value = "SELECT * FROM event WHERE (:search is null OR ev_name LIKE concat('%',:search,'%'))",nativeQuery = true)
    Page<Event> findAllEvents(String search, Pageable pagingParams);

    @Query(value = "SELECT * FROM event WHERE ev_code = :eventCode",nativeQuery = true)
    Event findByEventCode(int eventCode);
}
