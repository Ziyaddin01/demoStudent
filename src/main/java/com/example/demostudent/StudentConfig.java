package com.example.demostudent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student ziya = new Student(
                    1L,
                    "Ziya",
                    "ziya.hsnv@mail.ru",
                    LocalDate.of(2001, DECEMBER,1)
            );

            Student alex = new Student(
                    2L,
                    "Alex",
                    "alex.sd@mail.ru",
                    LocalDate.of(2002, DECEMBER,2)
            );
            repository.saveAll(
                    List.of(ziya, alex)
            );
        };
    }
}
