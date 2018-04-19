package it.heber.sandbox.springbootdemo.persistence.dao;

import it.heber.sandbox.springbootdemo.persistence.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Interface to use JPA repository programming model for company entities.
 * It includes all functions to interact with ths repository
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {

    /**
     * find all companies 
     * @param pageable
     * @return
     */
    @RestResource
    Page<Company> findAll(Pageable pageable);
}
