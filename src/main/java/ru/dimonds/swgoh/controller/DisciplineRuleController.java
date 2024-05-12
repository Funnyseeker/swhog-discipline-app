package ru.dimonds.swgoh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.model.response.ReadResponse;
import ru.dimonds.swgoh.model.response.StandardResponse;
import ru.dimonds.swgoh.service.DisciplineRuleService;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@Slf4j
public class DisciplineRuleController {

    @Autowired
    private DisciplineRuleService service;

    @GetMapping
    public Mono<ReadResponse<DisciplineRuleDto>> getAll() {
        return Mono.create(
                sink -> {
                    Long guildId = 1L;
                    try {
                        List<DisciplineRuleDto> data = service.getAll()
                                                              .stream()
                                                              .filter(
                                                                      rule -> rule.getGuild()
                                                                                  .equals(guildId)
                                                              )
                                                              .toList();
                        sink.success(ReadResponse.<DisciplineRuleDto>builder().data(data).build());
                    } catch (Exception e) {
                        log.error("Cannot read discipline rules:", e);
                        sink.error(e);

                    }
                }
        );
    }

    @PutMapping("/{id}")
    public Mono<StandardResponse> update(@PathVariable("id") Long id) {
        return Mono.create(
                sink -> sink.success(StandardResponse.builder().build())
        );
    }

    @PostMapping
    public Mono<StandardResponse> create() {
        return Mono.create(
                sink -> sink.success(StandardResponse.builder().build())
        );
    }
}
