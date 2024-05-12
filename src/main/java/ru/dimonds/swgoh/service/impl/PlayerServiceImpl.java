package ru.dimonds.swgoh.service.impl;

import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.service.PlayerService;

@Service
public class PlayerServiceImpl extends CrudServiceImpl<Long, PlayerEntity, PlayerDto> implements PlayerService {
}
