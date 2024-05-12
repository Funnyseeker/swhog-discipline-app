package ru.dimonds.swgoh.service;

import ru.dimonds.swgoh.exception.DisciplineAppException;
import ru.dimonds.swgoh.model.request.SignUpRequest;

public interface SignUpService {
    void signUp(SignUpRequest request) throws DisciplineAppException;
}
