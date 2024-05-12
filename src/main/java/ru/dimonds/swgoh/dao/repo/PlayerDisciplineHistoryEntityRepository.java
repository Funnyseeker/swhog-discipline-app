package ru.dimonds.swgoh.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;

@Repository
public interface PlayerDisciplineHistoryEntityRepository extends CrudRepository<PlayerDisciplineHistoryEntity, Long> {
}