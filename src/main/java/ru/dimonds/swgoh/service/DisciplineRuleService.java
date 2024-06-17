package ru.dimonds.swgoh.service;

import ru.dimonds.swgoh.dao.entity.DisciplineRuleEntity;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;

import java.util.List;

public interface DisciplineRuleService extends CrudService<Long, DisciplineRuleEntity, DisciplineRuleDto> {
    List<DisciplineRuleDto> getRulesByBot(String botType);
}
