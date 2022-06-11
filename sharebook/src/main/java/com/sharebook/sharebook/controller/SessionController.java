package com.sharebook.sharebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.MemberService;

@Controller
@SessionAttributes("userSession")
public class SessionController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/book/login.do")
	public ModelAndView handleRequest(
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			Model model) throws Exception {

		Member member = memberService.findByEmailAndPassword(id, password);
		if (member == null) {
			return new ModelAndView("Error", "message", 
					"Invalid username or password.  Signon failed.");
		}
		else {
			UserSession userSession = new UserSession(member);

			model.addAttribute("userSession", userSession);

			return new ModelAndView("main");
		}
	}

}
