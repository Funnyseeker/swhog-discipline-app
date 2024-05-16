package ru.dimonds.swgoh.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.dimonds.swgoh.dao.entity.AbstractEntity;
import ru.dimonds.swgoh.dao.repo.AbstractRepository;
import ru.dimonds.swgoh.model.mapper.GenericMapper;
import ru.dimonds.swgoh.service.CrudService;

import java.io.Serializable;
import java.util.List;

public abstract class CrudServiceImpl<PK extends Serializable, T extends AbstractEntity<PK>, D>
        implements CrudService<PK, T, D>
{
    @Autowired
    protected AbstractRepository<T, PK> repo;
    @Autowired
    protected GenericMapper<PK, T, D>   mapper;

    @Override
    @Transactional
    public D save(D dto) {
        return this.mapper.toDto(this.repo.save(this.mapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public List<D> getAll() {
        return repo.findAll().stream()
                   .map(mapper::toDto)
                   .toList();
    }

    @Override
    @Transactional
    public List<D> getAll(Sort sort) {
        return repo.findAll(sort).stream()
                   .map(mapper::toDto)
                   .toList();
    }

    @Override
    public D findById(PK id) {
        return mapper.toDto(repo.findById(id).get());
    }
}
