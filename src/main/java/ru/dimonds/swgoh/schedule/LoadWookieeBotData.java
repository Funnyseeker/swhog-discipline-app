package ru.dimonds.swgoh.schedule;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.service.DisciplineRuleService;
import ru.dimonds.swgoh.service.PlayerService;
import ru.dimonds.swgoh.service.impl.PlayerDisciplineHistoryService;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

//@Component
@Slf4j
public class LoadWookieeBotData {

    @Value("classpath:data/wookie-data.csv")
    private       Resource                       resourceFile;
    @Autowired
    private       PlayerService                  playerService;
    @Autowired
    private       PlayerDisciplineHistoryService disciplineHistoryService;
    @Autowired
    private       DisciplineRuleService          disciplineRuleService;
    private final OffsetDateTime                 importDate = OffsetDateTime.parse("2024-03-06T00:00:00+00:00");

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void loadWookieData() {
        try (CSVReader csvReader = new CSVReader(new FileReader(resourceFile.getFile()))) {
            String[]                values;
            List<DisciplineRuleDto> rules = disciplineRuleService.getRulesByBot("wookiee");
            while ((values = csvReader.readNext()) != null) {
                if (values.length > 4) {
                    Optional<PlayerDto> player = playerService.findByName(values[0]);
                    try {
                        BigDecimal diffPercent = new BigDecimal(Double.parseDouble(values[5]));
                        player.ifPresent(
                                playerDto -> {
                                    rules.stream()
                                         .filter(rule -> BigDecimal.valueOf(rule.getRuleValues().getMin())
                                                                   .compareTo(diffPercent) >= 0 &&
                                                         BigDecimal.valueOf(rule.getRuleValues().getMax())
                                                                   .compareTo(diffPercent) < 0
                                         )
                                         .findFirst()
                                         .ifPresent(
                                                 ruleDto -> disciplineHistoryService.save(
                                                         PlayerDisciplineHistoryDto.builder()
                                                                                   .player(playerDto.getId())
                                                                                   .disciplinePoints(ruleDto.getDisciplinePoints())
                                                                                   .date(importDate)
                                                                                   .build()
                                                 )
                                         );

                                }
                        );
                    } catch (Exception e) {
                        log.error("Failed to import wookiee row:", e);
                    }
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
