package com.esley.ticket.repository;

import com.esley.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.driverId = ?1")
    Set<Ticket> findAllTicketsByDriverId(Integer driverId);
}
