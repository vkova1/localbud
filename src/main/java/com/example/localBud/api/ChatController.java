package com.example.localBud.api;

import com.example.localBud.dto.ChatRequest;
import com.example.localBud.dto.ChatResponse;
import com.example.localBud.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3001")
@Slf4j
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/query")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        var response = chatService.processUserQuery(request);
        return ResponseEntity.ok(response);
    }
}
