package com.example.localBud.service;

import com.example.localBud.dto.ChatRequest;
import com.example.localBud.dto.ChatResponse;

public interface ChatService {

    ChatResponse processUserQuery(ChatRequest chatRequest);
}
