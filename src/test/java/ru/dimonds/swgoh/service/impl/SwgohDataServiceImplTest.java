package ru.dimonds.swgoh.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
class SwgohDataServiceImplTest {

    @Test
    void testParser() throws IOException {
        List<String> playerNamesBySwgohGGGuildUrl = new SwgohDataServiceImpl()
                .getPlayerNamesBySwgohGGGuildUrl("https://swgoh.gg/g/Q3315GJLR-WVNcQ6Vk3rIg/");
        Assertions.assertTrue(
                playerNamesBySwgohGGGuildUrl
                        .size()
                <= 50
        );
        playerNamesBySwgohGGGuildUrl.forEach(log::info);
    }
}