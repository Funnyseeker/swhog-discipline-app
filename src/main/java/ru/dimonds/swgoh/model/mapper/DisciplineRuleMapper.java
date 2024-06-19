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

    public Range<Long> map(RangeDto<Long> ruleValues) {
        Range<Long> range = Range.infinite(Long.class);
        if (ruleValues.getMax() == null && ruleValues.getMin() != null) {
            range = Range.openInfinite(ruleValues.getMin());
        } else if (ruleValues.getMin() == null && ruleValues.getMax() != null) {
            range = Range.infiniteOpen(ruleValues.getMax());
        } else if (ruleValues.getMin() != null) {
            range = Range.openClosed(ruleValues.getMin(), ruleValues.getMax());
        }
        return range;
    }

    public RangeDto<Long> map(Range<Long> ruleValues) {
        return RangeDto.<Long>builder()
                       .min(ruleValues.lower())
                       .max(ruleValues.upper())
                       .build();
    }
}
