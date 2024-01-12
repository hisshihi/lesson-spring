package com.lessonSpring.quickstar.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* * Аннотация @Data генерирует методы getters и setters для всех полей класса, а также методы toString(), equals() и hashCode().
 * Это упрощает написание кода, поскольку не нужно вручную создавать эти методы.
* */
@Data

/*
* * Аннотация @AllArgsConstructor генерирует конструктор с параметрами для всех полей класса.
 * Это упрощает создание экземпляров класса, поскольку не нужно вручную создавать конструктор.
* */
@AllArgsConstructor

/*
* * Аннотация @NoArgsConstructor генерирует конструктор без параметров для класса.
 * Это необходимо для того, чтобы Spring мог создать экземпляр класса без параметров.
* */
@NoArgsConstructor

/*
* * Аннотация @Builder генерирует паттерн проектирования "строитель" для класса.
 * Это позволяет создавать экземпляры класса по частям, что может быть полезно в некоторых случаях.
* */
@Builder
public class Author {

    private Long id;

    private String name;

    private Integer age;

}
