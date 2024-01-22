package com.lessonSpring.quickstar.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        /*
        * Это означает, что ModelMapper будет использовать более гибкую стратегию сопоставления при сопоставлении объектов.
        *  В частности, ModelMapper будет пытаться сопоставить поля, даже если они имеют разные имена или типы.

        *  В общем, этот код создает и возвращает объект ModelMapper с настройкой MATCHING_STRATEGY на LOOSE.
        *  Это позволяет ModelMapper сопоставлять объекты более гибким образом.
        * */
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

}
