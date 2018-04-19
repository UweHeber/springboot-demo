package it.heber.sandbox.springbootdemo.web.controller;

import it.heber.sandbox.springbootdemo.persistence.model.Company;
import it.heber.sandbox.springbootdemo.persistence.dao.CompanySpecificationsBuilder;
import it.heber.sandbox.springbootdemo.web.util.SearchOperation;
import it.heber.sandbox.springbootdemo.persistence.dao.CompanyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyRepository companies;

    @Autowired
    public CompanyController(CompanyRepository companies) {
        this.companies = companies;
    }



    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Company> search(@RequestParam(value = "search", required = false) String search, Pageable pageable) {

        if (search == null) {
            return companies.findAll(pageable);
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
        return companies.findAll(spec, pageable);
    }
}
