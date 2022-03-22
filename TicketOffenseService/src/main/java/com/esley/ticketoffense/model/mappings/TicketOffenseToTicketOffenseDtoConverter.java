package com.esley.ticketoffense.model.mappings;

import com.esley.ticketoffense.model.TicketOffense;
import com.esley.ticketoffense.model.dto.TicketOffenseDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class TicketOffenseToTicketOffenseDtoConverter implements Converter<TicketOffense, TicketOffenseDto> {

    @Override
    public TicketOffenseDto convert(MappingContext<TicketOffense, TicketOffenseDto> mappingContext) {
        TicketOffense source = mappingContext.getSource();
        TicketOffenseDto dto = TicketOffenseDto.builder()
                .id(source.getId())
                .ticketId(source.getTicketId())
                .offenseId(source.getOffenseId())
                .price(source.getPrice())
                .score(source.getScore())
                .country(source.getCountry())
                .build();

        return dto;
    }
}
