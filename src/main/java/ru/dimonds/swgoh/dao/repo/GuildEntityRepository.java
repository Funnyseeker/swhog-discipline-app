package ru.dimonds.swgoh.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimonds.swgoh.dao.entity.GuildEntity;

import java.util.Optional;

@Repository
public interface GuildEntityRepository extends CrudRepository<GuildEntity, Long> {
    Optional<GuildEntity> findGuildByUrl(String url);
}