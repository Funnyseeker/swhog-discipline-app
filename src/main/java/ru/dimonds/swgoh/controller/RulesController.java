package ru.dimonds.swgoh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dimonds.swgoh.service.DisciplineRuleService;

@Controller
@RequestMapping("/rules")
public class RulesController {

    @Autowired
    private DisciplineRuleService ruleService;

    @GetMapping
    private String getAll(Model model) {
        model.addAttribute("rules", ruleService.getAll());
        return "rules/rules.html";
    }
}
