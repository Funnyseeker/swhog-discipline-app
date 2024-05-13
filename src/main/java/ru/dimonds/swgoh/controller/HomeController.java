package ru.dimonds.swgoh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dimonds.swgoh.model.dto.SignUpRequest;
import ru.dimonds.swgoh.model.dto.UserDto;

import java.security.Principal;

@Controller
@Slf4j
public class HomeController {

    @GetMapping
    public String index(/*Principal principal*/) {
//        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
        return "home/homeNotSignedIn";
    }

    @GetMapping("/signUp")
    public String addRule(Model model) {
        model.addAttribute("signUpRequest", new SignUpRequest());
        return "users/sign-up";
    }
}
