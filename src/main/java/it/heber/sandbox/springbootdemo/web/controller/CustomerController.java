package it.heber.sandbox.springbootdemo.web.controller;

import it.heber.sandbox.springbootdemo.persistence.model.Customer;
import it.heber.sandbox.springbootdemo.persistence.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customers;

    @Autowired
    public CustomerController(CustomerRepository customers)  {
        this.customers = customers;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Customer> getCustomers(Pageable pageable) {
        return customers.findAll(pageable);
    }
}
