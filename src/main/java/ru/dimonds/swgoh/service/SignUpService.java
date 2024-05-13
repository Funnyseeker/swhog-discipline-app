package ru.dimonds.swgoh.service;

import ru.dimonds.swgoh.exception.DisciplineAppException;
import ru.dimonds.swgoh.model.dto.SignUpRequest;

public interface SignUpService {
    void signUp(SignUpRequest request) throws DisciplineAppException;
}
