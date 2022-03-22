package com.esley.ticketoffense.model.mappings;

import com.esley.ticketoffense.model.TicketOffense;
import com.esley.ticketoffense.model.command.CreateTicketOffenseCommand;
import com.esley.ticketoffense.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateTicketOffenseCommandToTicketOffenseConverter implements Converter<CreateTicketOffenseCommand, TicketOffense> {
    private final FeignService feignService;

    @Override
    public TicketOffense convert(MappingContext<CreateTicketOffenseCommand, TicketOffense> mappingContext) {
        CreateTicketOffenseCommand command = mappingContext.getSource();
        return new TicketOffense(feignService.findTicketById(command.getTicketId()).getId(),
                feignService.findOffenseById(command.getOffenseId()).getId(),
                command.getPrice(),
                command.getScore(),
                command.getCountry());
    }
}
