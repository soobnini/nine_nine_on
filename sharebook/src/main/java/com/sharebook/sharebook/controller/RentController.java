package com.sharebook.sharebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberService;
import com.sharebook.sharebook.service.RentService;

@Controller
public class RentController {
	@Autowired
	public MemberService memberService;
	@Autowired
	public BookService bookService;
	@Autowired
	public RentService rentService;
}
