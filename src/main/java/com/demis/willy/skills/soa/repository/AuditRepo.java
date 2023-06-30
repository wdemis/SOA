package com.demis.willy.skills.soa.repository;

import com.demis.willy.skills.soa.repository.entity.AuditRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepo extends CrudRepository<AuditRecord, Integer> {

}
