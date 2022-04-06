package com.esley.offense.service;

import com.esley.offense.exception.EntityNotFoundException;
import com.esley.offense.model.Offense;
import com.esley.offense.repository.OffenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OffenseService {

    private final OffenseRepository offenseRepository;

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

    @Transactional(readOnly = true)
    public boolean existsOffenseById(int id) {
        return offenseRepository.existsById(id);
    }

    @Transactional
    public void deleteOffenseByiD(int id) {
        if(!offenseRepository.existsById(id)) {
            throw new EntityNotFoundException(Offense.class, id);
        }
        offenseRepository.deleteById(id);
    }
}
