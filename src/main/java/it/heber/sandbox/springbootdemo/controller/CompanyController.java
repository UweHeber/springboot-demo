package it.heber.sandbox.springbootdemo.controller;

import it.heber.sandbox.springbootdemo.model.Company;
import it.heber.sandbox.springbootdemo.model.CompanySpecificationsBuilder;
import it.heber.sandbox.springbootdemo.model.SearchOperation;
import it.heber.sandbox.springbootdemo.service.CompanyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


//    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Page<Company> getCompanies(Pageable pageable) {
//        return companies.findAll(pageable);
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Company> search(@RequestParam(value = "search") String search) {
        CompanySpecificationsBuilder builder = new CompanySpecificationsBuilder();
        String operationSetExper = StringUtils.join(SearchOperation.SIMPLE_OPERATION_SET, "|");
        //String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
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
        return companies.findAll(spec);
    }
}
