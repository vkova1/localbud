package com.example.localBud.service.impl;

import com.example.localBud.client.AiClient;
import com.example.localBud.dto.ChatRequest;
import com.example.localBud.dto.ChatResponse;
import com.example.localBud.entity.BusinessContext;
import com.example.localBud.repository.BusinessContextRepository;
import com.example.localBud.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final BusinessContextRepository businessContextRepository;
    private final AiClient aiClient;

    public ChatServiceImpl(BusinessContextRepository businessContextRepository, AiClient aiClient) {
        this.businessContextRepository = businessContextRepository;
        this.aiClient = aiClient;
    }

    public ChatResponse processUserQuery(ChatRequest chatRequest) {
        var businessContext = businessContextRepository.findByBusinessId(chatRequest.businessId())
                .map(BusinessContext::getText)
                .filter(text -> !text.isBlank())
                .orElse("No specific business details available yet.");

        var userMessage = chatRequest.userMessage();
        if (userMessage == null || userMessage.isBlank()) {
            throw new IllegalArgumentException("User message cannot be empty");
        }

        var prompt = """
                You are a friendly and helpful AI assistant for a local business.
                
                Tone of voice:
                - Be warm, polite, and customer-friendly.
                - Sound natural and conversational.
                - When information is missing, respond kindly and helpfully.
                
                Strict rules:
                - Answer ONLY using the Context below.
                - If the requested information is missing, say something friendly like:
                  "I don’t have this information yet, but I’ll be happy to help once it’s available."
                - Do NOT guess or invent details.
                - Return plain text only (no Markdown, no bullet points, no asterisks).
                
                Context:
                %s
                
                Question:
                %s
                """.formatted(businessContext, userMessage);

        System.out.println("DEBUG: Generated Prompt for Gemini API:");
        System.out.println(prompt);

        var answer = aiClient.ask(prompt);

        return ChatResponse.builder()
                .response(answer)
                .build();

    }

}
