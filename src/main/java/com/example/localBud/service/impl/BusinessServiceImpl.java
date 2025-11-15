package com.example.localBud.service.impl;

import com.example.localBud.repository.BusinessRepository;
import com.example.localBud.dto.RegisterBusinessRequest;
import com.example.localBud.dto.RegisterBusinessResponse;
import com.example.localBud.mapper.BusinessMapper;
import com.example.localBud.service.BusinessService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    public BusinessServiceImpl(BusinessRepository businessRepository, BusinessMapper businessMapper) {
        this.businessRepository = businessRepository;
        this.businessMapper = businessMapper;
    }

    @Override
    @Transactional
    public RegisterBusinessResponse createBusiness(RegisterBusinessRequest registerBusinessRequest) {
        var entity = businessMapper.toEntity(registerBusinessRequest);
        var businessEntity = businessRepository.save(entity);
        return businessMapper.toDto(businessEntity);
    }
}
