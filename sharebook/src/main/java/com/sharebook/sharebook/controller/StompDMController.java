package com.sharebook.sharebook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Member;
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

	/*
	 * 이거... 나중에 DM방 최초 생성 시 책 정보? 같은 거 보낼 때 사용하면 좋을 것같아서 주석처리 해놔용 // Client가 SEND할
	 * 수 있는 경로 // stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping
	 * 경로가 병합됨 // "/pub/chat/enter"
	 * 
	 * @MessageMapping(value = "/chat/enter") public void enter(Message message) {
	 * message.setContent(message.getMember().getNickname() + "님이 채팅방에 참여하였습니다.");
	 * template.convertAndSend("/sub/chat/room/" +
	 * message.getChatRoom().getChat_room_id(), message); } 
	 */

	@MessageMapping(value = "/book/chat/message.do")
	public void message(Message message) {
		chat_roomService.saveMessage(message);
		System.out.println(message.getMember().getNickname() + ": " + message.getContent());
		template.convertAndSend("/topic/book/chat/room/" + message.getChatRoom().getChat_room_id() + ".do", message);
	}
	
	@RequestMapping("/book/chat/member.do")
	public Member getMemberForDM(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		return userSession.getMember();
	}
}