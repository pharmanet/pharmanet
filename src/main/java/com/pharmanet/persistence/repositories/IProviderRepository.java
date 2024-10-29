package com.pharmanet.persistence.repositories;


import com.pharmanet.persistence.entities.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends CrudRepository<Provider, Long> {
}
