package com.sharebook.sharebook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.service.CommunityService;
import com.sharebook.sharebook.service.FundingService;
import com.sharebook.sharebook.service.MemberService;
import com.sharebook.sharebook.service.RentService;
import com.sharebook.sharebook.service.BookService;

import java.util.List;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Likes;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Rent;

@Controller
public class MypageController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private FundingService fundingService;
	
	@GetMapping("/book/mypage.do")
	public ModelAndView showMypage (HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
		
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
//			System.out.println(member.getMember_id());
			mav.setViewName("myPage");
			mav.addObject("name", member.getName());
			mav.addObject("temperature", member.getTemperature());
		}	
		
		return mav;
	}
	
	@GetMapping("/book/mypage/member.do")
	public ModelAndView showMemberModifyPage (HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
		
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			mav.setViewName("myPage");
			mav.addObject("name", member.getName());
			mav.addObject("temperature", member.getTemperature());
			mav.addObject("category","member");
		}	
		
		return mav;
	}
	
	@GetMapping("/book/mypage/likes.do")
	public ModelAndView showlikesPage (HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			mav.setViewName("myPage");
			mav.addObject("name", member.getName());
			mav.addObject("temperature", member.getTemperature());
			mav.addObject("category","likes");
			
			List<Likes> bookList = bookService.findLikesListByMember(member);
			System.out.println(bookList.size());
			System.out.println(bookList);
			mav.addObject("bookList", bookList);
		}	

		return mav;
	}
	
	@GetMapping("/book/mypage/rent.do")
	public ModelAndView showRentPage (HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			mav.setViewName("myPage");
			mav.addObject("name", member.getName());
			mav.addObject("temperature", member.getTemperature());
			mav.addObject("category","rent");
			
			List<Rent> rentList = rentService.getRentList(member);
			System.out.println(rentList.size());
			System.out.println(rentList);
			mav.addObject("rentList", rentList);
		}	

		return mav;
	}
	
	@GetMapping("/book/mypage/funding.do")
	public ModelAndView showFundingPage (HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			mav.setViewName("myPage");
			mav.addObject("name", member.getName());
			mav.addObject("temperature", member.getTemperature());
			mav.addObject("category","funding");
			
			List<Funding> fundingList = fundingService.searchFundingListByMember_id(member);
			System.out.println(fundingList.size());
			System.out.println(fundingList);
			mav.addObject("fundingList", fundingList);
		}	

		return mav;
	}
	
	@GetMapping("/book/mypage/community.do")
	public ModelAndView showCommunityPage (HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			mav.setViewName("myPage");
			mav.addObject("name", member.getName());
			mav.addObject("temperature", member.getTemperature());
			mav.addObject("category","community");
			
			List<Community> communityList =  communityService.findCommunityByUser(member);
			System.out.println(communityList.size());
			System.out.println(communityList);
			mav.addObject("communityList", communityList);
		}	

		return mav;
	}
	
}
