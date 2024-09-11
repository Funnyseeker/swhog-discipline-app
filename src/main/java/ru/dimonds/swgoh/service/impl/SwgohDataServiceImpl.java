package ru.dimonds.swgoh.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.service.SwgohDataService;

import java.io.IOException;
import java.util.List;

@Service
public class SwgohDataServiceImpl implements SwgohDataService {

    @Override
    public List<String> getPlayerNamesBySwgohGGGuildUrl(String guildUrl) throws IOException {
        Document doc = Jsoup.connect(guildUrl).get();
        return doc.select("tbody").select("div[class=\"fw-bold text-white\"]")
                  .stream()
                  .map(element -> element.childNodes().get(0).toString().replace("\n", ""))
                  .toList();
    }
}
