package com.sharebook.sharebook.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.service.FundingService;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class FundingController {
	
	@Autowired
	private FundingService fundingService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/book/funding/create.do")
	public String createFunding() {
		return "thymeleaf/createFunding";
	}
	
	@PostMapping("/book/funding/create.do")
	public String createFunding(HttpServletRequest request) throws ParseException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String image = request.getParameter("image");
		String description = request.getParameter("description");
		int goal_amount = Integer.parseInt(request.getParameter("goal_amount"));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date deadline = df.parse(request.getParameter("deadline"));
		
		Funding funding = new Funding();
		funding.setTitle(title);
		funding.setAuthor(author);
		funding.setImage(image);
		funding.setDescription(description);
		funding.setViews(0);
		funding.setGoal_amount(goal_amount);
		funding.setDeadline(deadline);
//		session으로 member 정보 얻도록 수정 필요
		funding.setMember(memberService.getMember(1));
		
		fundingService.insertFunding(funding);
		
//		리워드 추가 View 구현 후 리워드 insert 구현 필요

		int funding_id = funding.getFunding_id();
		return "redirect:/book/funding/" + funding_id + ".do";
	}
	
	@GetMapping("/book/funding.do")
	public ModelAndView fundingList() {
		return new ModelAndView("fundingList", "fundingList", fundingService.getFundingList());
	}

//	findBy___Containing(String ___) : 첫 번째 시도만 정상적인 결과가 반환되고 두 번째 시도부터 오류가 발생
	@GetMapping("/book/searchFunding/{title}.do")
	public ModelAndView searchFunding(@PathVariable String title) {
		return new ModelAndView("fundingList", "fundingList", fundingService.searchFundingListByTitle(title));
	}
	
	@GetMapping("/book/funding/{fundingId}.do")
	public ModelAndView detailFunding(@PathVariable int fundingId) {
		ModelAndView modelAndView = new ModelAndView("detailFunding");
		
		Funding funding = fundingService.getFunding(fundingId);
		modelAndView.addObject("funding", funding);
		modelAndView.addObject("rewardList", fundingService.getRewardList(funding));
		
		return modelAndView;
	}
	
}
