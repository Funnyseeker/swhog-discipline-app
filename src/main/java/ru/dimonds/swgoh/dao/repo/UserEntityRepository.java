package ru.dimonds.swgoh.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimonds.swgoh.dao.entity.UserEntity;

@Repository
public interface UserEntityRepository extends AbstractRepository<UserEntity, Long> {
}