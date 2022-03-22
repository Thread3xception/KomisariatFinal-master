package com.esley.ticket.model.mappings;

import com.esley.ticket.model.Ticket;
import com.esley.ticket.model.command.CreateTicketCommand;
import com.esley.ticket.service.FeignService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTicketCommandToTicketConverter implements Converter<CreateTicketCommand, Ticket> {

    private final FeignService feignService;

    @SneakyThrows
    @Override
    public Ticket convert(MappingContext<CreateTicketCommand, Ticket> mappingContext) {
        CreateTicketCommand command = mappingContext.getSource();
        return new Ticket(feignService.findOwner(command.getDriverEmail()).getId(),
                command.getData(),
                command.getCountry());
    }
}
