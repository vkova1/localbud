package com.example.localBud.repository;

import com.example.localBud.entity.BusinessContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessContextRepository extends JpaRepository<BusinessContext,Long> {

    Optional<BusinessContext> findByBusinessId(Long businessId);

}
