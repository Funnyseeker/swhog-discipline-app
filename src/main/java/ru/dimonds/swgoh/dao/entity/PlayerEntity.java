package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
@Table(name = "players")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerEntity extends AbstractPersistable<Long> {
    @Column(columnDefinition = "text")
    private String                             name;
    private String                             discordNickName;
    private String                             swgohAllyCode;
    @OneToMany(mappedBy = "player")
    @ToString.Exclude
    private Set<PlayerDisciplineHistoryEntity> playerDisciplineHistory;
}