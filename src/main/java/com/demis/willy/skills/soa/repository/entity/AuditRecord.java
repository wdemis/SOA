package com.demis.willy.skills.soa.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AuditRecord {

    @Id
    private Integer id;
}
