package com.lessonSpring.quickstar.config;

import com.lessonSpring.quickstar.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {

    @Bean
    public Person createPerson() {
        Person person = new Person();
        person.setName("Hiss");
        person.setAge(21);
        return person;
    }

}
