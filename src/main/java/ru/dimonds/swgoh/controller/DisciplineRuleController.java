package ru.dimonds.swgoh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;
import ru.dimonds.swgoh.service.DisciplineRuleService;

@Controller
@RequestMapping("/rules")
@Slf4j
public class DisciplineRuleController {

    @Autowired
    private DisciplineRuleService service;

//    @GetMapping("/index")
//    public Mono<String> getAll(Model model) {
//        return Mono.create(
//                sink -> {
//                    Long guildId = 1L;
//                    try {
//                        List<DisciplineRuleDto> data = service.getAll()
//                                                              .stream()
//                                                              .filter(
//                                                                      rule -> rule.getGuild()
//                                                                                  .equals(guildId)
//                                                              )
//                                                              .toList();
//                        model.addAttribute("rules", data);
//                        sink.success("index");
//                    } catch (Exception e) {
//                        log.error("Cannot read discipline rules:", e);
//                        sink.error(e);
//
//                    }
//                }
//        );
//    }

//    @PutMapping("/{id}")
//    public Mono<StandardResponse> update(@PathVariable("id") Long id) {
//        return Mono.create(
//                sink -> sink.success(StandardResponse.builder().build())
//        );
//    }

    @PostMapping
    public String create(DisciplineRuleDto rule, BindingResult result, Model model) {
        return "index";
//        return Mono.create(
//                sink -> {
//                    if (result.hasErrors()) {
//                        sink.success("add-rule");
//                    } else {
//                        sink.success("redirect:/homeNotSignedIn.html");
//                    }
//                }
//        );
    }

//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//
//        userRepository.save(user);
//        return "redirect:/index";
//    }
}
