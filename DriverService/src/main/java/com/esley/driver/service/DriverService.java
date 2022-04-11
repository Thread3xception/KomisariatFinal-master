package com.esley.driver.service;


import com.esley.driver.exception.DriverNotFoundException;
import com.esley.driver.exception.EntityNotFoundException;
import com.esley.driver.model.Driver;
import com.esley.driver.model.command.CreateDriverCommand;
import com.esley.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final FeignService feignService;

    @Autowired
    private EmailSenderService senderService;

    public void saveDriver(Driver driver) {
        driverRepository.saveAndFlush(driver);
    }

    @Transactional(readOnly = true) // dirty checking - sprawdzanie i nadpisywanie danych
    public Driver findDriverById(Integer id) {
        return driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Driver.class, id));
    }

    @Transactional(readOnly = true) // dirty checking - sprawdzanie i nadpisywanie danych
    public Driver findDriverByEmail(String email) {
        return driverRepository.findByEmail(email).orElseThrow(() -> new DriverNotFoundException(format("Driver with id: %s not found!", email)));
    }

    public Optional<Driver> findByDriverIdOptional(Integer id) {
        return driverRepository.findById(id);
    }

    public Page findAllPage(Pageable pageable) {
        return driverRepository.findAll(pageable);
    }

    public Driver editDriver(Integer id, CreateDriverCommand command) {
        return driverRepository.findById(id)
                .map(driver -> {
                    driver.setEmail(command.getEmail());
                    driver.setName(command.getName());
                    driver.setSurname(command.getSurname());
                    driver.setYearsOld(command.getYearsOld());
                    driver.setScore(command.getScore());
                    return driver;
                })
                .orElseThrow(() -> new DriverNotFoundException("Driver with id: %s not found!"));
    }


    public int getTotalScore(Integer driverId) {
        return Optional.ofNullable(feignService.findTicketListForDriver(driverId))
                .orElse(Collections.emptySet())
                .stream()
                .mapToInt(n -> feignService.getSumOfScoreTicketById(n.getId()))
                .sum();
    }



    @Scheduled(fixedRate = 30000)
    @Transactional
    public void checkScore() {
        List<Driver> drivers = driverRepository.findAll();
        for(Driver driver : drivers) {
            int id = driver.getId();
            int score = getTotalScore(driver.getId());
            driverRepository.updateScore(id, score);
            sendMail(driver);
        }
    }

    public void sendMail(Driver driver) {
        senderService.sendEmail(driver.getEmail(), "Utrata Prawa Jazdy",
                "Kierowca o mailu: " + driver.getEmail() +
                        " stracił prawojazdy z powodu przekroczenia dozwolonej ilości punktów.");
    }
}
