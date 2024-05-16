package ru.dimonds.swgoh.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T, PK> extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {
}
