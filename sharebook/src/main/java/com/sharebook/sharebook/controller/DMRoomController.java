package com.sharebook.sharebook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Membership;
import com.sharebook.sharebook.service.Chat_roomService;
import com.sharebook.sharebook.service.MemberService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class DMRoomController {
	@Autowired
    private final Chat_roomService chat_roomService;
	@Autowired
	private final MemberService memberService;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(HttpServletRequest request){
    	//UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
		
		/*
    	if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			mav.setViewName("redirect:/book/dm/" + member.getMember_id() + ".do");
		}
		*/
    	
		int memberId = 1; // 임시
    	Member member = memberService.getMember(memberId);
		List<Membership> membershipList = chat_roomService.findMembershipListByMember(member);

		List<Membership> otherMembershipList = new ArrayList<>();
		for (int i = 0; i < membershipList.size(); i++) {
			List<Membership> chat_room = chat_roomService
					.findMembershipListByChatRoom(membershipList.get(i).getChatRoom());
			for (int j = 0; j < 2; j++) {
				if (chat_room.get(j).getMember().getMember_id() != memberId) {
					otherMembershipList.add(chat_room.get(j));
				}
			}
		}

		mav.setViewName("thymeleaf/testChatRoomList");
		mav.addObject("member", member);
		mav.addObject("membershipList", otherMembershipList);
		return mav;
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(int roomId, Model model){
        log.info("# get Chat Room, roomID : " + roomId);
        model.addAttribute("thymeleaf/testChatRoom", chat_roomService.findChatRoomById(roomId));
    }
}