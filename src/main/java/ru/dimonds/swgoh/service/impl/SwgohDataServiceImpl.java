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

    @Override
    public List<String> getPlayerNamesBySwgohGGGuildUrl(String guildUrl) throws IOException {
        Document      doc         = Jsoup.connect(guildUrl).get();
        List<String>  playerNames = new ArrayList<>();
        AtomicInteger cnt         = new AtomicInteger(0);
        doc.select("td")
           .forEach(
                   element -> {
                       element.select("div")
                              .forEach(
                                      div -> {
                                          if (
                                                  div.attribute("class") != null &&
                                                  div.attribute("class")
                                                     .getValue().equals("fw-bold text-white")
                                          )
                                          {
                                              String val = div.childNodes()
                                                              .get(0)
                                                              .toString()
                                                              .replace("\n", "");
                                              if (val != null && !val.isBlank()) {
                                                  playerNames.add(val);
                                              }
                                          }
                                      }
                              );
                   }
           );
        return playerNames;
    }
}
