package com.lessonSpring.quickstar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private final Person person;

    public HelloWorldController(Person person) {
        this.person = person;
    }

    @GetMapping("/person")
    public Person getPerson() {
        return person;
    }

    @GetMapping("/person/name")
    public String getNamePerson() {
        return person.getName();
    }
}
