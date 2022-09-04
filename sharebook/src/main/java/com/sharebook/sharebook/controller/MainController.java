package com.sharebook.sharebook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class MainController {
	@Autowired
	public BookService bookService;
	@Autowired
	public MemberService memberService;

	@RequestMapping("/")
	public ModelAndView handleRequest() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book.do");
		return mav;
	}

	@RequestMapping("/book.do")
	public ModelAndView viewMain(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.addObject("isLogin", false);
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			System.out.println(member.getName());
			mav.addObject("isLogin", true);
			mav.addObject("address1", member.getAddress1());
			mav.addObject("address2", member.getAddress2());

			List<Book> nearBookList = bookService.findNearBookList(member);
			mav.addObject("nearBookList", nearBookList);
		}

		List<Book> recommendList = bookService.findRecommendBookList();
		List<Book> newBookList = bookService.findBookList();

		mav.setViewName("thymeleaf/main");
		mav.addObject("recommendList", recommendList);
		mav.addObject("newBookList", newBookList);
		//mav.addObject("uploadDirLocal", uploadDirLocal);	// 일단 주석처리했는데 나중에 오류나면 수정하겠음
		return mav;
	}
	
	/*
	 *  header and footer
	 */
	@RequestMapping("/book/header.do")
	public ModelAndView loadHeader() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/fragments/header");
		return mav;
	}
	
	@RequestMapping("/book/footer.do")
	public ModelAndView loadFooter() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/fragments/footer");
		return mav;
	}
}
