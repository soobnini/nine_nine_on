package com.sharebook.sharebook.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Chat_room;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Membership;
import com.sharebook.sharebook.domain.Message;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.Chat_roomService;
import com.sharebook.sharebook.service.MemberService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class DMRoomController {
	@Autowired
	private final Chat_roomService chat_roomService;
	@Autowired
	private final MemberService memberService;
	@Autowired
	private final BookService bookService;
	
	// detail Book 대여문의 -> 채팅방 생성
	@GetMapping(value = "/book/chat/create.do")
	public ModelAndView createRoom(String otherMemberId, String bookId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우 
			mav.setViewName("login");
			return mav;
		}
		
		Member member = memberService.getMember(userSession.getMember().getMember_id());
		Member otherMember = memberService.getMember(Integer.parseInt(otherMemberId));
		
		Book book = bookService.findBookById(Integer.parseInt(bookId));
		
		Chat_room room = new Chat_room(0, book);
		int roomId = chat_roomService.saveChatRoom(room).getChat_room_id();
		
		Membership myMembership = new Membership(room, member);
		Membership otherMembership = new Membership(room, otherMember);
		chat_roomService.saveMembership(myMembership);
		chat_roomService.saveMembership(otherMembership);

		mav.setViewName("redirect:/book/chat/room/" + roomId + ".do");
		return mav;
	}

	// 채팅방 목록 조회
	@GetMapping(value = "/book/chat/rooms.do")
	public ModelAndView rooms(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우 
			mav.setViewName("login");
			return mav;
		}
		
		Member member = memberService.getMember(userSession.getMember().getMember_id());
		List<Membership> membershipList = chat_roomService.findMembershipList(member);

		mav.setViewName("thymeleaf/DMRoomList");
		mav.addObject("member", member);
		mav.addObject("membershipList", membershipList);
		return mav;
	}

	// 채팅방 접속
	@GetMapping("/book/chat/room/{roomId}.do")
	public ModelAndView getRoom(@PathVariable int roomId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
			return mav;
		}
		
		Member member = memberService.getMember(userSession.getMember().getMember_id());

		Chat_room room = chat_roomService.findChatRoomById(roomId);
		Membership otherMembership = chat_roomService.findOtherParticipation(member, room);
		Member otherMember = otherMembership.getMember();

		List<Message> messageList = chat_roomService.findMessageListByChatRoom(room);

		mav.setViewName("thymeleaf/DMRoom");
		mav.addObject("member", member);
		mav.addObject("otherMember", otherMember);
		mav.addObject("roomId", roomId);
		mav.addObject("room", room);
		mav.addObject("messageList", messageList);

		return mav;
	}
}