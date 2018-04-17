package it.heber.sandbox.springbootdemo.controller;

import it.heber.sandbox.springbootdemo.model.Company;
import it.heber.sandbox.springbootdemo.service.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<Company> getCompanies(Pageable pageable) {
        return companies.findAll(pageable);
    }
}
