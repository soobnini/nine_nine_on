package com.sharebook.sharebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sharebook.sharebook.dao.MemberCommand;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.MemberFormValidator;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberFormValidator validator;
	public void setValidator(MemberFormValidator validator) {
		this.validator = validator;
	}
	
	@GetMapping("/book/register.do")
	public String showForm (Model model) {
		model.addAttribute("memberCommand", new MemberCommand());
		return "registerForm";
	}
	
	@PostMapping("/book/register.do") 
	public String register (@ModelAttribute("memberCommand") MemberCommand memberCommand,
			BindingResult result) {
		validator.validateMemberCommand(memberCommand, result);
		if (result.hasErrors()) return "registerForm";

		Member member = new Member();
		member.setMember_id(0);
		member.setEmail(memberCommand.getEmail());
		member.setPassword(memberCommand.getPassword());
		member.setName(memberCommand.getName());
		member.setNickname(memberCommand.getNickname());
		member.setPhone(memberCommand.getPhone());
		member.setAddress1(memberCommand.getAddress1());
		member.setAddress2(memberCommand.getAddress2());
		member.setTemperature(36.5f);
		member.setImage("/images/ex_image.png");
		
		memberService.createMember(member);
		
		return "login";
	}
	
	@GetMapping("/book/login.do")
	public String login () {
		return "login";
	}
	
	@GetMapping("/book/logout.do")
	public String handleRequest(HttpSession session) throws Exception {
		session.removeAttribute("userSession");
		session.invalidate();
		return "main";
	}
	
}
