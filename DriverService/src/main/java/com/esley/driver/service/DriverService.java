package com.esley.driver.service;


import com.esley.clients.ticket.Ticket;
import com.esley.driver.exception.DriverNotFoundException;
import com.esley.driver.exception.EntityNotFoundException;
import com.esley.driver.model.Driver;
import com.esley.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final FeignService feignService;
    private final EmailSenderService senderService;

    public void saveDriver(Driver driver) {
        driverRepository.saveAndFlush(driver);
    }

    @Transactional(readOnly = true) // dirty checking - sprawdzanie i nadpisywanie danych
    public Driver findDriverById(Integer id) {
        return driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Driver.class, id));
    }

    @Transactional(readOnly = true) // dirty checking - sprawdzanie i nadpisywanie danych
    public Driver findDriverByEmail(String email) {
        return driverRepository.findByEmail(email).orElseThrow(() -> new DriverNotFoundException(format("Driver with email: %s not found!", email)));
    }

    public Optional<Driver> findByDriverIdOptional(Integer id) {
        return driverRepository.findById(id);
    }

    public Page findAllPage(Pageable pageable) {
        return driverRepository.findAll(pageable);
    }

//    public int getTotalScore(Integer driverId) {
//        return Optional.ofNullable(feignService.findTicketListForDriver(driverId))
//                .orElseGet(Collections::emptySet)
//                .stream()
//                //.filter(n -> n.checkStatus() == true)
//                .collect(Collectors.summingInt(Ticket::get));
//
//    }

    // @Scheduled(fixedDelay = 24 * 60 * 60 * 1000) // 24 hours in milliseconds
//    @Scheduled(fixedRate = 50000)
//    public void checkScore() {
//        List<Driver> drivers = driverRepository.findAllWithTickets();
//
//        for(Driver driver : drivers) {
//            if (driver.getTotalScore() >= 24) {
//                sendMail(driver);
//            }
//        }
//
//    }
//    //N+1 - EAGER
//
//    public void sendMail(Driver driver) {
//        senderService.sendEmail(driver.getEmail(), "Utrata Prawa Jazdy",
//                "Kierowca o mailu: " + driver.getEmail() +
//                        " stracił prawojazdy z powodu przekroczenia dozwolonej ilości punktów.");
//    }
}
