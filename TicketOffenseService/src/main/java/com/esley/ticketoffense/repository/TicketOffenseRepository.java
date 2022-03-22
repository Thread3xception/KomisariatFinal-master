package com.esley.ticketoffense.repository;

import com.esley.ticketoffense.model.TicketOffense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketOffenseRepository extends JpaRepository<TicketOffense, Integer> {

    @Query("SELECT t FROM TicketOffense t WHERE t.offenseId = ?1")
    Set<TicketOffense> findAllTicketOffenseByOffenseId(Integer offenseId);

    @Query("SELECT t FROM TicketOffense t WHERE t.ticketId = ?1")
    Set<TicketOffense> findAllTicketOffenseByTicketId(Integer ticketId);
}
