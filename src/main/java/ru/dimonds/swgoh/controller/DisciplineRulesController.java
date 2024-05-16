package ru.dimonds.swgoh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.service.DisciplineRuleService;

@Controller
@RequestMapping("/rules")
public class DisciplineRulesController {

    @Autowired
    private DisciplineRuleService ruleService;

    @GetMapping
    private String getAll(
            Model model,
            @RequestParam(name = "sort", defaultValue = "disciplinePoints,asc") String[] sort
    )
    {
        String sortField     = sort[0];
        String sortDirection = sort[1];

        Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort.Order     order     = new Sort.Order(direction, sortField);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("desc") ? "asc" : "desc");
        model.addAttribute("rules", ruleService.getAll(Sort.by(order)));
        model.addAttribute("rule", DisciplineRuleDto.builder().build());
        return "rules/rules.html";
    }

    @PostMapping
    private String addRule(
            Model model,
            @RequestParam(name = "sort", defaultValue = "disciplinePoints,asc") String[] sort,
            @ModelAttribute("rule") DisciplineRuleDto ruleDto
    )
    {
        String sortField     = sort[0];
        String sortDirection = sort[1];

        Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort.Order     order     = new Sort.Order(direction, sortField);

        ruleDto.setGuild(1L);

        ruleService.save(ruleDto);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("desc") ? "asc" : "desc");
        model.addAttribute("rules", ruleService.getAll(Sort.by(order)));
        model.addAttribute("rule", DisciplineRuleDto.builder().build());
        return "rules/rules.html";
    }
}
