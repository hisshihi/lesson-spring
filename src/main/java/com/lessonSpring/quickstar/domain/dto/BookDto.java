/*
* Создаём DTO Data Transfer Objects
* DTO используются в бизнес-логике приложения для передачи данных между компонентами приложения.
*  Они представляют собой объекты, которые содержат только те поля, которые необходимы для конкретной операции.
*  DTO могут быть созданы на основе сущностей, но они не обязательно должны быть идентичны сущностям.
* */


package com.lessonSpring.quickstar.domain.dto;

import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private String isbn;

    private String title;

    private AuthorEntity authorEntity;

}
