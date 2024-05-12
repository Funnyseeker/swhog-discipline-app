package ru.dimonds.swgoh.service;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public interface CrudService<PK extends Serializable, T extends AbstractPersistable<PK>, D> {
    D save(D dto);
}
