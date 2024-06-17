package ru.dimonds.swgoh.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.DisciplineRuleEntity;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.service.DisciplineRuleService;

import java.util.List;

@Service
public class DisciplineRuleServiceImpl extends CrudServiceImpl<Long, DisciplineRuleEntity, DisciplineRuleDto>
        implements DisciplineRuleService
{
    @Override
    public List<DisciplineRuleDto> getRulesByBot(String botType) {
        return repo.findAll(
                           (Specification<DisciplineRuleEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                                   root.get("botType"),
                                   botType
                           ),
                           Sort.unsorted()
                   )
                   .stream()
                   .map(mapper::toDto)
                   .toList();
    }
}
