package ru.dimonds.swgoh.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.model.dto.SearchFiltersDto;
import ru.dimonds.swgoh.service.PlayerService;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl extends CrudServiceImpl<Long, PlayerEntity, PlayerDto> implements PlayerService {

    @Override
    public List<PlayerDto> getAll(SearchFiltersDto filtersDto, Sort sort) {
        if (filtersDto != null && !filtersDto.isEmpty()) {

            Specification<PlayerEntity> dateRangeSpec = getDateRangeSpecification(filtersDto);
            Specification<PlayerEntity> rulesSpec     = getRulesSpec(filtersDto);
            Specification<PlayerEntity> finalSpec = dateRangeSpec != null && rulesSpec != null
                                                    ? dateRangeSpec.and(rulesSpec)
                                                    : Optional.ofNullable(dateRangeSpec).orElse(rulesSpec);

            return repo.findAll(finalSpec, sort)
                       .stream()
                       .map(mapper::toDto)
                       .map(
                               playerDto -> {
                                   if (filtersDto.getStart() != null || filtersDto.getEnd() != null) {
                                       Set<PlayerDisciplineHistoryDto> history =
                                               playerDto.getPlayerDisciplineHistory()
                                                        .stream()
                                                        .filter(
                                                                hist -> filterBySearchDates(
                                                                        filtersDto,
                                                                        hist
                                                                )
                                                        )
                                                        .collect(Collectors.toSet());

                                       playerDto.setPlayerDisciplineHistory(history);
                                       playerDto.setDisciplinePointsTotal(
                                               history.stream()
                                                      .mapToLong(PlayerDisciplineHistoryDto::getDisciplinePoints)
                                                      .sum()
                                       );
                                   }
                                   return playerDto;
                               }
                       )
                       .toList();
        }
        return repo.findAll(sort)
                   .stream()
                   .map(mapper::toDto)
                   .toList();
    }

    @Override
    @Transactional
    public List<PlayerDto> findByGuild(Long guildId) {
        Specification<PlayerEntity> specification = (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("guildId"),
                guildId
        );
        return repo.findAll(specification)
                   .stream()
                   .map(mapper::toDto)
                   .toList();
    }

    private Specification<PlayerEntity> getDateRangeSpecification(
            SearchFiltersDto searchDates
    )
    {
        if ((searchDates.getStart() != null || searchDates.getEnd() != null)) {
            return
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
        }
        return null;
    }

    private Specification<PlayerEntity> getRulesSpec(
            SearchFiltersDto filtersDto
    )
    {
        if (filtersDto.getRules() != null && !filtersDto.getRules().isEmpty()) {
            return (root, query, criteriaBuilder) -> root.join("playerDisciplineHistory")
                                                         .get("id")
                                                         .in(
                                                                 filtersDto.getRules()
                                                                           .stream()
                                                                           .map(DisciplineRuleDto::getId)
                                                                           .toList()
                                                         );
        }
        return null;
    }

    private boolean filterBySearchDates(SearchFiltersDto searchDates, PlayerDisciplineHistoryDto hist) {
        if (searchDates.getStart() == null) {
            return !hist.getDate().isAfter(
                    OffsetDateTime.ofInstant(
                            searchDates.getEnd().toInstant(),
                            ZoneOffset.UTC
                    )
            );
        }
        if (searchDates.getEnd() == null) {
            return !hist.getDate()
                        .isBefore(
                                OffsetDateTime.ofInstant(
                                        searchDates.getStart().toInstant(),
                                        ZoneOffset.UTC
                                )
                        );
        }
        return !hist.getDate()
                    .isBefore(
                            OffsetDateTime.ofInstant(
                                    searchDates.getStart().toInstant(),
                                    ZoneOffset.UTC
                            )
                    )
               &&
               !hist.getDate()
                    .isAfter(
                            OffsetDateTime.ofInstant(
                                    searchDates.getEnd().toInstant(),
                                    ZoneOffset.UTC
                            )
                    );
    }
}
