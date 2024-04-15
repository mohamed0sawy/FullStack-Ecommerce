package com.ecommerce.FullStackEcommerce.repository;

import com.ecommerce.FullStackEcommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.xml.xpath.XPath;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries" ,path = "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
