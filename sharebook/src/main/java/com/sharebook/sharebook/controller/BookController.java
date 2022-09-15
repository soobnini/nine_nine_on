package com.sharebook.sharebook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Genre;
import com.sharebook.sharebook.domain.Likes;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Region;
import com.sharebook.sharebook.domain.Review;
import com.sharebook.sharebook.domain.Store;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberService;
import com.sharebook.sharebook.service.StoreService;

@Controller
public class BookController {
	@Autowired
	public BookService bookService;
	@Autowired
	public MemberService memberService;
	@Autowired
	public StoreService storeService;

	@RequestMapping("/book/list.do")
	public ModelAndView viewBookList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		List<Region> regionList = storeService.findRegionList();
		List<Genre> genreList = bookService.findGenreList();

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

		if (flashMap != null) {
			String query = (String)flashMap.get("query");
			mav.addObject("query", query);
		}

		List<Book> bookList = bookService.findBookListSorted(2);
		
		Page<Book> bookPage = null;
		//List<Book> bookList = null;
		int totalPage = 0;
		//bookPage = bookService.getAllReview(page, orderBy);


		//totalPage = resultPage.getTotalPages();
		
		List<List<Book>> totalBookList = divideList(bookList);
		mav.addObject("bookList", totalBookList);

		List<Book> allRecommendBookList = bookService.findBookListSorted(4);
		List<Book> recommendBookList = new ArrayList<>();
		for (int i = 0; i < allRecommendBookList.size(); i++) {
			if (i >= 5) {
				break;
			}
			recommendBookList.add(allRecommendBookList.get(i));
		}
		mav.addObject("recommendBookList", recommendBookList);

		mav.setViewName("thymeleaf/bookList");
		mav.addObject("regionList", regionList);
		mav.addObject("genreList", genreList);
		return mav;
	}

	@RequestMapping("/book/list/sort.do")
	public ModelAndView sortBookList(String query) {
		ModelAndView mav = new ModelAndView();

		List<Book> bookList = bookService.findBookListSorted(Integer.parseInt(query));
		List<List<Book>> totalBookList = divideList(bookList);

		mav.setViewName("thymeleaf/bookListResult");
		mav.addObject("bookList", totalBookList);
		return mav;
	}

	@RequestMapping("/book/search.do")
	public ModelAndView searchBook(String query) {
		ModelAndView mav = new ModelAndView();

		List<Book> searchResult = new ArrayList<>();
		searchResult.addAll(bookService.findBookListByTitle(query));

		mav.setViewName("thymeleaf/bookListResult");
		mav.addObject("bookList", searchResult);
		return mav;
	}

	@RequestMapping("/book/direct/search.do")
	public ModelAndView searchDirectBook(String query, RedirectAttributes redirectAttr) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/book/list.do");
		redirectAttr.addFlashAttribute("query", query);
		return mav;
	}

	@RequestMapping("/book/list/condition/region.do")
	public ModelAndView regionConditionBookList(String query) {
		ModelAndView mav = new ModelAndView();

		Region region = storeService.findRegionById(Integer.parseInt(query));
		List<Store> storeList = storeService.findStoreListByRegion(region);

		List<Book> bookList = bookService.findSameRegionBookList(storeList.get(0));
		List<List<Book>> totalBookList = divideList(bookList);

		mav.setViewName("thymeleaf/bookListResult");
		mav.addObject("bookList", totalBookList);
		return mav;
	}

	@RequestMapping("/book/list/condition/genre.do")
	public ModelAndView genreConditionBookList(String query) {
		ModelAndView mav = new ModelAndView();

		Genre genre = bookService.findGenreById(Integer.parseInt(query));

		List<Book> bookList = bookService.findBookListByGenre(genre);
		List<List<Book>> totalBookList = divideList(bookList);

		mav.setViewName("thymeleaf/bookListResult");
		mav.addObject("bookList", totalBookList);
		return mav;
	}

	@RequestMapping("/book/view/{bookId}.do")
	public ModelAndView viewDetailBook(@PathVariable int bookId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		Book book = bookService.findBookById(bookId);
		book.setIntroduce(book.getIntroduce().replaceAll("<br>", "\r\n"));
		bookService.saveBook(book);

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

		List<Book> allSimilarBookList = bookService.findBookListByGenre(book.getGenre());
		List<Book> similarBookList = new ArrayList<>();
		for (int i = 0; i < allSimilarBookList.size(); i++) {
			if (i >= 5) {
				break;
			}
			similarBookList.add(allSimilarBookList.get(i));
		}

		List<Book> allRegionBookList = bookService.findSameRegionBookList(book.getStore());
		List<Book> regionBookList = new ArrayList<>();
		for (int i = 0; i < allRegionBookList.size(); i++) {
			if (i >= 5) {
				break;
			}
			regionBookList.add(allRegionBookList.get(i));
		}

		mav.setViewName("thymeleaf/detailBook");
		mav.addObject("book", book);
		mav.addObject("likesCount", likesList.size());

		mav.addObject("similarBookList", similarBookList);
		mav.addObject("regionBookList", regionBookList);
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
		}

		return mav;
	}

	@RequestMapping("/book/unlike/{bookId}.do")
	public ModelAndView unlikeBook(@PathVariable int bookId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());
			Book book = bookService.findBookById(bookId);

			Likes likes = bookService.findLikesByMemberAndBook(member, book);
			bookService.deleteLikes(likes);

			mav.setViewName("redirect:/book/view/" + bookId + ".do");
		}

		return mav;
	}

	public List<List<Book>> divideList(List<Book> bookList) {
		List<List<Book>> totalBookList = new ArrayList<>();
		if (bookList.size() > 0) {
			System.out.println("검색 결과 있음" + bookList.size());
			for (int i = 0; i <= (bookList.size() / 5); i++) {
				List<Book> partialBookList = new ArrayList<>();
				if (i == (bookList.size() / 5)) {
					if (bookList.size() % 5 == 0) {
						break;
					} else {
						for (int j = 0; j < (bookList.size() % 5); j++) {
							partialBookList.add(bookList.get((i * 5) + j));
						}
					}
				} else {
					for (int j = 0; j < 5; j++) {
						partialBookList.add(bookList.get((i * 5) + j));
					}
				}

				totalBookList.add(partialBookList);
			}
		}

		return totalBookList;
	}

}
