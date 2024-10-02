package com.cbs.ppm.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbs.ppm.customer.model.NaturalPerson;

@Repository
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {

}
