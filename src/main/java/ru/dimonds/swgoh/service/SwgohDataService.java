package ru.dimonds.swgoh.service;

import java.io.IOException;
import java.util.List;

public interface SwgohDataService {
    List<String> getPlayerNamesBySwgohGGGuildUrl(String url) throws IOException;
}
