package com.example.localBud.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OllamaClient {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:11434")
            .build();

    public String ask(String prompt) {
        StringBuilder fullResponse = new StringBuilder();

        webClient.post()
                .uri("/api/generate")
                .bodyValue(Map.of(
                        "model", "llama3.2:3b",
                        "prompt", prompt
                ))
                .retrieve()
                .bodyToFlux(String.class)
                .map(this::extractChunk)
                .doOnNext(fullResponse::append)
                .blockLast();

        return fullResponse.toString().trim();
    }

    private String extractChunk(String jsonLine) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonLine);
            if (node.has("response")) {
                return node.get("response").asText();
            }
        } catch (Exception ignored) {}
        return "";
    }



}
