package it.heber.sandbox.springbootdemo.dao;

import it.heber.sandbox.springbootdemo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Collection<Customer> findAll() {

        String sql = "SELECT * FROM CUSTOMER";

        List customers = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Customer.class));

        return customers;
    }
}
