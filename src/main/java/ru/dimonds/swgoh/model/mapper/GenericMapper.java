package ru.dimonds.swgoh.model.mapper;

import ru.dimonds.swgoh.dao.entity.AbstractEntity;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface GenericMapper<PK extends Serializable, T extends AbstractEntity<PK>, D> {

    D toDto(T entity);

    T toEntity(D dto);

    default PK toId(T entity) {
        return entity != null? entity.getId(): null;
    }

    default T fromId(PK id)
    throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        if (id == null) {
            return null;
        }
        T entity = getEntityClass().getConstructor()
                                   .newInstance();
        entity.setId(id);
        return entity;
    }

    default Set<PK> toIds(Set<T> set) {
        if (set == null) {
            return null;
        }
        return set.stream()
                  .map(AbstractEntity::getId)
                  .collect(Collectors.toSet());
    }

    default Set<T> fromIds(Set<PK> ids)
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        if (ids == null) {
            return null;
        }
        Set<T> set = new HashSet<>();
        for (PK id : ids) {
            T entity = getEntityClass().getConstructor()
                                       .newInstance();
            entity.setId(id);
            set.add(entity);
        }
        return set;
    }

    @SuppressWarnings("unchecked")
    default Class<T> getEntityClass() {
        return (
                (Class<T>) (
                        (ParameterizedType) getClass()
                                .getGenericSuperclass()
                ).getActualTypeArguments()[1]
        );
    }
}
