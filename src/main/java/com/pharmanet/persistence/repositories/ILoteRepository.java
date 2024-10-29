package com.pharmanet.persistence.repositories;


import com.pharmanet.persistence.entities.Lote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoteRepository extends CrudRepository<Lote, Long> {
}
