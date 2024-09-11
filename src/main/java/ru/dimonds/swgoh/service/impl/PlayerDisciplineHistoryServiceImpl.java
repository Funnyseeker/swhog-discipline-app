package ru.dimonds.swgoh.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;

import java.util.List;

@Service
public class PlayerDisciplineHistoryServiceImpl
        extends CrudServiceImpl<Long, PlayerDisciplineHistoryEntity, PlayerDisciplineHistoryDto>
        implements PlayerDisciplineHistoryService
{
    @Override
    @Transactional
    public List<PlayerDisciplineHistoryDto> getByPlayerId(Long playerId, Sort sort) {
        Specification<PlayerDisciplineHistoryEntity>
                specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                        root.get("player"),
                        playerId
                );
        return repo.findAll(specification, sort)
                   .stream()
                   .map(mapper::toDto)
                   .toList();
    }

    @Override
    @Transactional
    public void deleteByPlayerId(Long playerId) {
        Specification<PlayerDisciplineHistoryEntity>
                specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                        root.join("player")
                            .get("id"),
                        playerId
                );
        repo.findAll(specification)
            .forEach(repo::delete);

    }
}
