package com.cbs.ppm.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbs.ppm.customer.model.LegalEntity;

@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {

}
