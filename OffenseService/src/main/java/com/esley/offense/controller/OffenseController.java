package com.esley.offense.controller;

import com.esley.offense.model.Offense;
import com.esley.offense.model.command.CreateOffenseCommand;
import com.esley.offense.model.dto.OffenseDto;
import com.esley.offense.service.OffenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/offense")
@RequiredArgsConstructor
public class  OffenseController {

    private final OffenseService offenseService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllOffenses(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(offenseService.findAllPage(pageable)
                .map(offense -> modelMapper.map(offense, OffenseDto.class)));
    }

    @PostMapping
    public ResponseEntity saveOffense(@RequestBody @Valid CreateOffenseCommand command) {
        Offense newOffense = modelMapper.map(command, Offense.class);
        offenseService.saveOffense(newOffense);
        return new ResponseEntity(modelMapper.map(newOffense, OffenseDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/{offenseId}")
    public ResponseEntity getSingleOffense(@PathVariable("offenseId") Integer offenseId) {
        return ResponseEntity.ok(modelMapper.map(offenseService.findOffenseById(offenseId), OffenseDto.class));
    }

    @DeleteMapping("/{offenseId}")
    public ResponseEntity deleteOffense(@PathVariable("offenseId") Integer offenseId) {
        if(!offenseService.existsOffenseById(offenseId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        offenseService.deleteOffenseByiD(offenseId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
