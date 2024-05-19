package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import ru.dimonds.swgoh.dao.entity.DisciplineRuleEntity;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;

@Mapper(componentModel = "spring", uses = {GuildMapper.class})
public abstract class DisciplineRuleMapper extends GenericMapper<Long, DisciplineRuleEntity, DisciplineRuleDto>{
}
