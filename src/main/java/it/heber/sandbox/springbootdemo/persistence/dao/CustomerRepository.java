package it.heber.sandbox.springbootdemo.persistence.dao;

import it.heber.sandbox.springbootdemo.persistence.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @RestResource
    Page<Customer> findAll(Pageable pageable);

    @RestResource
    Page<Customer> findByFirstName(String firstName, Pageable pageable);

    @RestResource
    Page<Customer> findByLastName(String lastName, Pageable pageable);

    @RestResource
    Page<Customer> findByCompany(String company, Pageable pageable);
}
