package ru.dimonds.swgoh.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.GuildEntity;
import ru.dimonds.swgoh.dao.repo.GuildEntityRepository;
import ru.dimonds.swgoh.model.dto.GuildDto;
import ru.dimonds.swgoh.service.GuildService;

import java.util.Optional;

@Service
public class GuildServiceImpl extends CrudServiceImpl<Long, GuildEntity, GuildDto> implements GuildService {

    @Override
    @Transactional
    public Optional<GuildDto> findGuildByUrl(String url) {
        GuildEntityRepository repoCast = (GuildEntityRepository) this.repo;
        return repoCast.findGuildByUrl(url)
                       .map(mapper::toDto);
    }
}
