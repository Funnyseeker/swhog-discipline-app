package ru.dimonds.swgoh.service;

import org.springframework.data.domain.Sort;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.model.dto.SearchDatesDto;

import java.util.List;

public interface PlayerService extends CrudService<Long, PlayerEntity, PlayerDto> {
    List<PlayerDto> getAll(SearchDatesDto searchDates, Sort sort);
}
