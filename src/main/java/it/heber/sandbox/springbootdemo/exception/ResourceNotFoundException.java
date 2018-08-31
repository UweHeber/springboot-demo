package it.heber.sandbox.springbootdemo.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private Long id;
    private Class<?> resourceClass;

    public ResourceNotFoundException(Class<?> resourceClass, long id) {
        super(String.format("Resource with ID %d could not be found", id));
        this.id = id;
        this.resourceClass = resourceClass;
    }
}