package com.pharmanet.persistence.repositories;

import com.pharmanet.persistence.entities.Laboratory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILaboratoryRepository extends CrudRepository<Laboratory, Long> {
    Optional<Laboratory> findByName(String name);
}
