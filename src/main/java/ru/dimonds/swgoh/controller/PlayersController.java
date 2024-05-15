package ru.dimonds.swgoh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.service.PlayerService;

import java.util.Comparator;

@Controller
@RequestMapping("/players")
public class PlayersController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    private String getAll(Model model, @RequestParam(name = "sort", defaultValue = "id,asc") String[] sort) {

        String sortField     = sort[0];
        String sortDirection = sort[1];

        Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort.Order     order     = new Sort.Order(direction, sortField);

        model.addAttribute(
                "players",
                playerService.getAll(Sort.by(order))
                             .stream()
                             .sorted(Comparator.comparing(PlayerDto::getDisciplinePointsTotal))
                             .toList()
        );
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("desc") ? "asc" : "desc");
        return "players/players.html";
    }

    @
}
