package it.heber.sandbox.springbootdemo.persistence.dao;

import it.heber.sandbox.springbootdemo.persistence.model.Customer;
import it.heber.sandbox.springbootdemo.web.util.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Customer filter – querying with custom Spring Data JPA Specifications
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
public class CustomerSpecification implements Specification<Customer> {

    private SearchCriteria criteria;

    public CustomerSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(
            Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(
                        criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(
                        criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(
                        criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(
                        criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(
                        criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(
                        criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                return null;
        }
    }
}
