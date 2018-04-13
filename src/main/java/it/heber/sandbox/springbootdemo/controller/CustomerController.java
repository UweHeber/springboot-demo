package it.heber.sandbox.springbootdemo.controller;

import it.heber.sandbox.springbootdemo.dao.CustomerDao;
import it.heber.sandbox.springbootdemo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CustomerController {

    @Autowired
    CustomerDao customerDAO;

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Collection<Customer>> getCustomers() {
        return new ResponseEntity<>(customerDAO.findAll(), HttpStatus.OK);
    }

}
