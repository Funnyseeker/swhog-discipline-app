package ru.dimonds.swgoh.service.impl;

import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.DisciplineRuleEntity;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.service.DisciplineRuleService;

@Service
public class DisciplineRuleServiceImpl extends CrudServiceImpl<Long, DisciplineRuleEntity, DisciplineRuleDto>
        implements DisciplineRuleService
{
}
