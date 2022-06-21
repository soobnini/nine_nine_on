package com.sharebook.sharebook.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Chat_room;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Membership;
import com.sharebook.sharebook.domain.Message;
import com.sharebook.sharebook.service.Chat_roomService;
import com.sharebook.sharebook.service.MemberService;
import com.sharebook.sharebook.service.BookService;

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
	@Autowired
	public BookService bookService;

	/*
	 * 책 상세 페이지 -> DM 페이지 최초 이동 시 책의 정보도 넘기기 위함
	 */
	@RequestMapping("/book/dm/start/{otherMemberId}/{bookId}.do")
	public ModelAndView viewDetailDM(@PathVariable int otherMemberId, @PathVariable int bookId,
			RedirectAttributes redirectAttr, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			Book book = bookService.findBookById(bookId);
			String bookInfo = "안녕하세요, " + book.getTitle() + " 대여 문의 드려요. :D";

			mav.setViewName("redirect:/book/dm/" + member.getMember_id() + "/" + otherMemberId + "/send.do");
			mav.addObject("content", bookInfo);
		}

		return mav;
	}

	/*
	 * DM 페이지로 이동
	 */
	@RequestMapping("/book/dm.do")
	public ModelAndView viewDMByHeader(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			mav.setViewName("redirect:/book/dm/" + member.getMember_id() + ".do");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/book/dm/{memberId}.do", method = RequestMethod.GET)
	public ModelAndView viewDM(@PathVariable int memberId) {
		Member member = memberService.getMember(memberId);
		List<Membership> membershipList = chat_roomService.findMembershipListByMember(member);

		List<Member> otherMemberList = new ArrayList<>();
		for (int i = 0; i < membershipList.size(); i++) {
			List<Membership> chat_room = chat_roomService
					.findMembershipListByChatRoom(membershipList.get(i).getChatRoom());
			for (int j = 0; j < 2; j++) {
				if (chat_room.get(j).getMember().getMember_id() != memberId) {
					otherMemberList.add(chat_room.get(j).getMember());
				}
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/chatRoom");
		mav.addObject("member", member);
		mav.addObject("otherMemberList", otherMemberList);
		return mav;
	}

	@RequestMapping("/book/dm/{memberId}/{otherMemberId}.do")
	public ModelAndView viewDetailDM(@PathVariable int memberId, @PathVariable int otherMemberId,
			RedirectAttributes redirectAttr) {
		ModelAndView mav = new ModelAndView();
		
		Member member = memberService.getMember(memberId);
		Member otherMember = memberService.getMember(otherMemberId);

		Chat_room chatRoom = findChatRoom(member, otherMember);

		List<Message> totalMessageList = new ArrayList<>();
		totalMessageList.addAll(chat_roomService.findMessageListByMemberAndChatRoom(member, chatRoom));
		totalMessageList.addAll(chat_roomService.findMessageListByMemberAndChatRoom(otherMember, chatRoom));
		
		Book book = findBookInMessage(totalMessageList);
		if (book != null) {
			redirectAttr.addFlashAttribute("book", book);
		}

		Collections.sort(totalMessageList, new SortBySentTime());

		mav.setViewName("redirect:/book/dm/" + memberId + ".do");
		redirectAttr.addFlashAttribute("messageList", totalMessageList);
		redirectAttr.addFlashAttribute("otherMember", otherMember);
		return mav;
	}

	@RequestMapping("/book/dm/{memberId}/{otherMemberId}/send.do")
	public ModelAndView sendDM(@PathVariable int memberId, @PathVariable int otherMemberId, String content) {
		Member member = memberService.getMember(memberId);
		Member otherMember = memberService.getMember(otherMemberId);

		Chat_room chatRoom = findChatRoom(member, otherMember);

		if (chatRoom == null) { // 채팅방 최초 개설 시
			chatRoom = chat_roomService.saveChatRoom(new Chat_room(0));
			System.out.println(chatRoom.getChat_room_id());
			chat_roomService.saveMembership(new Membership(chatRoom, member));
			chat_roomService.saveMembership(new Membership(chatRoom, otherMember));
		}

		LocalDate now = LocalDate.now();
		Date sentTime = java.sql.Date.valueOf(now);

		Message message = new Message(0, sentTime, content, member, chatRoom);
		chat_roomService.saveMessage(message);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book/dm/" + memberId + "/" + otherMemberId + ".do");
		return mav;
	}

	@RequestMapping("/book/dm/{memberId}/{otherMemberId}/rent.do")
	public ModelAndView rentBook(@PathVariable int memberId, @PathVariable int otherMemberId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book/dm/" + memberId + "/" + otherMemberId + ".do");

		Member member = memberService.getMember(memberId);
		Member otherMember = memberService.getMember(otherMemberId);

		Chat_room chatRoom = findChatRoom(member, otherMember);

		List<Message> totalMessageList = new ArrayList<>();
		totalMessageList.addAll(chat_roomService.findMessageListByMemberAndChatRoom(member, chatRoom));
		totalMessageList.addAll(chat_roomService.findMessageListByMemberAndChatRoom(otherMember, chatRoom));

		Book book = findBookInMessage(totalMessageList);
		if (book != null) {
			book.setAvailable(false);
			bookService.saveBook(book);

			String rentConfirmMessage = "대여해드리겠습니다. :)";
			mav.addObject("content", rentConfirmMessage);
			mav.setViewName("redirect:/book/dm/" + memberId + "/" + otherMemberId + "/send.do");
		}

		return mav;
	}

	public Chat_room findChatRoom(Member member, Member otherMember) {
		List<Membership> membership = chat_roomService.findMembershipListByMember(member);
		List<Membership> otherMembership = chat_roomService.findMembershipListByMember(otherMember);

		Chat_room chatRoom = null;
		for (int i = 0; i < membership.size(); i++) {
			Chat_room nowChatRoom = membership.get(i).getChatRoom();
			for (int j = 0; j < otherMembership.size(); j++) {
				if (nowChatRoom.equals(otherMembership.get(j).getChatRoom())) {
					chatRoom = nowChatRoom;
				}
			}
		}

		return chatRoom;
	}

	public Book findBookInMessage(List<Message> totalMessageList) {
		// 메시지에 포함된 책이름을 가져옴
		String title = "";
		for (int i = 0; i < totalMessageList.size(); i++) {
			int isRentMessage = totalMessageList.get(i).getContent().indexOf(" 대여 문의 드려요. :D");
			if (isRentMessage != -1) {
				title = totalMessageList.get(i).getContent().substring(7, isRentMessage);
				System.out.println(title);
			}
		}
		
		Book book = bookService.findBookByTitle(title);
		return book;
	}

}
