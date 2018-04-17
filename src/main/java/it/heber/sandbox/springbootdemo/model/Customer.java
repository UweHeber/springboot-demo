package it.heber.sandbox.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Simple POJO as value object, using Lombok to
 * reduce Getter/Setter/toString/equals/...
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
@Data
@Entity
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "customer_generator")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "company")
    private String company;
}
