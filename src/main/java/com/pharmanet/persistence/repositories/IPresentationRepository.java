package com.pharmanet.persistence.repositories;

import com.pharmanet.persistence.entities.Presentation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPresentationRepository extends CrudRepository<Presentation, Long> {
    Optional<Presentation> findByName(String name);
}
