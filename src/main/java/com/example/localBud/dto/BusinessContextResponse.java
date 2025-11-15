package com.example.localBud.dto;

import java.time.LocalDateTime;

public record BusinessContextResponse(
        Long id,
        Long businessId,
        String title,
        String category,
        String language,
        String text,
        String vector,
        Integer chunkIndex,
        String source,
        Boolean isActive,
        Integer version,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
