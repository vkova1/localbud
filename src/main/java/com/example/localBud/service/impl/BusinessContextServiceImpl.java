package com.example.localBud.service.impl;

import com.example.localBud.dto.BusinessContextResponse;
import com.example.localBud.dto.CreateBusinessContextRequest;
import com.example.localBud.mapper.BusinessContextMapper;
import com.example.localBud.repository.BusinessContextRepository;
import com.example.localBud.repository.BusinessRepository;
import com.example.localBud.service.BusinessContextService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BusinessContextServiceImpl implements BusinessContextService {

    private final BusinessContextRepository businessContextRepository;
    private final BusinessContextMapper businessContextMapper;

    public BusinessContextServiceImpl(BusinessContextRepository businessContextRepository, BusinessRepository businessRepository, BusinessContextMapper businessContextMapper) {
        this.businessContextRepository = businessContextRepository;
        this.businessContextMapper = businessContextMapper;
    }

    @Override
    @Transactional
    public BusinessContextResponse createBusinessContext(CreateBusinessContextRequest createBusinessContextRequest, Long businessId) {
        var entity = businessContextMapper.toEntity(createBusinessContextRequest, businessId);
        var businessContextEntity = businessContextRepository.save(entity);
        return businessContextMapper.toResponse(businessContextEntity);
    }

    @Override
    @Transactional
    public BusinessContextResponse getBusinessContext(Long businessId) {
        var entity = businessContextRepository.findByBusinessId(businessId).orElseThrow();
        return businessContextMapper.toResponse(entity);
    }
}
