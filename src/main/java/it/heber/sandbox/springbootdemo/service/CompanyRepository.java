package it.heber.sandbox.springbootdemo.service;

import it.heber.sandbox.springbootdemo.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CompanyRepository  extends JpaRepository<Company, Integer> {

    @RestResource
    Page<Company> findAll(Pageable pageable);
}
