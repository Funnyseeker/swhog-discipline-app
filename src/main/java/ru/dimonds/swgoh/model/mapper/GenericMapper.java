package ru.dimonds.swgoh.model.mapper;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public interface GenericMapper<PK extends Serializable, T extends AbstractPersistable<PK>, D> {
    D toDto(T entity);

    T toEntity(D entity);
}
