package it.heber.sandbox.springbootdemo.persistence.dao;

import it.heber.sandbox.springbootdemo.persistence.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Interface to allow execution of Specifications based on the
 * JPA criteria API against JPA repository with customer entities.
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    /**
     * fetch all customer, based on the pagination info
     *
     * @param pageable pagination info, contains the part of
     *                 the total result set that is to be returned
     * @return all customers, which match the pagination info
     */
    @RestResource
    Page<Customer> findAll(Pageable pageable);

    @RestResource
    Page<Customer> findByFirstName(String firstName, Pageable pageable);

    @RestResource
    Page<Customer> findByLastName(String lastName, Pageable pageable);

    @RestResource
    Page<Customer> findByCompany(String company, Pageable pageable);
}
