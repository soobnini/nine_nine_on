package com.sharebook.sharebook.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.MemberService;

@RestController
public class SNSLoginController {
	@Autowired
	private MemberService memberService;
	
	KakaoAPI kakaoApi = new KakaoAPI();
	
	
	@RequestMapping(value="/book/login/test/1.do")
	public ModelAndView login(@RequestParam("code") String code, HttpSession session, Model model) {
		ModelAndView mav = new ModelAndView();
		// 1번 : 인증코드 요청 전달
		String accessToken = kakaoApi.getAccessToken(code);
		// 2번 : 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
		
		System.out.println("login info : " + userInfo.toString());
		
		if(userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("accessToken", accessToken);
		}
		mav.addObject("userId", userInfo.get("email"));
		mav.setViewName("login");
		
		// 추후 수정 필요
		Member member = new Member();
		member.setMember_id(0);
		member.setEmail(" ");
		member.setPassword(" ");
		member.setName(" ");
		member.setNickname(" ");
		member.setPhone(" ");
		member.setAddress1(" ");
		member.setAddress2(" ");
		member.setTemperature(36f);
		member.setImage("/images/ex_image.png");
		member.setAdmin(0);
		
		memberService.createMember(member);
		System.out.println("멤버... 생성 되었을까요?");
		System.out.println("member: " + member);
		
		UserSession userSession = new UserSession(member);
		model.addAttribute("userSession", userSession);
		mav.setViewName("redirect:/book.do");
		
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		mav.setViewName("login");
		return mav;
	}
}
