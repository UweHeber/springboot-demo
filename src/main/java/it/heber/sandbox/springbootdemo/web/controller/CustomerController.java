package it.heber.sandbox.springbootdemo.web.controller;

import it.heber.sandbox.springbootdemo.persistence.dao.CustomerRepository;
import it.heber.sandbox.springbootdemo.persistence.dao.CustomerSpecificationsBuilder;
import it.heber.sandbox.springbootdemo.persistence.model.Customer;
import it.heber.sandbox.springbootdemo.web.util.SearchOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * REST controller to provide an API for customer access
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customers;

    @Autowired
    public CustomerController(CustomerRepository customers) {
        this.customers = customers;
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Customer> search(@RequestParam(value = "search", required = false) String search, Pageable pageable) {

        if (search == null) {
            return customers.findAll(pageable);
        }
        CustomerSpecificationsBuilder builder = new CustomerSpecificationsBuilder();
        String operationSetExper = StringUtils.join(SearchOperation.SIMPLE_OPERATION_SET, "|");
        Pattern pattern = Pattern.compile(
                "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }

        Specification<Customer> spec = builder.build();
        return customers.findAll(spec, pageable);
    }
}
