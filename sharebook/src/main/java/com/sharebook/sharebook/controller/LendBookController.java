package com.sharebook.sharebook.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Genre;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Store;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberService;
import com.sharebook.sharebook.service.StoreService;

@Controller
public class LendBookController {
	@Autowired
	public BookService bookService;
	@Autowired
	public MemberService memberService;
	@Autowired
	public StoreService storeService;

	@RequestMapping("/book/view/create.do")
	public ModelAndView viewCreateBook(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("thymeleaf/login");
			return mav;
		}

		List<Store> storeList = storeService.findStoreList();
		List<Genre> genreList = bookService.findGenreList();
		mav.addObject("storeList", storeList);
		mav.addObject("genreList", genreList);
		mav.setViewName("thymeleaf/createBook");

		return mav;
	}

	@RequestMapping("/book/view/create/api.do")
	public ModelAndView viewCreateBookFromAPI(String title, String author, String publisher, String publishYear,
			String image, String isbn, HttpServletRequest request, RedirectAttributes redirectAttr) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("thymeleaf/login");
			return mav;
		}

		mav.setViewName("redirect:/book/view/create.do");
		redirectAttr.addFlashAttribute("isAPI", true);

		redirectAttr.addFlashAttribute("title", title);
		redirectAttr.addFlashAttribute("author", author);
		redirectAttr.addFlashAttribute("publisher", publisher);
		redirectAttr.addFlashAttribute("publishYear", publishYear);
		redirectAttr.addFlashAttribute("image", image);
		redirectAttr.addFlashAttribute("isbn", isbn);

		return mav;
	}

	@RequestMapping("/book/create.do")
	public ModelAndView createBook(String title, String author, String publisher, String publishYear,
			MultipartFile image, String imageUrl, String genreId, String description, String introduce, String isbn,
			String store_id, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("thymeleaf/login");
		} else { // 로그인이 되어있는 경우

			Member member = memberService.getMember(userSession.getMember().getMember_id());
			Genre genre = bookService.findGenreById(Integer.parseInt(genreId));
			Store store = storeService.findStoreById(Integer.parseInt(store_id));

			String filename = "";
			System.out.println(imageUrl);
			if (imageUrl.startsWith("https")) {
				System.out.println(imageUrl);
				filename = imageUrl;
			} else {
				filename = uploadFile(image, request);
			}

			String introduceContent = introduce.replace("\r\n", "<br>");
			
			if(description.length() > 500) {
				description = description.substring(500);
			}
			
			if(introduceContent.length() > 500) {
				introduceContent = introduceContent.substring(500);
			}

			Book book = new Book(0, title, author, publisher, filename, description, introduceContent, isbn,
					publishYear, 0, true, member, genre, store); // genre null값 추후 수정
			// 필요
			int bookId = bookService.saveBook(book).getBookId();

			mav.setViewName("redirect:/book/view/" + bookId + ".do");
		}

		return mav;
	}

	private String uploadFile(MultipartFile image, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String uploadLocalDir = userSession.getUploadDirLocal();

		String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
		System.out.println(uploadLocalDir + filename + " 업로드");

		File file = new File(uploadLocalDir + filename);

		try {
			image.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
