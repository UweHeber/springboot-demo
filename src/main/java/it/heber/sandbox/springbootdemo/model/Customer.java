package it.heber.sandbox.springbootdemo.model;

import lombok.Data;

/**
 * Simple POJO as value object, using Lombok to
 * reduce Getter/Setter/equals/...
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
@Data
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String company;

    /**
     * Standard constructor to create new customer object
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param company
     */
//    public Customer(Long id, String firstName, String lastName, String company) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.company = company;
//    }
}
