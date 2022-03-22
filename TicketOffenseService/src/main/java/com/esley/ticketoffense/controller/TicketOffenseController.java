package com.esley.ticketoffense.controller;

import com.esley.ticketoffense.model.TicketOffense;
import com.esley.ticketoffense.model.command.CreateTicketOffenseCommand;
import com.esley.ticketoffense.model.dto.TicketOffenseDto;
import com.esley.ticketoffense.service.TicketOffenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/ticket_offense")
@RequiredArgsConstructor
public class TicketOffenseController {

    private final TicketOffenseService ticketOffenseService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllTicketOffense(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(ticketOffenseService.findAllPage(pageable)
                .map(ticketOffense -> modelMapper.map(ticketOffense, TicketOffenseDto.class)));
    }

    @PostMapping
    public ResponseEntity saveTicketOffense(@RequestBody @Valid CreateTicketOffenseCommand command) {
        TicketOffense newTicketOffense = modelMapper.map(command, TicketOffense.class);
        ticketOffenseService.saveTicketOffense(newTicketOffense);
        return new ResponseEntity(modelMapper.map(newTicketOffense, TicketOffenseDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/{ticket_offense_id}")
    public ResponseEntity getSingleTicketOffense(@PathVariable("ticket_offense_id") Integer ticket_offense_id) {
//        return ticketOffenseRepository.findById(ticket_offense_id)
//                .map(ticketOffense -> modelMapper.map(ticketOffense, TicketOffenseDto.class))
//                .map(ticketOffenseDto -> new ResponseEntity<>(ticketOffenseDto, HttpStatus.OK))
//                .orElseThrow(() -> new EntityNotFoundException(TicketOffense.class, ticket_offense_id));
        return ResponseEntity.ok(modelMapper.map(ticketOffenseService.findTicketOffenseById(ticket_offense_id), TicketOffenseDto.class));
    }

    @GetMapping("/list_offense_id/{offenseId}")
    public ResponseEntity getListOfTicketOffenseByOffenseId(@PathVariable("offenseId") Integer offenseId) {
        Set<TicketOffense> ticketOffenses = ticketOffenseService.getAllTicketOffenseByOffenseId(offenseId);
        return new ResponseEntity(ticketOffenses, HttpStatus.OK);
    }

    @GetMapping("/list_ticket_id/{ticketId}")
    public ResponseEntity getListOfTicketOffenseByTicketId(@PathVariable("ticketId") Integer ticketId) {
        Set<TicketOffense> ticketOffenses = ticketOffenseService.getAllTicketOffenseByTicketId(ticketId);
        return new ResponseEntity(ticketOffenses, HttpStatus.OK);
    }

}
