package com.sharebook.sharebook.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Membership;
import com.sharebook.sharebook.domain.Message;
import com.sharebook.sharebook.service.Chat_roomService;
import com.sharebook.sharebook.service.MemberService;

class SortBySentTime implements Comparator<Message> {
	// Message를 송신 시간 별로 정렬
    @Override
    public int compare(Message m1, Message m2) {
        return m1.getSent_time().compareTo(m2.getSent_time());
    }
}

@Controller
public class DMController {
	@Autowired
	public Chat_roomService chat_roomService;
	@Autowired
	public MemberService memberService;
	
	/*
	 *  DM 페이지로 이동
	 */
	@RequestMapping(value="/book/dm/{memberId}.do", method=RequestMethod.GET)
	public ModelAndView viewDM(@PathVariable int memberId) {
		Member member = memberService.getMember(memberId);
		List<Membership> membershipList = chat_roomService.findMembershipListByMember(member);
		
		
		List<Member> otherMemberList = new ArrayList<>();
		for(int i = 0; i < membershipList.size(); i++) {
			List<Membership> chat_room = chat_roomService.findMembershipListByChatRoom(membershipList.get(i).getChatRoom());
			for(int j = 0; j < 2; j++) {
				if(chat_room.get(j).getMember().getMember_id() != memberId) {
					otherMemberList.add(chat_room.get(j).getMember());
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/chatRoom");
		mav.addObject("otherMemberList", otherMemberList);
		return mav;
	}
	
	
	@RequestMapping("/book/dm/{memberId}/{otherMemberId}.do")
	public ModelAndView viewDetailDM(@PathVariable int memberId, @PathVariable int otherMemberId, RedirectAttributes redirectAttr) {
		Member member = memberService.getMember(memberId);
		Member otherMember = memberService.getMember(otherMemberId);
		
		List<Message> totalMessageList = new ArrayList<>();
		totalMessageList.addAll(chat_roomService.findMessageListByMember(member));
		totalMessageList.addAll(chat_roomService.findMessageListByMember(otherMember));
		
		Collections.sort(totalMessageList, new SortBySentTime());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book/dm/" + memberId +".do");
		redirectAttr.addFlashAttribute("messageList", totalMessageList);
		return mav;
	}
	
	@RequestMapping("/book/dm/{memberId}/{otherMemberId}/send.do")
	public ModelAndView sendDM(@PathVariable int memberId, @PathVariable int otherMemberId, String content) {
		Member member = memberService.getMember(memberId);
		Membership membership = chat_roomService.findMembershipByMember(member);
		
		LocalDate now = LocalDate.now();
		Date sentTime = java.sql.Date.valueOf(now);
		
		Message message = new Message(0, sentTime, content, member, membership.getChatRoom());
		chat_roomService.saveMessage(message);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book/dm/" + memberId + "/" + otherMemberId + ".do");
		return mav;
	}
	
}
