package com.sharebook.sharebook.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.MemberService;

@Controller
@SessionAttributes("userSession")
public class SessionController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/book/login.do")
	public ModelAndView handleRequest(
			HttpSession session,
			HttpServletResponse response,
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			Model model) throws Exception {

		ModelAndView mav = new ModelAndView();
		Member member = memberService.findByEmailAndPassword(id, password);
		
		if (member == null) {
			mav.setViewName("login");

			response.setContentType("text/html;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보가 일치하지 않습니다');</script>");
			out.println("<script>history.back();</script>");
			out.flush();
		}
		else {
			UserSession userSession = new UserSession(member);
			model.addAttribute("userSession", userSession);
			mav.setViewName("redirect:/book.do");
		}
		
		session.setAttribute("member", member);
		
		return mav;
	}

}
