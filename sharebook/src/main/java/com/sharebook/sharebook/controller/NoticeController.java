package com.sharebook.sharebook.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Notice;
import com.sharebook.sharebook.service.NoticeService;

@Controller
@RequestMapping("/book/Notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/List.do")
	public String noticeList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "orderBy", required = false, defaultValue = "1") int orderBy, ModelMap model)
			throws Exception {
		Page<Notice> resultPage = null;
		List<Notice> result = null;
		int totalPage = 0;
		result = noticeService.getpinList();
		
		/* 검색 */
		if (searchText != null && searchText != "") {
			resultPage = noticeService.searchNotice(page, searchText, orderBy);
		}
		/* 전체글조회 */
		else {
			searchText = "";
			resultPage = noticeService.getAllNotice(page, orderBy);
		}

		totalPage = resultPage.getTotalPages();
		if (result != null)
		{
			result.addAll(resultPage.getContent());
			model.addAttribute("noticeList", result);
		}
		else {
			result = resultPage.getContent();
			model.addAttribute("noticeList", result);
		}
		model.addAttribute("searchText", searchText);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("orderBy", orderBy);
		return "thymeleaf/noticeList";
	}

	@RequestMapping("/detail.do")
	public String viewDetail(HttpServletRequest request, @RequestParam("noticeId") int noticeId, ModelMap model)
			throws Exception {
		Notice notice = noticeService.getNotice(noticeId);
		// List<Comments> comments =
		// noticeService.findCommentByCommunity(notice);**Notice에도 댓글있음???
		int updateView = notice.getViews() + 1;
		notice.setViews(updateView);
		noticeService.updateNotice(notice);// view update

		model.addAttribute("Notice", notice);
		return "thymeleaf/noticeDetail";
	}// 상세 페이지

	@GetMapping("/uploadNotice.do")
	public String form(HttpServletRequest request) {
		// 로그인 확인, 관리자인지 확인
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession == null && userSession.getMember().getAdmin() == 0)
			return "login";
		else {
			return "thymeleaf/noticeWrite";
		}
	}// 글 작성-forwarding

	@PostMapping("/uploadNotice.do")
	public String uploadCommunity(HttpServletRequest request, @RequestParam("title") String title,
			@RequestParam("category") int category, @RequestParam("content") String content,
			RedirectAttributes redirectAttributes) {

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

		if (userSession == null || userSession.getMember().getAdmin() == 0)
			return "login";// 관리자 아니면 login화면으로

		LocalDateTime now = LocalDateTime.now();
		Timestamp upTime = Timestamp.valueOf(now);// 현재 시간 가져오기

		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setUpload_date(upTime);
		notice.setMember(userSession.getMember());
		notice.setCategory(category);
		notice.setImage(getThumbnail(content));// TODO:: 이미지 업로드, 위지윅 에디터 방식으로..?
		notice.setIspin(0);
		notice.setViews(0);

		int noticeId = noticeService.createNotice(notice);
		redirectAttributes.addAttribute("noticeId", noticeId);
		return "redirect:/book/Notice/detail.do";
	}// 글 작성

	public String getThumbnail(String content) {

		String resultString = null;

		Pattern regex = Pattern.compile("(?<=<img src=\")+.+(?=\")");

		Matcher regexMatcher = regex.matcher(content);

		if (regexMatcher.find()) {

			resultString = regexMatcher.group();

		}
		if(resultString != null) {
			return resultString.trim();
		}
		return " ";
	}
}
