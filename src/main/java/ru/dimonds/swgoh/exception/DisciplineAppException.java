package ru.dimonds.swgoh.exception;

public class DisciplineAppException extends Exception {

    public DisciplineAppException(String message) {
        super(message);
    }

    public DisciplineAppException(Exception e) {
        super(e);
    }
}
