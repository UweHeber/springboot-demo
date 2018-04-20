package it.heber.sandbox.springbootdemo.persistence.dao;

import it.heber.sandbox.springbootdemo.persistence.model.Customer;
import it.heber.sandbox.springbootdemo.web.util.SearchCriteria;
import it.heber.sandbox.springbootdemo.web.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder to incorporate with new search operations for customer entities via JPA query
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
public class CustomerSpecificationsBuilder {

    private List<SearchCriteria> params = new ArrayList<>();

    public CustomerSpecificationsBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {

            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Customer> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Customer>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new CustomerSpecification(param));
        }

        Specification<Customer> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
