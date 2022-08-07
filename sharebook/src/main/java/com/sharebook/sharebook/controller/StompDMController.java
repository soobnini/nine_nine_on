package com.sharebook.sharebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.sharebook.sharebook.domain.Message;
import com.sharebook.sharebook.service.Chat_roomService;
import com.sharebook.sharebook.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompDMController {
	@Autowired
	public Chat_roomService chat_roomService;
	@Autowired
	public MemberService memberService;

	private final SimpMessagingTemplate template; // 특정 Broker로 메세지를 전달

	// Client가 SEND할 수 있는 경로
	// stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
	// "/pub/chat/enter"
	@MessageMapping(value = "/chat/enter")
	public void enter(Message message) {
		message.setContent(message.getMember().getNickname() + "님이 채팅방에 참여하였습니다.");
		template.convertAndSend("/sub/chat/room/" + message.getChatRoom().getChat_room_id(), message);
	}

	@MessageMapping(value = "/chat/message")
	public void message(Message message) {
		template.convertAndSend("/sub/chat/room/" + message.getChatRoom().getChat_room_id(), message);
	}
}