package it.heber.sandbox.springbootdemo.persistence.model;

import lombok.Data;

import javax.persistence.*;

/**
 * JPA entity, using Lombok to reduce Getter/Setter/toString/equals/...
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
@Data
@Entity
public class Company {

    @Id
    @SequenceGenerator(name = "company_generator", sequenceName = "company_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "company_generator")
    Long id;

    @Column(name = "name")
    String name;
}
