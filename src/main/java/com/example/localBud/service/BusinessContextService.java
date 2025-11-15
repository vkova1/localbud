package com.example.localBud.service;

import com.example.localBud.dto.BusinessContextResponse;
import com.example.localBud.dto.CreateBusinessContextRequest;

public interface BusinessContextService {

    BusinessContextResponse createBusinessContext(CreateBusinessContextRequest createBusinessContextRequest, Long businessId);

    BusinessContextResponse getBusinessContext(Long businessId);
}
