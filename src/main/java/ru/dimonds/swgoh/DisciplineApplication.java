package ru.dimonds.swgoh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DisciplineApplication {
    private static final Logger log = LoggerFactory.getLogger(DisciplineApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DisciplineApplication.class, args);
    }
}


