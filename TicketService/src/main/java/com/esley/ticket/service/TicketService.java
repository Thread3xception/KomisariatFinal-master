package com.esley.ticket.service;

import com.esley.clients.ticketOffense.TicketOffense;
import com.esley.ticket.exception.EntityNotFoundException;
import com.esley.ticket.model.Ticket;
import com.esley.ticket.repository.TicketRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FeignService feignService;

    public void saveTicket(Ticket ticket) {
        ticketRepository.saveAndFlush(ticket);
    }

    @Transactional(readOnly = true) // dirty checking - sprawdzanie i nadpisywanie danych
    public Ticket findTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Ticket.class, id));
    }

    public Page findAllPage(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Set<TicketOffense> getTicketOffenseListByTicketId(Integer ticketId) {
        return feignService.findListTicketOffenseByTicketId(ticketId);
    }

    @Transactional(readOnly = true)
    public Set<Ticket> getTicketsListByDriverId(Integer driverId) {
        return ticketRepository.findAllTicketsByDriverId(driverId);
    }

    @Transactional(readOnly = true)
    public double getTotalPrice(Integer ticketId) {
        return Optional.ofNullable(feignService.findListTicketOffenseByTicketId(ticketId))
                .orElseGet(Collections::emptySet)
                .stream()
                .collect(Collectors.summingDouble(TicketOffense::getPrice));
    }

    @Transactional(readOnly = true)
    public int getTotalScore(Integer ticketId) {
        return Optional.ofNullable(feignService.findListTicketOffenseByTicketId(ticketId))
                .orElseGet(Collections::emptySet)
                .stream()
                .collect(Collectors.summingInt(TicketOffense::getScore));
    }

}
