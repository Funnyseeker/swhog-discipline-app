package ru.dimonds.swgoh.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimonds.swgoh.dao.entity.DisciplineRuleEntity;

@Repository
public interface DisciplineRuleEntityRepository extends CrudRepository<DisciplineRuleEntity, Long> {
}