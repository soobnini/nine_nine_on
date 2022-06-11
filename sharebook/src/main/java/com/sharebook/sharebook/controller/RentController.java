package com.sharebook.sharebook.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class RentController {
	@Autowired
	public BookService bookService;
	@Autowired
	public MemberService memberService;
	
	@RequestMapping("/book/dm/{memberId}/{otherMemberId}/{bookId}/rent.do")
	public ModelAndView sendDM(@PathVariable int memberId, @PathVariable int otherMemberId, @PathVariable int bookId) {
		Book book = bookService.findBookById(bookId);
		book.setAvailable(false);
		bookService.saveBook(book);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book/dm/" + memberId + "/" + otherMemberId + ".do");
		return mav;
	}
}
