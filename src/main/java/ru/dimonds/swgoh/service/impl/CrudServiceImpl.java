package ru.dimonds.swgoh.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;
import ru.dimonds.swgoh.model.mapper.GenericMapper;
import ru.dimonds.swgoh.service.CrudService;

import java.io.Serializable;

public abstract class CrudServiceImpl<PK extends Serializable, T extends AbstractPersistable<PK>, D>
        implements CrudService<PK, T, D>
{
    @Autowired
    protected CrudRepository<T, PK>   repo;
    @Autowired
    protected GenericMapper<PK, T, D> mapper;

    @Override
    @Transactional
    public D save(D dto) {
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }
}
