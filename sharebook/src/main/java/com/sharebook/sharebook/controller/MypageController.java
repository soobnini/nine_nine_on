package com.sharebook.sharebook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
public class MypageController {
	@GetMapping("/book/mypage.do")
	public String showMypage (HttpServletRequest request) {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
	
		System.out.println(userSession);
			if (userSession == null) {
//				String url = request.getRequestURL().toString(); 
//				String query = request.getQueryString();
//				ModelAndViewn modelAndView = new ModelAndView("login");
				return "login";
			}
			else
				return "myPage";

	}
}
