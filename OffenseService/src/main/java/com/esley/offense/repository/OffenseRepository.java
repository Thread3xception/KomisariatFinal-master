package com.esley.offense.repository;

import com.esley.offense.model.Offense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffenseRepository extends JpaRepository<Offense, Integer> {
    Optional<Offense> findById(int id);
    boolean existsById(int id);
}
