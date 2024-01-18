// Создаём слой представления между слоем сохранения и слоем бизнес-логики
package com.lessonSpring.quickstar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Обязательно нужно создавать конструктор и геттеры и сеттеры, так как они нужны для jackson
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    private Long id;

    private String name;

    private Integer age;

}
