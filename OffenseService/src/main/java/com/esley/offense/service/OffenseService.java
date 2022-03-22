package com.esley.offense.service;

import com.esley.offense.exception.EntityNotFoundException;
import com.esley.offense.model.Offense;
import com.esley.offense.repository.OffenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OffenseService {

    @Autowired
    private OffenseRepository offenseRepository;

    public void saveOffense(Offense offense) {
        offenseRepository.saveAndFlush(offense);
    }

    @Transactional(readOnly = true)
    public Offense findOffenseById(Integer id) {
        return offenseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Offense.class, id));
    }

    public Page findAllPage(Pageable pageable) {
        return offenseRepository.findAll(pageable);
    }
}
