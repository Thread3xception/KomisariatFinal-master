package com.esley.driver.model.mappings;

import com.esley.driver.model.Driver;
import com.esley.driver.model.command.CreateDriverCommand;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CreateDriverCommandToDriverConverter implements Converter<CreateDriverCommand, Driver> {
    @Override
    public Driver convert(MappingContext<CreateDriverCommand, Driver> mappingContext) {
        CreateDriverCommand command = mappingContext.getSource();
        return new Driver(0, command.getEmail(),
                command.getName(),
                command.getSurname(),
                0,
                command.getYearsOld());
                //new HashSet<>());
    }
}
