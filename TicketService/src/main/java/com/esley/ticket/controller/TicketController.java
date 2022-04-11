package com.esley.ticket.controller;

import com.esley.ticket.model.Ticket;
import com.esley.ticket.model.command.CreateTicketCommand;
import com.esley.ticket.model.dto.TicketDto;
import com.esley.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_POLICEMAN')")
    public ResponseEntity getAllTickets(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(ticketService.findAllPage(pageable)
                .map(ticket -> modelMapper.map(ticket, TicketDto.class)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_POLICEMAN')")
    public ResponseEntity saveTicket(@RequestBody @Valid CreateTicketCommand command) {
        Ticket newTicket = modelMapper.map(command, Ticket.class);
        ticketService.saveTicket(newTicket);
        return new ResponseEntity(modelMapper.map(newTicket, TicketDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/{ticketId}")
    @PreAuthorize("hasRole('ROLE_POLICEMAN')")
    public ResponseEntity getSingleTicket(@PathVariable("ticketId") Integer ticketId) {
//        return ticketRepository.findById(ticketId)
//                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
//                .map(ticketDto -> new ResponseEntity<>(ticketDto, HttpStatus.OK))
//                .orElseThrow(() -> new EntityNotFoundException(Ticket.class, ticketId));
        return ResponseEntity.ok(modelMapper.map(ticketService.findTicketById(ticketId), TicketDto.class));
    }

    @GetMapping("/score/{ticketId}")
    @PreAuthorize("hasRole('ROLE_POLICEMAN')")
    public ResponseEntity getSumOfScoreSingleTicketById(@PathVariable("ticketId") Integer ticketId) {
        return ResponseEntity.ok(ticketService.getTotalScore(ticketId));
    }

    @GetMapping("/price/{ticketId}")
    @PreAuthorize("hasRole('ROLE_POLICEMAN')")
    public ResponseEntity getSumOfPriceSingleTicketById(@PathVariable("ticketId") Integer ticketId) {
        return ResponseEntity.ok(ticketService.getTotalPrice(ticketId));
    }

    @GetMapping("/list/{driverId}")
    @PreAuthorize("hasRole('ROLE_POLICEMAN')")
    public ResponseEntity getTicketListForDriver(@PathVariable("driverId") Integer driverId) {
        Set<Ticket> tickets = ticketService.getTicketsListByDriverId(driverId);
        return new ResponseEntity(tickets, HttpStatus.OK);
    }

}
