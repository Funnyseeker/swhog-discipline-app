package ru.dimonds.swgoh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;
import ru.dimonds.swgoh.model.dto.SearchDatesDto;
import ru.dimonds.swgoh.service.PlayerService;

@Controller
@RequestMapping("/players")
public class PlayersController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    private String getAll(Model model, @RequestParam(name = "sort", defaultValue = "name,asc") String[] sort) {

        String sortField     = sort[0];
        String sortDirection = sort[1];

        Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort.Order     order     = new Sort.Order(direction, sortField);

        Object         searchDatesAttr = model.getAttribute("searchDates");
        SearchDatesDto searchDatesDto;
        if (searchDatesAttr != null) {
            searchDatesDto = (SearchDatesDto) searchDatesAttr;
        } else {
            searchDatesDto = SearchDatesDto.builder().build();
        }

        model.addAttribute(
                "players",
                playerService.getAll(Sort.by(order))
        );
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("desc") ? "asc" : "desc");
        model.addAttribute("searchDates", searchDatesDto);
        model.addAttribute("historyData", PlayerDisciplineHistoryDto.builder().build());
        return "players/players.html";
    }

    @PostMapping("/search")
    private String getAllWithSearch(
            Model model,
            @RequestParam(name = "sort", defaultValue = "name,asc") String[] sort,
            @ModelAttribute("searchDates") SearchDatesDto searchDatesDto
    )
    {

        String sortField     = sort[0];
        String sortDirection = sort[1];

        Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort.Order     order     = new Sort.Order(direction, sortField);

        model.addAttribute(
                "players",
                playerService.getAll(searchDatesDto, Sort.by(order))
        );
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("desc") ? "asc" : "desc");
        model.addAttribute("searchDates", searchDatesDto);
        model.addAttribute("historyData", PlayerDisciplineHistoryDto.builder().build());
        return "players/players.html";
    }

    @GetMapping("/{id}/addpoints")
    private String addPoints(
            Model model,
            @PathVariable("id") Long playerId,
            @ModelAttribute("searchDates") SearchDatesDto searchDatesDto
    )
    {

        model.addAttribute(
                "players",
                playerService.getAll()
        );
        model.addAttribute("sortField", "name");
        model.addAttribute("sortDirection", "asc");
        model.addAttribute("reverseSortDirection", "desc");
        model.addAttribute("searchDates", searchDatesDto);
        model.addAttribute("historyData", PlayerDisciplineHistoryDto.builder().build());
        return "players/players.html";
    }
}
