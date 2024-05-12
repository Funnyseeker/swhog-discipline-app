package ru.dimonds.swgoh.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import ru.dimonds.swgoh.dao.entity.AbstractEntity;
import ru.dimonds.swgoh.model.mapper.GenericMapper;
import ru.dimonds.swgoh.service.CrudService;

import java.io.Serializable;

public abstract class CrudServiceImpl<PK extends Serializable, T extends AbstractEntity<PK>, D>
        implements CrudService<PK, T, D>
{
    @Autowired
    protected CrudRepository<T, PK>   repo;
    @Autowired
    protected GenericMapper<PK, T, D> mapper;

    @Override
    @Transactional
    public D save(D dto) {
        return this.mapper.toDto(this.repo.save(this.mapper.toEntity(dto)));
    }
}
