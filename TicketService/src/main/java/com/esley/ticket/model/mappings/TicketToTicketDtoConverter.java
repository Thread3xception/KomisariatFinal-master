package com.esley.ticket.model.mappings;

import com.esley.clients.ticketOffense.TicketOffense;
import com.esley.ticket.controller.TicketController;
import com.esley.ticket.model.Ticket;
import com.esley.ticket.model.dto.TicketDto;
import com.esley.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class TicketToTicketDtoConverter implements Converter<Ticket, TicketDto> {

    private final TicketService ticketService;

    @Override
    public TicketDto convert(MappingContext<Ticket, TicketDto> mappingContext) {
        Ticket source = mappingContext.getSource();
        TicketDto dto = TicketDto.builder()
                .id(source.getId())
                .driverId(source.getDriverId())
                .data(source.getData())
                .price(ticketService.getTotalPrice(source.getId()))
                .score(ticketService.getTotalScore(source.getId()))
                .status(source.checkStatus())
                .country(source.getCountry())
                //.offenseIds(source.getTicketOffenses().stream().map(TicketOffense::getId).collect(Collectors.toSet()))
                .ticketOffenseIds(ticketService.getTicketOffenseListByTicketId(source.getId()).stream().map(TicketOffense::getId).collect(Collectors.toSet()))
                .build();

        dto.add(linkTo(methodOn(TicketController.class).getAllTickets(Pageable.unpaged())).withRel("all-tickets"));

        return dto;
    }
}
