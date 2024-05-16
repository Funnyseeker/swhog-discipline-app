package ru.dimonds.swgoh.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.model.dto.SearchDatesDto;
import ru.dimonds.swgoh.service.PlayerService;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class PlayerServiceImpl extends CrudServiceImpl<Long, PlayerEntity, PlayerDto> implements PlayerService {

    @Override
    public List<PlayerDto> getAll(SearchDatesDto searchDates, Sort sort) {
        if (searchDates != null && (searchDates.getStart() != null || searchDates.getEnd() != null)) {
            Specification<PlayerEntity> playerEntitySpecification =
                    (root, query, criteriaBuilder) -> {
                        if (searchDates.getStart() == null) {

                            return criteriaBuilder.lessThanOrEqualTo(
                                    root.join("playerDisciplineHistory")
                                        .get("date"),
                                    OffsetDateTime.ofInstant(searchDates.getEnd().toInstant(), ZoneOffset.UTC)
                            );
                        }
                        if (searchDates.getEnd() == null) {
                            return criteriaBuilder.lessThanOrEqualTo(
                                    root.join("playerDisciplineHistory")
                                        .get("date"),
                                    OffsetDateTime.ofInstant(searchDates.getStart().toInstant(), ZoneOffset.UTC)
                            );
                        }
                        return criteriaBuilder.between(
                                root.join("playerDisciplineHistory")
                                    .get("date"),
                                OffsetDateTime.ofInstant(searchDates.getStart().toInstant(), ZoneOffset.UTC),
                                OffsetDateTime.ofInstant(searchDates.getEnd().toInstant(), ZoneOffset.UTC)
                        );
                    };
            return repo.findAll(playerEntitySpecification, sort)
                       .stream()
                       .map(mapper::toDto)
                       .toList();
        }
        return repo.findAll(sort)
                   .stream()
                   .map(mapper::toDto)
                   .toList();
    }
}
