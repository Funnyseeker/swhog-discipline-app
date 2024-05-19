package ru.dimonds.swgoh.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dimonds.swgoh.dao.entity.AbstractEntity;
import ru.dimonds.swgoh.dao.repo.AbstractRepository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GenericMapper<PK extends Serializable, T extends AbstractEntity<PK>, D> {

    @Autowired
    protected AbstractRepository<T, PK> repository;

    public abstract D toDto(T entity);

    public abstract T toEntity(D dto);

    public PK toId(T entity) {
        return entity != null ? entity.getId() : null;
    }

    public T fromId(PK id)
    throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        if (id == null) {
            return null;
        }
        return repository.findById(id).orElse(null);
    }

    public Set<PK> toIds(Set<T> set) {
        if (set == null) {
            return null;
        }
        return set.stream()
                  .map(AbstractEntity::getId)
                  .collect(Collectors.toSet());
    }

    public Set<T> fromIds(Set<PK> ids)
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        if (ids == null) {
            return null;
        }
        return new HashSet<>(repository.findAllById(ids));
    }

    public List<PK> toIds(List<T> set) {
        if (set == null) {
            return null;
        }
        return set.stream()
                  .map(AbstractEntity::getId)
                  .toList();
    }

    public List<T> fromIds(List<PK> ids)
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        if (ids == null) {
            return null;
        }
        return repository.findAllById(ids);
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        return (
                (Class<T>) (
                        (ParameterizedType) ((Class<?>) getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0]
                ).getActualTypeArguments()[1]
        );
    }
}
