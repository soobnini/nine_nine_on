package com.sharebook.sharebook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

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
	
	@RequestMapping("/book.do")
	public ModelAndView viewMain(HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.addObject("isLogin", false);
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			System.out.println(member.getName());
			mav.addObject("isLogin", true);
			mav.addObject("address1", member.getAddress1());
			mav.addObject("address2", member.getAddress2());
			
			List<Book> nearBookList = bookService.findNearBookList(member);
			mav.addObject("nearBookList", nearBookList);
		}
		
		List<Book> recommendList = bookService.findRecommendBookList();
		List<Book> newBookList = bookService.findBookList();
		
		mav.setViewName("thymeleaf/main");
		mav.addObject("recommendList", recommendList);
		mav.addObject("newBookList", newBookList);
		return mav;
	}
	
	@RequestMapping("/book/search.do")
	public ModelAndView viewDetailBook(String keyword) {
		List<Book> searchResult = new ArrayList<>();
		if(keyword == null) {
			searchResult.addAll(bookService.findBookList());
		}
		else {
			searchResult.addAll(bookService.findBookListByTitle(keyword));
			searchResult.addAll(bookService.findBookListByAuthor(keyword));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/bookList");
		mav.addObject("bookList", searchResult);
		return mav;
	}
	
	@RequestMapping("/book/view/create.do")
	public String viewCreateBook(HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		if (userSession == null) { // 로그인이 안되어있는 경우
			return "login";
		}
		
		return "thymeleaf/createBook";
	}
	
	@RequestMapping("/book/create.do")
	public ModelAndView createBook(String title, String author, String description, HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
		
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			Book book = new Book(0, title, author, "4", description, 0, true, member);
			bookService.saveBook(book);
			
			Book savedBook = bookService.findBookByTitle(title);
			
			mav.setViewName("redirect:/book/view/" + savedBook.getBook_id() + ".do");
		}
		
		return mav;
	}
	
	@RequestMapping("/book/view/{bookId}.do")
	public ModelAndView viewDetailBook(@PathVariable int bookId, HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
		
		Book book = bookService.findBookById(bookId);
		List<Likes> likesList = bookService.findLikesListByBook(book);
		
		book.setViews(book.getViews() + 1);
		bookService.saveBook(book);
		
		mav.addObject("isLike", false);
		
		if(userSession != null) { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			Likes likes = bookService.findLikesByMemberAndBook(member, book);
			
			if(likes != null) {
				mav.addObject("isLike", true);
			}
		}
		
		mav.setViewName("thymeleaf/detailBook");
		mav.addObject("book", book);
		mav.addObject("likesCount", likesList.size());
		return mav;
	}
	
	@RequestMapping("/book/like/{bookId}.do")
	public ModelAndView likeBook(@PathVariable int bookId, HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
		
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		}
		else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			Book book = bookService.findBookById(bookId);
			Likes likes = new Likes(member, book);
			bookService.saveLikes(likes);
			
			mav.setViewName("redirect:/book/view/" + bookId + ".do");
		}
		
		return mav;
	}
	
}
