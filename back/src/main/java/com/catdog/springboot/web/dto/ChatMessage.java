package com.catdog.springboot.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    enum MessageType {
       ENTER, TALK
    }

    private MessageType type; // 메세지 타입
    private String roomId;    // 방 번호
    private String sender;    // 메세지 보낸 사람
    private String message;   // 메세지
}
