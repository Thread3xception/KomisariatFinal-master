package com.esley.driver.repository;

import com.esley.driver.model.Driver;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByEmail(String email);
    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE Driver d SET d.score = ?2 WHERE d.id= ?1")
    void updateScore(@Param("driverId") Integer id, @Param("score") Integer score);



//    @Query("select distinct d from Driver join fetch d.tickets t join fetch t.ticketOffenses")
//    List<Driver> findAllWithTickets();
}
/*
1 kierowca co ma 3 tickiety
1 JAN PREDKOSC 300, 10
1 JAN WYPRZEDZENIA 500, 5
1 JAN Pierdolenie glupot, 10000, 10000

 */