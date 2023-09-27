package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.EventStoreMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreMapRepository extends JpaRepository<EventStoreMap,Integer>{
    @Query(value = "INSERT INTO event_str_map WHERE event_id = :id AND store_id IN :storeCodes",nativeQuery = true)
    void addEventStoreCode(int id, List<Integer> storeCodes);

//    @Query(value = "DELETE FROM event_str_map WHERE event_id = :id",nativeQuery = true)
//    int unpublishDelete(int id);
    @Query(value = "SELECT id FROM event_str_map WHERE event_id = :eventId AND store_id = :storeId", nativeQuery = true)
    int findId(@Param("eventId") int eventId, @Param("storeId") int storeId);

    @Modifying
    @Query(value = "DELETE FROM event_str_map WHERE event_id = :id", nativeQuery = true)
    int unpublishDelete(@Param("id") int id);

    @Query(value = "SELECT COUNT(*) FROM event_str_map WHERE event_id = :eventCode",nativeQuery = true)
    int eventCount(@Param("eventCode") int eventCode);

//    @Query(value = "INSERT INTO event_str_map (event_id, store_id) VALUES (:id, :storeCode)", nativeQuery = true)
//    void addEventStoreCode(int id,  List<Integer> storeCodes);



//    @Query(value = "INSERT INTO event_str_map (event_id, store_id) " +
//                    "SELECT :id, store_id " +
//                    "FROM some_table " +
//                    "WHERE event_id = :id AND store_id IN :storeCodes",nativeQuery = true)
//    void addEventStoreCode(@Param("id") int id, @Param("storeCodes") List<Integer> storeCodes);

}