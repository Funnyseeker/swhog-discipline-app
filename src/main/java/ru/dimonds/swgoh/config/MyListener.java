package ru.dimonds.swgoh.config;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.model.mapper.DisciplineRuleMapper;
import ru.dimonds.swgoh.service.DisciplineRuleService;
import ru.dimonds.swgoh.service.PlayerService;
import ru.dimonds.swgoh.service.impl.PlayerDisciplineHistoryService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

//@Component
@Slf4j
public class MyListener extends ListenerAdapter {

    @Autowired
    private PlayerService                  playerService;
    @Autowired
    private PlayerDisciplineHistoryService disciplineHistoryService;
    @Autowired
    private DisciplineRuleService          disciplineRuleService;
    @Autowired
    private DisciplineRuleMapper           disciplineRuleMapper;

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        if (!event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        log.info("Title {}", message.getEmbeds().getFirst().getTitle());
        log.info("Description {}", message.getEmbeds().getFirst().getDescription());
        if (
                event.getAuthor().getName().equals("C3PO") &&
                !message.getEmbeds().isEmpty() &&
                message.getEmbeds().getFirst().getTitle() != null &&
                message.getEmbeds().getFirst().getDescription() != null
        )
        {
            List<DisciplineRuleDto> rules = disciplineRuleService.getRulesByBot("c3po");

            String[] content = message.getEmbeds().getFirst().getDescription().split("\n");
            log.info(message.getEmbeds().getFirst().getTitle());
            if (message.getEmbeds().getFirst().getTitle().contains("EtA FORGE OF DIAMONDS Daily Raid Tickets")) {
                for (String row : content) {
                    if (row.contains(":")) {
                        try {
                            String[]            values      = row.split(":");
                            Optional<PlayerDto> player      = playerService.findByName(values[1].trim());
                            Long                diffPercent = Long.parseLong(values[0].trim());
                            log.info("{} = {}", row, diffPercent);
                            player.ifPresent(
                                    playerDto -> {
                                        rules.stream()
                                             .filter(
                                                     rule -> disciplineRuleMapper.map(rule.getRuleValues())
                                                                                 .contains(diffPercent) ||
                                                             (
                                                                     rule.getRuleValues().getMin() != null &&
                                                                     rule.getRuleValues().getMin().equals(diffPercent)
                                                             )
                                             )
                                             .findFirst()
                                             .ifPresent(
                                                     ruleDto -> disciplineHistoryService.save(
                                                             PlayerDisciplineHistoryDto.builder()
                                                                                       .player(playerDto.getId())
                                                                                       .reason(ruleDto.getReason() +
                                                                                               "(bot)")
                                                                                       .disciplinePoints(ruleDto.getDisciplinePoints())
                                                                                       .date(OffsetDateTime.now())
                                                                                       .build()
                                                     )
                                             );

                                    }
                            );
                        } catch (Exception e) {
                            log.error("Tickets pars error", e);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        log.info("Title {}", message.getEmbeds().getFirst().getTitle());
        log.info("Description {}", message.getEmbeds().getFirst().getDescription());
        if (
                event.getAuthor().getName().equals("C3PO") &&
                !message.getEmbeds().isEmpty() &&
                message.getEmbeds().getFirst().getTitle() != null &&
                message.getEmbeds().getFirst().getDescription() != null
        )
        {
            List<DisciplineRuleDto> rules = disciplineRuleService.getRulesByBot("c3po");

            String[] content = message.getEmbeds().getFirst().getDescription().split("\n");
            log.info(message.getEmbeds().getFirst().getTitle());
            if (message.getEmbeds().getFirst().getTitle().contains("EtA FORGE OF DIAMONDS Daily Raid Tickets")) {
                for (String row : content) {
                    if (row.contains(":")) {
                        try {
                            String[]            values      = row.split(":");
                            Optional<PlayerDto> player      = playerService.findByName(values[1].trim());
                            Long                diffPercent = Long.parseLong(values[0].trim());
                            log.info("{} = {}", row, diffPercent);
                            player.ifPresent(
                                    playerDto -> {
                                        rules.stream()
                                             .filter(
                                                     rule -> disciplineRuleMapper.map(rule.getRuleValues())
                                                                                 .contains(diffPercent) ||
                                                             (
                                                                     rule.getRuleValues().getMin() != null &&
                                                                     rule.getRuleValues().getMin().equals(diffPercent)
                                                             )
                                             )
                                             .findFirst()
                                             .ifPresent(
                                                     ruleDto -> disciplineHistoryService.save(
                                                             PlayerDisciplineHistoryDto.builder()
                                                                                       .player(playerDto.getId())
                                                                                       .reason(ruleDto.getReason() +
                                                                                               "(bot)")
                                                                                       .disciplinePoints(ruleDto.getDisciplinePoints())
                                                                                       .date(OffsetDateTime.now())
                                                                                       .build()
                                                     )
                                             );

                                    }
                            );
                        } catch (Exception e) {
                            log.error("Tickets pars error", e);
                        }
                    }
                }
            }
        }
    }
}
