package com.start.contract.repo;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.start.contract.dto.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    List<Customer> findByLastName(String lastName);
}
