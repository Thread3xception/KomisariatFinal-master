package com.esley.driver.model.mappings;

import com.esley.clients.ticket.Ticket;
import com.esley.driver.controller.DriverController;
import com.esley.driver.model.Driver;
import com.esley.driver.model.dto.DriverDto;
import com.esley.driver.service.FeignService;
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
public class DriverToDriverDtoConverter implements Converter<Driver, DriverDto> {

    private final FeignService feignService;

    @Override
    public DriverDto convert(MappingContext<Driver, DriverDto> mappingContext) {
        Driver source = mappingContext.getSource();
        DriverDto dto = DriverDto.builder()
                .id(source.getId())
                .email(source.getEmail())
                .name(source.getName())
                .surname(source.getSurname())
                .yearsOld(source.getYearsOld())
                //.score(source.getTotalScore())
                //.tickets(source.getTickets())
                //.ticketIds(source.getTickets().stream().map(Ticket::getId).collect(Collectors.toSet()))
                .ticketIds(feignService.findTicketListForDriver(source.getId()).stream().map(Ticket::getId).collect(Collectors.toSet()))
                .build();

        dto.add(linkTo(methodOn(DriverController.class).getAllDrivers(Pageable.unpaged())).withRel("all-drivers"));
        dto.add(linkTo(methodOn(DriverController.class).getDriverTickets(source.getId())).withRel("all-tickets-of-driver"));
        dto.add(linkTo(methodOn(DriverController.class).getSingleDriver(source.getId())).withRel("single-driver-details"));
        dto.add(linkTo(methodOn(DriverController.class).getSingleTicket(source.getId())).withRel("single-ticket-details"));

        return dto;
    }
}
