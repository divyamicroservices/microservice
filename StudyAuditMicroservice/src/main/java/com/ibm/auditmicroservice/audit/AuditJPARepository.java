package com.ibm.auditmicroservice.audit;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AuditJPARepository extends JpaRepository<Audit, Integer> {

}
