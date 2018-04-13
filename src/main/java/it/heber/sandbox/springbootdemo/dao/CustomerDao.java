package it.heber.sandbox.springbootdemo.dao;

import it.heber.sandbox.springbootdemo.model.Customer;

import java.util.Collection;

/**
 * Data Access Object to query related customer objects
 */
public interface CustomerDao {
    Collection<Customer> findAll();
}
