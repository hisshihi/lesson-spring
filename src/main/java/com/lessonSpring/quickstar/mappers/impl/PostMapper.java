package com.lessonSpring.quickstar.mappers.impl;

import com.lessonSpring.quickstar.domain.dto.PostDto;
import com.lessonSpring.quickstar.domain.entities.PostEntity;
import com.lessonSpring.quickstar.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements Mapper<PostEntity, PostDto> {

    private ModelMapper modelMapper;

    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto mapTo(PostEntity postEntity) {
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostEntity mapFrom(PostDto postDto) {
        return modelMapper.map(postDto, PostEntity.class);
    }
}
