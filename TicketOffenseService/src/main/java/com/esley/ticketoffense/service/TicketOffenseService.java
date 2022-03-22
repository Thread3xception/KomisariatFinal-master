package com.esley.ticketoffense.service;

import com.esley.ticketoffense.exception.EntityNotFoundException;
import com.esley.ticketoffense.model.TicketOffense;
import com.esley.ticketoffense.repository.TicketOffenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class TicketOffenseService {

    private final TicketOffenseRepository ticketOffenseRepository;

    public void saveTicketOffense(TicketOffense ticketOffense) {
        ticketOffenseRepository.saveAndFlush(ticketOffense);
    }

    @Transactional(readOnly = true)
    public TicketOffense findTicketOffenseById(Integer id) {
        return ticketOffenseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(TicketOffense.class, id));
    }

    public Page findAllPage(Pageable pageable) {
        return ticketOffenseRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Set<TicketOffense> getAllTicketOffenseByOffenseId(Integer offenseId) {
        return ticketOffenseRepository.findAllTicketOffenseByOffenseId(offenseId);
    }

    @Transactional(readOnly = true)
    public Set<TicketOffense> getAllTicketOffenseByTicketId(Integer ticketId) {
        return ticketOffenseRepository.findAllTicketOffenseByTicketId(ticketId);
    }
}
