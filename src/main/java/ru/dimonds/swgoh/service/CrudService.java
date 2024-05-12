package ru.dimonds.swgoh.service;

import ru.dimonds.swgoh.dao.entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudService<PK extends Serializable, T extends AbstractEntity<PK>, D> {
    D save(D dto);

    List<D> getAll();
}
