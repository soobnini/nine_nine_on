package com.sharebook.sharebook.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.thymeleaf.util.StringUtils;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Likes;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.FundingService;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class BookController implements ApplicationContextAware {
	@Autowired
	public BookService bookService;
	@Autowired
	public MemberService memberService;
	@Autowired
	public FundingService fundingService;

	@Value("/upload/")
	private String uploadDirLocal;
	private WebApplicationContext context;
	private String uploadDir;

	@Override // life-cycle callback method
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath(this.uploadDirLocal);
		System.out.println(this.uploadDir);
	}

	@RequestMapping("/")
	public ModelAndView handleRequest() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/book.do");
		return mav;
	}

	@RequestMapping("/book.do")
	public ModelAndView viewMain(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.addObject("isLogin", false);
		} else { // 로그인이 되어있는 경우
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
		List<Funding> fundingList = fundingService.getTop3FundingListSortedByDeadline();

		mav.setViewName("thymeleaf/main");
		mav.addObject("recommendList", recommendList);
		mav.addObject("newBookList", newBookList);
		mav.addObject("fundingList", fundingList);
		mav.addObject("uploadDirLocal", uploadDirLocal);
		return mav;
	}

	@RequestMapping("/book/search.do")
	public ModelAndView viewDetailBook(String keyword, String sortType) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sortingType", sortType);
		
		List<Book> searchResult = new ArrayList<>();
		if (keyword == null || keyword == "") {
			if(sortType == null) {
				searchResult.addAll(bookService.findBookList());
			}
			else {
				searchResult.addAll(bookService.findBookListSorted(Integer.parseInt(sortType)));
			}
			mav.addObject("keyword", "");
		} else {
			if(sortType == null) {
				searchResult.addAll(bookService.findBookListByTitle(keyword));
				searchResult.addAll(bookService.findBookListByAuthor(keyword));
			}
			else {
				searchResult.addAll(bookService.findBookListByTitleSorted(keyword, Integer.parseInt(sortType)));
				searchResult.addAll(bookService.findBookListByAuthorSorted(keyword, Integer.parseInt(sortType)));
			}
			mav.addObject("keyword", keyword);
		}

		mav.setViewName("thymeleaf/bookList");
		mav.addObject("bookList", searchResult);
		mav.addObject("uploadDirLocal", uploadDirLocal);
		return mav;
	}

	@RequestMapping("/book/view/create.do")
	public String viewCreateBook(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

		if (userSession == null) { // 로그인이 안되어있는 경우
			return "login";
		}

		return "thymeleaf/createBook";
	}

	@RequestMapping("/book/create.do")
	public ModelAndView createBook(String title, String author, String description, MultipartFile image,
			HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			String filename = uploadFile(image);
			Book book = new Book(0, title, author, filename, description, 0, true, member);
			int bookId = bookService.saveBook(book).getBook_id();

			mav.setViewName("redirect:/book/view/" + bookId + ".do");
			mav.addObject("uploadDirLocal", uploadDirLocal);
		}

		return mav;
	}

	@RequestMapping("/book/view/{bookId}.do")
	public ModelAndView viewDetailBook(@PathVariable int bookId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		Book book = bookService.findBookById(bookId);
		List<Likes> likesList = bookService.findLikesListByBook(book);

		book.setViews(book.getViews() + 1);
		bookService.saveBook(book);

		mav.addObject("isLike", false);

		if (userSession != null) { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			Likes likes = bookService.findLikesByMemberAndBook(member, book);

			if (likes != null) {
				mav.addObject("isLike", true);
			}
		}

		mav.setViewName("thymeleaf/detailBook");
		mav.addObject("book", book);
		mav.addObject("likesCount", likesList.size());
		mav.addObject("uploadDirLocal", uploadDirLocal);
		return mav;
	}

	@RequestMapping("/book/like/{bookId}.do")
	public ModelAndView likeBook(@PathVariable int bookId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			Book book = bookService.findBookById(bookId);
			Likes likes = new Likes(member, book);
			bookService.saveLikes(likes);

			mav.setViewName("redirect:/book/view/" + bookId + ".do");
			mav.addObject("uploadDirLocal", uploadDirLocal);
		}

		return mav;
	}

	private String uploadFile(MultipartFile image) {
		String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
		System.out.println(this.uploadDir + filename + " 업로드");

		File file = new File(this.uploadDir + filename);

		try {
			image.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
