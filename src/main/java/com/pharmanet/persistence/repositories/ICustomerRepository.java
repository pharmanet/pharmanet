package com.pharmanet.persistence.repositories;

import com.pharmanet.persistence.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
