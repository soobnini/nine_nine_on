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
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Likes_funding;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Reward;
import com.sharebook.sharebook.service.FundingService;

@Controller
public class FundingController {
	
	@Autowired
	private FundingService fundingService;

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
		
//		funding 등록 버튼 선택 시 session 검사하도록 수정
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		funding.setMember(userSession.getMember());
		
		fundingService.insertFunding(funding);
		
		int funding_id = funding.getFunding_id();
		
		int reward_index = 1;
		String reward_price = "";
		String reward_prize = "";
		while (reward_price != null) {
			reward_price = request.getParameter("reward" + reward_index);
			if (reward_price == null) {
				continue;
			}
			reward_prize = request.getParameter("reward" + reward_index + "_description");
			
			Reward reward = new Reward();
			reward.setPrice(Integer.parseInt(reward_price));
			reward.setPrize(reward_prize);
			reward.setImage("/images/ex_image.png");
			reward.setFunding(fundingService.getFunding(funding_id));
			
			fundingService.insertReward(reward);
			
			reward_index++;
		}
		
		return "redirect:/book/funding/" + funding_id + ".do";
	}
	
	@GetMapping("/book/funding.do")
	public ModelAndView fundingList() {
		return new ModelAndView("fundingList", "fundingList", fundingService.getFundingList());
	}

	@GetMapping("/book/searchFunding/{title}.do")
	public ModelAndView searchFunding(@PathVariable String title) {
		return new ModelAndView("fundingList", "fundingList", fundingService.searchFundingListByTitle(title));
	}
	
	@GetMapping("/book/funding/{fundingId}.do")
	public ModelAndView detailFunding(HttpServletRequest request, @PathVariable int fundingId) {
		ModelAndView modelAndView = new ModelAndView("detailFunding");
		
		Funding funding = fundingService.getFunding(fundingId);
		funding.setViews(funding.getViews() + 1);
		fundingService.updateFunding(funding);
		
		long deadline = funding.getDeadline().getTime() / 1000 / 60 / 60 / 24;
		long today = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
		
		modelAndView.addObject("funding", funding);
		modelAndView.addObject("dDay", (int) (deadline - today) + 1);
		modelAndView.addObject("likesCount", fundingService.getLikes_fundingCount(funding));
		modelAndView.addObject("rewardList", fundingService.getRewardList(funding));
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String likes = "";
		if (fundingService.getLikes_funding(funding, userSession.getMember()) != null) {
			likes = "/images/filled_likes.png";
		} else {
			likes = "/images/blank_likes.png";
		}
		modelAndView.addObject("likes", likes);
		
//		Funding_order 정보 이용하여 achivememntRate 비율 계산하도록 수정
		modelAndView.addObject("achievementRate", 80);
		
		return modelAndView;
	}
	
	@GetMapping("/book/funding/{fundingId}/likes.do")
	public String likesFunding(HttpServletRequest request, @PathVariable int fundingId) {
		Funding funding = fundingService.getFunding(fundingId);
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Member member = userSession.getMember();
		
		Likes_funding likes_funding = fundingService.getLikes_funding(funding, member);
		if (likes_funding != null) {
			fundingService.deleteLikes_funding(likes_funding);
		} else {
			likes_funding = new Likes_funding(member, funding);
			fundingService.insertLikes_funding(likes_funding);
		}
		
		return "redirect:/book/funding/" + fundingId + ".do";
	}
	
	@PostMapping("/book/funding/order.do")
	public String orderFunding(HttpServletRequest request) throws ParseException {
		int fundingId = Integer.parseInt(request.getParameter("fundingId"));
		
//		order 기능 구현
		
		return "redirect:/book/funding/" + fundingId + ".do";
	}
	
}
