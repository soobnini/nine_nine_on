package com.sharebook.sharebook.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MypageController {
	@GetMapping("/book/mypage.do")
	public String showMypage () {
		return "myPage";
	}
}
