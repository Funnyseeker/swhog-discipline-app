package ru.dimonds.swgoh.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.service.PlayerService;
import ru.dimonds.swgoh.service.impl.PlayerDisciplineHistoryService;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LoadWookieeBotData {

    @Value("classpath:data/wookie-data.csv")
    private Resource                       resourceFile;
    @Autowired
    private PlayerService                  playerService;
    @Autowired
    private PlayerDisciplineHistoryService disciplineHistoryService;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void loadWookieData() {
        try (CSVReader csvReader = new CSVReader(new FileReader(resourceFile.getFile()))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                Optional<PlayerDto> player = playerService.findByName(values[0]);
                player.ifPresent(
                        playerDto -> {
                            disciplineHistoryService.save(
                                    PlayerDisciplineHistoryDto.builder()
                                                              .player(playerDto.getId())
                                                              .build()
                            );
                        }
                );
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
