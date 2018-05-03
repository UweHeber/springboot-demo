package it.heber.sandbox.springbootdemo.exception;

import it.heber.sandbox.springbootdemo.web.controller.CompanyController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ResourceControllerAdvice extends ResponseEntityExceptionHandler {

    private Class<?> resourceClass;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<VndErrors> notFoundException(final ResourceNotFoundException e) {
        this.resourceClass = e.getResourceClass();
        return error(e, HttpStatus.NOT_FOUND, e.getId().toString());
    }

    private ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {

        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());

        linkTo(CompanyController.class).slash(logRef).withSelfRel().toString();

        return new ResponseEntity<>(new VndErrors(logRef, message,
                new Link(linkTo(resourceClass).slash(logRef).toString(), "self"),
                new Link("http://path.to/describes", "describes"),
                new Link("http://path.to/help", "help")), httpStatus);
    }
}
