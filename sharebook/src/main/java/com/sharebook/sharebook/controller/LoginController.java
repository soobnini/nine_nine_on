package com.sharebook.sharebook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.dao.MemberCommand;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.repository.MemberRepository;
import com.sharebook.sharebook.service.MemberFormValidator;
import com.sharebook.sharebook.service.ShareBookFacade;

@Controller
public class LoginController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	/*
	@Autowired
	private ShareBookFacade shareBook;
	public void setShareBook(ShareBookFacade shareBook) {
		this.shareBook = shareBook;
	}
	
	@Autowired
	private MemberFormValidator validator;
	public void setValidator(MemberFormValidator validator) {
		this.validator = validator;
	}
		
	@ModelAttribute("memberForm")
	public MemberForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			return new MemberForm(
					shareBook.getMember(userSession.getMember()));
		}
		else {	// create a new account
			return new MemberForm();
		}
	}
	*/
	@GetMapping("/book/register.do")
	public String showForm () {
		return "registerForm";
	}
	
	@PostMapping("/book/register.do") 
	public String register (MemberCommand memberCommand) {
		// 입력 값 검증 코드 추가 필요

		// @GeneratedValue 추가 필요
		Member member = new Member(6, memberCommand.getEmail(),
				memberCommand.getPassword(),
				memberCommand.getName(), 
				memberCommand.getNickname(),
				memberCommand.getPhone(),
				memberCommand.getAddress1(),
				memberCommand.getAddress2(),
				36.5f);
		memberRepository.save(member);
		
		/*
		// @GeneratedValue 추가 필요
		Member member = new Member();
		member.setMember_id(6);
		member.setEmail(memberCommand.getEmail());
		member.setPassword(memberCommand.getPassword());
		member.setName(memberCommand.getName());
		member.setNickname(memberCommand.getNickname());
		member.setPhone(memberCommand.getPhone());
		member.setAddress1(memberCommand.getAddress1());
		member.setAddress2(memberCommand.getAddress2());
		member.setTemperature(36.5f);
		*/
		return "login";
	}
	
	@GetMapping("/book/login.do")
	public String login () {
		return "login";
	}
}
