package ru.dimonds.swgoh.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dimonds.swgoh.model.dto.SignUpRequest;
import ru.dimonds.swgoh.service.SignUpService;
import ru.dimonds.swgoh.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private UserService   userService;

    @PostMapping("/signUp")
    public String signUp(
            @Valid SignUpRequest signUpRequest, BindingResult result, Model model
    )
    {
        try {
            signUpService.signUp(signUpRequest);
            return "home/homeSignedIn";
        } catch (Exception e) {
            return "users/sign-up";
        }
    }
}
