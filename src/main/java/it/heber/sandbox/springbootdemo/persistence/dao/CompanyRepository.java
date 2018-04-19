package it.heber.sandbox.springbootdemo.persistence.dao;

import it.heber.sandbox.springbootdemo.persistence.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Interface to allow execution of Specifications based on the
 * JPA criteria API against JPA repository with company entities.
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {

    /**
     * fetch all companies, based on the pagination info
     *
     * @param pageable pagination info, contains the part of
     *                 the total result set that is to be returned
     * @return all companies, which match the pagination info
     */
    @RestResource
    Page<Company> findAll(Pageable pageable);
}
