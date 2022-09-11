package com.sharebook.sharebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BrandPageController {
	@RequestMapping("/book/ideology.do")
	public String viewIdeology() {
		return "thymeleaf/ideology";
	}
	
	@RequestMapping("/book/manual.do")
	public String viewManual() {
		return "thymeleaf/manual";
	}
	
	@RequestMapping("/book/question/often.do")
	public String viewOftenQuestion() {
		return "thymeleaf/oftenQuestion";
	}
}
