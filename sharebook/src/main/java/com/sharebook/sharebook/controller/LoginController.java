package com.sharebook.sharebook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.sharebook.sharebook.domain.Genre;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Member_genre;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberFormValidator;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MemberFormValidator validator;
	public void setValidator(MemberFormValidator validator) {
		this.validator = validator;
	}
	
	@GetMapping("/book/register/1.do")
	public String register_step1 () {
		return "thymeleaf/registForm1";
	}

	@GetMapping("/book/register/3.do")
	public String register_step3 () {
		return "registerForm_step3";
	}
	
	@GetMapping("/book/register/2.do")
	public String showForm (Model model) {
		List<Genre> genreList= bookService.findGenreList();
		model.addAttribute("memberCommand", new MemberCommand());
		model.addAttribute("genreList",genreList);
		return "registerForm";
	}
	
	
	@PostMapping("/book/register/2.do") 
	public ModelAndView register (HttpServletResponse response,
			@ModelAttribute("memberCommand") MemberCommand memberCommand,
			BindingResult result,@RequestParam("genreList") int[] genres) throws IOException {
		ModelAndView mav = new ModelAndView();
		List<Member_genre> memberSelect = new ArrayList<Member_genre>();
		
		validator.validateMemberCommand(memberCommand, result);
		if (result.hasErrors()) { // 오류 있는 경우
			List<Genre> genreList= bookService.findGenreList();
			mav.addObject("genreList",genreList);
			mav.setViewName("registerForm");
		}
		else { // 오류 없는 경우
			mav.setViewName("/thymeleaf/login");
			
			Member member = new Member();
			member.setMember_id(0);
			member.setEmail(memberCommand.getEmail());
			member.setPassword(memberCommand.getPassword());
			member.setName(memberCommand.getName());
			member.setNickname(memberCommand.getNickname());
			member.setPhone(memberCommand.getPhone());
			member.setAddress1(memberCommand.getAddress1());
			member.setAddress2(memberCommand.getAddress2());
			member.setTemperature(0.0f);
			member.setImage("../images/icon/characterLogo.png");
			member.setAdmin(0);
			
			memberService.createMember(member);
			
			for(int i = 0; i < genres.length; i++) {
				memberSelect.add(new Member_genre(member,bookService.getGenre(genres[i])));
			}
			if(memberSelect != null) {
				memberService.createMemberGenre(memberSelect);
			}
			
			
			
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('회원가입에 성공하였습니다'); location.href='/book/login.do';</script>");
//			out.flush();
			
			mav.addObject("name", member.getName());
			mav.setViewName("registerForm_step3");
		}
		
		return mav;
	}
	
	@GetMapping("/book/login.do")
	public String login () {
		return "thymeleaf/Login";
	}
	
	@GetMapping("/book/logout.do")
	public String handleRequest(HttpSession session) throws Exception {
		session.removeAttribute("userSession");
		session.invalidate();
		return "redirect:/book.do";
	}
	
}
