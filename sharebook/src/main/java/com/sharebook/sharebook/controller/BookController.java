package com.sharebook.sharebook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Likes;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class BookController {
	@Autowired
	public BookService bookService;
	@Autowired
	public MemberService memberService;
	
	@RequestMapping("/book/search.do")
	public ModelAndView viewDetailBook(String keyword) {
		List<Book> searchResult = new ArrayList<>();
		if(keyword == null) {
			searchResult.addAll(bookService.findBookList());
		}
		else {
			searchResult.add(bookService.findBookByTitle(keyword));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/bookList");
		mav.addObject("bookList", searchResult);
		return mav;
	}
	
	@RequestMapping("/book/view/create.do")
	public String viewCreateBook() {
		return "thymeleaf/createBook";
	}
	
	@RequestMapping("/book/create.do")
	public ModelAndView createBook(String title, String author, String description) {
		Member member = memberService.getMember(1);
		Book book = new Book(0, title, author, "4", description, 0, true, member);
		bookService.saveBook(book);
		
		Book savedBook = bookService.findBookByTitle(title);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book/view/" + savedBook.getBook_id() + ".do");
		return mav;
	}
	
	@RequestMapping("/book/view/{bookId}.do")
	public ModelAndView viewDetailBook(@PathVariable int bookId) {
		Book book = bookService.findBookById(bookId);
		List<Likes> likesList = bookService.findLikesListByBook(book);
		
		/*
		 *  나중에 로그인 연결하면 로그인 여부에 따라 likes 달라지는 거 추가하겠슴
		 */
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/detailBook");
		mav.addObject("book", book);
		mav.addObject("likesCount", likesList.size());
		return mav;
	}
	
	
	@RequestMapping("/book/like/{bookId}.do")
	public ModelAndView likeBook(@PathVariable int bookId) {
		Member member = memberService.getMember(1);
		Book book = bookService.findBookById(bookId);
		Likes likes = new Likes(member, book);
		bookService.saveLikes(likes);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book/view/" + bookId + ".do");
		return mav;
	}
	
}
