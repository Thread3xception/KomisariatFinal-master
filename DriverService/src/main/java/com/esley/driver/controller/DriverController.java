package com.esley.driver.controller;

import com.esley.driver.exception.EntityNotFoundException;
import com.esley.driver.model.Driver;
import com.esley.driver.model.command.CreateDriverCommand;
import com.esley.driver.model.dto.DriverDto;
import com.esley.driver.service.DriverService;
import com.esley.driver.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;
    private final ModelMapper modelMapper;
    private final FeignService feignService;

    @GetMapping
    public ResponseEntity getAllDrivers(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(driverService.findAllPage(pageable).map(driver -> modelMapper.map(driver, DriverDto.class)));
    }

    @GetMapping("/{email}")
    public ResponseEntity getSingleDriver(@PathVariable("email") String email) {
//        return driverRepository.findById(driverId)
//                .map(driver -> modelMapper.map(driver, DriverDto.class))
//                .map(driverDto -> new ResponseEntity<>(driverDto, HttpStatus.OK))
//                .orElseThrow(() -> new EntityNotFoundException(Driver.class, driverId))
        // test-github
        // test-github2
        // test-bitbucket
        return ResponseEntity.ok(modelMapper.map(driverService.findDriverByEmail(email), DriverDto.class));
    }

    @PostMapping
    public ResponseEntity saveDriver(@RequestBody @Valid CreateDriverCommand command) {
        Driver newDriver = modelMapper.map(command, Driver.class);
        //newDriver = driverRepository.saveAndFlush(newDriver);
        driverService.saveDriver(newDriver);
        return new ResponseEntity(modelMapper.map(newDriver, DriverDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/{driverId}/tickets")
    //@PreAuthorize("hasRole('ROLE_POLICEMAN')")
    public ResponseEntity getDriverTickets(@PathVariable("driverId") Integer driverId) {
        return driverService.findByDriverIdOptional(driverId).map(driver -> modelMapper.map(driver, DriverDto.class)).map(driverDto -> new ResponseEntity<>(driverDto.getTicketIds(), HttpStatus.OK)).orElseThrow(() -> new EntityNotFoundException(Driver.class, driverId));
    }


    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity getSingleTicket(@PathVariable("ticketId") Integer ticketId) {
//        return ticketRepository.findById(ticketId).map(ticket -> modelMapper.map(ticket, TicketDto.class))
//                .map(ticketDto -> new ResponseEntity<>(ticketDto, HttpStatus.OK))
//                .orElseThrow(() -> new EntityNotFoundException(Ticket.class, ticketId));
        return ResponseEntity.ok(feignService.findTicketById(ticketId));
    }

    @PutMapping("/{id}")
    public ResponseEntity editDriver(@PathVariable("driverId") int driverId, @RequestBody @Valid CreateDriverCommand command) {
        Driver driver = driverService.editDriver(driverId, command);
        return new ResponseEntity<>(modelMapper.map(driver, DriverDto.class), HttpStatus.OK);

    }
}
