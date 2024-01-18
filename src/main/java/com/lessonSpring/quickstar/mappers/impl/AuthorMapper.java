package com.lessonSpring.quickstar.mappers.impl;

import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, com.lessonSpring.quickstar.domain.dto.AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public com.lessonSpring.quickstar.domain.dto.AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, com.lessonSpring.quickstar.domain.dto.AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(com.lessonSpring.quickstar.domain.dto.AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
