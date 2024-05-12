package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "guilds")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GuildEntity extends AbstractPersistable<Long> {
    @Column(columnDefinition = "text")
    private String          guild_url;
    @Column(columnDefinition = "text")
    private String          name;
    private OffsetDateTime  lastSyncDate;
    @OneToMany(mappedBy = "guild")
    @ToString.Exclude
    private Set<UserEntity> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GuildEntity that = (GuildEntity) o;
        return Objects.equals(guild_url, that.guild_url) &&
               Objects.equals(lastSyncDate, that.lastSyncDate) &&
               Objects.equals(userList, that.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), guild_url, lastSyncDate, userList);
    }
}