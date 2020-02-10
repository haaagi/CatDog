package com.catdog.springboot.interceptor;

import com.catdog.springboot.service.ChatService;
import com.catdog.springboot.web.dto.ChatMessage;
import com.catdog.springboot.web.dto.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}" , payload);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        System.out.println(chatMessage.getRoomId() + " " + " !!!!");
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }
}
