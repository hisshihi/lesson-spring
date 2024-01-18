package com.lessonSpring.quickstar.domain.entities;


import jakarta.persistence.*;
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


// Преобразовываем этот объект в сущность, чтобы использовать в jpa and hibernate

// С помощью первой аннотации помечаем объект как сущность
@Entity

// Указываем с какой таблицей сопоставляется эта сущность
@Table(name = "authors")
public class AuthorEntity {


    //    Указываем id и автоикремент, каждый раз, как будет создаваться новый автор его id будет увеличиватся на единицу
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long id;

    private String name;

    private Integer age;

}
