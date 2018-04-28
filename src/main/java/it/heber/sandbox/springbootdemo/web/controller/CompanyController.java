package it.heber.sandbox.springbootdemo.web.controller;

import it.heber.sandbox.springbootdemo.persistence.dao.CompanyRepository;
import it.heber.sandbox.springbootdemo.persistence.dao.CompanySpecificationsBuilder;
import it.heber.sandbox.springbootdemo.persistence.model.Company;
import it.heber.sandbox.springbootdemo.web.util.SearchOperation;
import it.heber.sandbox.springbootdemo.web.util.SimpleResourceAssembler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * REST controller to provide an API for companies access including Hypermedia support for Pageables
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyRepository companies;
    private final SimpleResourceAssembler<Company> resourceAssembler;

    @Autowired
    public CompanyController(CompanyRepository companies) {
        this.companies = companies;
        this.resourceAssembler = new SimpleResourceAssembler<>(this.getClass());
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity<PagedResources<Company>> search(@RequestParam(value = "search", required = false) String search, Pageable pageable, PagedResourcesAssembler assembler) {

        if (search == null) {
            return new ResponseEntity<>(assembler.toResource(companies.findAll(pageable)), HttpStatus.OK);
        }
        CompanySpecificationsBuilder builder = new CompanySpecificationsBuilder();
        String operationSetExper = StringUtils.join(SearchOperation.SIMPLE_OPERATION_SET, "|");
        Pattern pattern = Pattern.compile(
                "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)([\\w\\s]+)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }

        Specification<Company> spec = builder.build();
        return new ResponseEntity<>(assembler.toResource(companies.findAll(spec, pageable)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    HttpEntity<Resource<Company>> showCompany(@PathVariable long id) {

        return companies.findById(id)
                .map(company -> new ResponseEntity<>(resourceAssembler.toResource(company), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
