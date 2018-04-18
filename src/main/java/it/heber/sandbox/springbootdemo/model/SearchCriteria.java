package it.heber.sandbox.springbootdemo.model;

import lombok.Data;

/**
 * The SearchCriteria implementation holds a basic representation of a constraint –
 * and it’s based on this constraint that we’re going to be constructing the query:
 *
 * key: the field name – for example, firstName, age, … etc.
 * operation: the operation – for example, equality, less than, … etc.
 * value: the field value – for example, john, 25, … etc.
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
@Data
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
