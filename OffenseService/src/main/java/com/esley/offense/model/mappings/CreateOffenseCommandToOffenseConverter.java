package com.esley.offense.model.mappings;

import com.esley.offense.model.Offense;
import com.esley.offense.model.command.CreateOffenseCommand;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOffenseCommandToOffenseConverter implements Converter<CreateOffenseCommand, Offense> {

    @SneakyThrows
    @Override
    public Offense convert(MappingContext<CreateOffenseCommand, Offense> mappingContext) {
        CreateOffenseCommand command = mappingContext.getSource();
        return new Offense(0,
                command.getPriceFrom(),
                command.getPriceTo(),
                command.getDescription(),
                command.getScoreFrom(),
                command.getScoreTo());
                //new HashSet<>());
    }
}
