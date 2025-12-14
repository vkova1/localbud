package com.example.localBud.client;

import com.google.cloud.aiplatform.v1.*;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient {

    // Секретний ключ буде автоматично підтягнутий зі змінних середовища або application.properties
    // @Value("${gemini.project.id}")
    private static final String PROJECT_ID = "carbide-datum-480501-h9"; // Введіть ваш ID проєкту
    private static final String MODEL_ID = "gemini-2.5-flash";

    public String ask(String prompt) {
        var modelName = ModelName.of(PROJECT_ID, "us-central1", MODEL_ID);

        var content = Content.newBuilder()
                .addParts(Part.newBuilder().setText(prompt).build())
                .setRole("user")
                .build();

        var request = GenerateContentRequest.newBuilder()
                .setModel(modelName.toString())
                .addContents(content)
                .build();

        try (PredictionServiceClient client = PredictionServiceClient.create()) {
            GenerateContentResponse response = client.generateContent(request);

            return response.getCandidates(0).getContent().getParts(0).getText();

        } catch (Exception e) {
            //TODO
            System.err.println("Помилка під час виклику Gemini API: " + e.getMessage());
            // Важливо: обробка помилок тут має бути більш надійною
            return "Виникла помилка під час обробки запиту AI.";
        }
    }
}