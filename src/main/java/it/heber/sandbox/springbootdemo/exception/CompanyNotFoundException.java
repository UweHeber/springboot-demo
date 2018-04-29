package it.heber.sandbox.springbootdemo.exception;

import lombok.Getter;

@Getter
public class CompanyNotFoundException  extends RuntimeException {

    private final Long id;

    public CompanyNotFoundException(final long id) {
        super("Company could not be found with id: " + id);
        this.id = id;
    }
}