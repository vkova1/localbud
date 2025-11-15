package com.example.localBud.mapper;

import com.example.localBud.dto.RegisterBusinessRequest;
import com.example.localBud.dto.RegisterBusinessResponse;
import com.example.localBud.entity.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    Business toEntity(RegisterBusinessRequest registerBusinessRequest);


    RegisterBusinessResponse toDto(Business business);
}
