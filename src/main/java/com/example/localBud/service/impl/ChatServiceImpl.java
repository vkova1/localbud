package com.example.localBud.service.impl;

import com.example.localBud.client.GeminiClient;
import com.example.localBud.dto.ChatRequest;
import com.example.localBud.dto.ChatResponse;
import com.example.localBud.entity.BusinessContext;
import com.example.localBud.repository.BusinessContextRepository;
import com.example.localBud.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final BusinessContextRepository businessContextRepository;
    private final GeminiClient geminiClient;

    public ChatServiceImpl(BusinessContextRepository businessContextRepository, GeminiClient geminiClient) {
        this.businessContextRepository = businessContextRepository;
        this.geminiClient = geminiClient;
    }

    public ChatResponse processUserQuery(ChatRequest chatRequest) {
        var businessContext = businessContextRepository.findByBusinessId(chatRequest.businessId())
                .map(BusinessContext::getText)
                .orElseThrow();

        var prompt = """
                You are a helpful AI assistant for a local business.
                Use the following business context to answer the question accurately.

                Context:
                %s

                Question:
                %s
                """.formatted(businessContext, chatRequest.userMessage());

        var answer = geminiClient.ask(prompt);

        return ChatResponse.builder()
                .response(answer)
                .build();

    }


}
