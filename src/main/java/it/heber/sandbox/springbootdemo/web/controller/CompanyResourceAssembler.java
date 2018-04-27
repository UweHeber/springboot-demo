package it.heber.sandbox.springbootdemo.web.controller;

import it.heber.sandbox.springbootdemo.persistence.model.Company;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CompanyResourceAssembler implements ResourceAssembler<Company, Resource<Company>> {

    @Override
    public Resource<Company> toResource(Company company) {
        Resource<Company> resource = new Resource<>(company);
        addLinks(resource);
        return resource;
    }

    public void addLinks(Resource<Company> resource) {
        resource.add(linkTo(CompanyController.class).slash(resource.getContent().getId()).withSelfRel());
    }
}
