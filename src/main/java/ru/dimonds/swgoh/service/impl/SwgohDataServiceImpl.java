package ru.dimonds.swgoh.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.service.SwgohDataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SwgohDataServiceImpl implements SwgohDataService {

    private final int columnsCnt = 6;

    @Override
    public List<String> getPlayerNamesBySwgohGGGuildUrl(String guildUrl) throws IOException {
        Document      doc         = Jsoup.connect(guildUrl).get();
        List<String>  playerNames = new ArrayList<>();
        AtomicInteger cnt         = new AtomicInteger(0);
        doc.select("td")
           .forEach(
                   element -> {
                       if (element.hasAttr("data-sort-value") && cnt.get() % columnsCnt == 0) {
                           playerNames.add(element.attribute("data-sort-value").getValue());
                       }
                       cnt.incrementAndGet();
                   }
           );
        return playerNames;
    }
}
