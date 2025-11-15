package com.example.localBud.api;

import com.example.localBud.dto.BusinessContextResponse;
import com.example.localBud.dto.CreateBusinessContextRequest;
import com.example.localBud.dto.RegisterBusinessRequest;
import com.example.localBud.dto.RegisterBusinessResponse;
import com.example.localBud.service.BusinessContextService;
import com.example.localBud.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final BusinessService businessService;
    private final BusinessContextService businessContextService;

    public BusinessController(BusinessService businessService, BusinessContextService businessContextService) {
        this.businessService = businessService;
        this.businessContextService = businessContextService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RegisterBusinessResponse> createBusiness(@RequestBody RegisterBusinessRequest registerBusinessRequest) {
        var response = businessService.createBusiness(registerBusinessRequest);
        log.info("The new business with name {} has been created", response.name());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{businessId}/context")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<BusinessContextResponse> createBusinessContext(@RequestBody CreateBusinessContextRequest createBusinessContextRequest, @PathVariable Long businessId) {
        var response = businessContextService.createBusinessContext(createBusinessContextRequest, businessId);
        log.info("The business context with id {} has been created", businessId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{businessId}/context")
    public ResponseEntity<BusinessContextResponse> getBusinessContext(@PathVariable Long businessId) {
        var response = businessContextService.getBusinessContext(businessId);
        return ResponseEntity.ok(response);
    }

}
