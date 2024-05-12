package ru.dimonds.swgoh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.dimonds.swgoh.model.request.SignUpRequest;
import ru.dimonds.swgoh.model.response.StandardResponse;
import ru.dimonds.swgoh.service.SignUpService;

@RestController
@RequestMapping("/api/signUp")
public class SignUpController {

    @Autowired
    private SignUpService service;

    @PostMapping
    public Mono<StandardResponse> signUp(
            @RequestBody SignUpRequest signUpRequest
    )
    {
        return Mono.create(
                sink -> {
                    try {
                        service.signUp(signUpRequest);
                        sink.success(
                                StandardResponse.builder()
                                                .success(true)
                                                .build()
                        );
                    } catch (Exception e) {
                        sink.error(e);
                    }
                }
        );
    }
}
