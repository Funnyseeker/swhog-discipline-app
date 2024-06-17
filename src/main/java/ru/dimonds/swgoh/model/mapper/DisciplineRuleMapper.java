package ru.dimonds.swgoh.model.mapper;

import io.hypersistence.utils.hibernate.type.range.Range;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dimonds.swgoh.dao.entity.DisciplineRuleEntity;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.model.dto.RangeDto;

@Mapper(componentModel = "spring", uses = {GuildMapper.class})
public abstract class DisciplineRuleMapper extends GenericMapper<Long, DisciplineRuleEntity, DisciplineRuleDto> {

    @Override
    @Mapping(target = "ruleValues", ignore = true)
    @Mapping(target = "botType", ignore = true)
    public abstract DisciplineRuleEntity toEntity(DisciplineRuleDto dto);

    protected Range<Long> map(RangeDto<Long> ruleValues) {
        return Range.closedOpen(ruleValues.getMin(), ruleValues.getMax());
    }

    protected RangeDto<Long> map(Range<Long> ruleValues) {
        return RangeDto.<Long>builder()
                       .min(ruleValues.lower())
                       .max(ruleValues.upper())
                       .build();
    }
}
