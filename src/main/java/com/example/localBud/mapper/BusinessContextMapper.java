package com.example.localBud.mapper;

import com.example.localBud.dto.BusinessContextResponse;
import com.example.localBud.dto.CreateBusinessContextRequest;
import com.example.localBud.entity.BusinessContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessContextMapper {

    @Mapping(target = "business.id", source = "businessId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vector", ignore = true)
    @Mapping(target = "chunkIndex", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    BusinessContext toEntity(CreateBusinessContextRequest request, Long businessId);

    @Mapping(target = "businessId", source = "business.id")
    BusinessContextResponse toResponse(BusinessContext entity);
}
