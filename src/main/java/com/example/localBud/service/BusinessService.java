package com.example.localBud.service;

import com.example.localBud.dto.RegisterBusinessRequest;
import com.example.localBud.dto.RegisterBusinessResponse;

public interface BusinessService {

    RegisterBusinessResponse createBusiness(RegisterBusinessRequest registerBusinessRequest);

}
