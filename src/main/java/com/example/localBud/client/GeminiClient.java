package com.example.localBud.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "ai.enabled", havingValue = "true", matchIfMissing = true)
public class GeminiClient implements AiClient {

    private static final String PROJECT_ID = "carbide-datum-480501-h9";
    private static final String LOCATION = "us-central1";
    private static final String MODEL_ID = "gemini-2.5-flash";

    private volatile Client client;

    private Client client() {
        if (client == null) {
            synchronized (this) {
                if (client == null) {
                    client = Client.builder()
                            .vertexAI(true)
                            .project(PROJECT_ID)
                            .location(LOCATION)
                            .build();
                }
            }
        }
        return client;
    }

    @Override
    public String ask(String prompt) {
        try {
            GenerateContentResponse response =
                    client().models.generateContent(MODEL_ID, prompt, null);

            String text = response.text();
            return (text == null || text.trim().isEmpty())
                    ? "AI did not return a response."
                    : text;

        } catch (Exception e) {
            System.err.println("Gemini API error: " + e.getMessage());
            return "Виникла помилка під час обробки AI-запиту.";
        }
    }
}