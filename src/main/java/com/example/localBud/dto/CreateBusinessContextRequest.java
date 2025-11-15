package com.example.localBud.dto;

public record CreateBusinessContextRequest(
        String title,
        String category,
        String language,
        String text,
        String source
) {}
