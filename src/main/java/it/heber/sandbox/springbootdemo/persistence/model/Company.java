package it.heber.sandbox.springbootdemo.persistence.model;

import lombok.Data;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

/**
 * JPA entity, using Lombok to reduce Getter/Setter/toString/equals/...
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
@Data
@Entity
public class Company implements Identifiable<Long> {

    @Id
    @SequenceGenerator(name = "company_generator", sequenceName = "company_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "company_generator")
    private Long id;

    @Column(name = "name")
    private String name;
}
