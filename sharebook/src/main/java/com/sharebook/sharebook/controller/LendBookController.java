package com.sharebook.sharebook.controller;

import java.io.File;
import java.io.IOException;
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
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.BookService;
import com.sharebook.sharebook.service.MemberService;

@Controller
public class LendBookController implements ApplicationContextAware {
	@Autowired
	public BookService bookService;
	@Autowired
	public MemberService memberService;

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

	@RequestMapping("/book/view/create.do")
	public String viewCreateBook(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

		if (userSession == null) { // 로그인이 안되어있는 경우
			return "login";
		}

		return "thymeleaf/createBook";
	}

	@RequestMapping("/book/view/create/api.do")
	public ModelAndView viewCreateBookFromAPI(String title, String author, String publisher, String image,
			HttpServletRequest request, RedirectAttributes redirectAttr) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
			return mav;
		}

		mav.setViewName("redirect:/book/view/create.do");
		redirectAttr.addFlashAttribute("isAPI", true);

		redirectAttr.addFlashAttribute("title", title);
		redirectAttr.addFlashAttribute("author", author);
		redirectAttr.addFlashAttribute("publisher", publisher);
		redirectAttr.addFlashAttribute("image", image);

		return mav;
	}

	@RequestMapping("/book/create.do")
	public ModelAndView createBook(String title, String author, String publisher, MultipartFile image,
			String description, String isbn, int publishYear, String store, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();

		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("login");
		} else { // 로그인이 되어있는 경우
			Member member = memberService.getMember(userSession.getMember().getMember_id());

			String filename = uploadFile(image);
			Book book = new Book(0, title, author, publisher, filename, description, isbn, publishYear, 0, true, member,
					null, null); // genre null값 추후 수정
			// 필요
			int bookId = bookService.saveBook(book).getBook_id();

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
