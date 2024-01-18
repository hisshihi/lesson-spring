package com.lessonSpring.quickstar.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
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

// С помощью первой аннотации помечаем объект как сущность и его можно сохранить в бд
@Entity

// Игнорируем те значения, которые нам не известны и мы не ожидали, что они придут
@JsonIgnoreProperties(ignoreUnknown = true)

// Указываем с какой таблицей сопоставляется эта сущность
@Table(name = "books")
public class Book {

    @Id
    private String isbn;

    private String title;

//    Указываем, что это ссылка на автора, который написал книгу, используем связть многие к одному и указываем, что вместе с книгой будет вывзываться также и автор
//    И также указываем, с каким столбцом мы объеденям
//    Позволяет автоматически создать и перейти к автору
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

//    Для того, чтобы преобразовать к примеру год выпуска просто в год нужно использвать
//    @JsonProperty("year")

}
