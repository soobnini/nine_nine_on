package com.sharebook.sharebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharebook.sharebook.domain.Member;

@Controller
public class LoginController {
	
	/*
	@RequestMapping("/hello.do") // "/hello.do" request 를 처리할 method 정의
	public String hello (Model model) {
		model.addAttribute("greeting", "안녕하세요 ");// model data
		return "hello"; // view 이름
	}
	*/
	
	@GetMapping("/book/register.do")
	public String showForm () {
		return "registerForm";
	}
	
	@PostMapping("/book/register.do") 
	public String register (Member member) {
		// 여기에 입력 값 검증해야함
		return "login";
	}
}
