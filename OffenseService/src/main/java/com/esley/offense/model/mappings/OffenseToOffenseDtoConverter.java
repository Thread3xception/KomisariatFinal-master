package com.esley.offense.model.mappings;

import com.esley.offense.controller.OffenseController;
import com.esley.offense.model.Offense;
import com.esley.offense.model.dto.OffenseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OffenseToOffenseDtoConverter implements Converter<Offense, OffenseDto> {

    @Override
    public OffenseDto convert(MappingContext<Offense, OffenseDto> mappingContext) {
        Offense source = mappingContext.getSource();
        OffenseDto dto = OffenseDto.builder()
                .id(source.getId())
                .priceFrom(source.getPriceFrom())
                .priceTo(source.getPriceTo())
                .description(source.getDescription())
                .scoreFrom(source.getScoreFrom())
                .scoreTo(source.getScoreTo())
                //.ticketsIds(source.getTicketOffenses().stream().map(TicketOffense::getId).collect(Collectors.toSet()))
                //.ticketsIds(feignService.findListTicketOffensesById(source.getId()).stream().map(TicketOffense::getId).collect(Collectors.toSet()))
                .build();

        dto.add(linkTo(methodOn(OffenseController.class).getAllOffenses(Pageable.unpaged())).withRel("all-offenses"));
        dto.add(linkTo(methodOn(OffenseController.class).getSingleOffense(source.getId())).withRel("single-offense-details"));

        return dto;
    }
}
