package com.sharebook.sharebook.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Notice;
import com.sharebook.sharebook.domain.Review;
import com.sharebook.sharebook.service.ReviewService;

@Controller
@RequestMapping("/book/Review")
public class reviewController {
	@Autowired
	private ReviewService reviewService;

	@RequestMapping("/List.do")
	public String reviewList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "orderBy", required = false, defaultValue = "1") int orderBy, ModelMap model)
			throws Exception {
		Page<Review> resultPage = null;
		List<Review> result = null;
		int totalPage = 0;

		/* 검색 */
		if (searchText != null && searchText != "") {
			resultPage = reviewService.getSearchReview(page, searchText, orderBy);
		}
		/* 전체글조회 */
		else {
			searchText = "";
			resultPage = reviewService.getAllReview(page, orderBy);
		}

		totalPage = resultPage.getTotalPages();

		result = resultPage.getContent();
		String regex = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
		result.forEach(content -> content.setContent(content.getContent().replaceAll(regex, "")));
		model.addAttribute("reviewList", result);

		model.addAttribute("searchText", searchText);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("orderBy", orderBy);
		return "thymeleaf/reviewList";
	}
	
	@RequestMapping("/detail.do")
	public String viewDetail(HttpServletRequest request, @RequestParam("reviewId") int reviewId, ModelMap model)
			throws Exception {
		Review review = reviewService.getReview(reviewId);
		
		int updateView = review.getViews() + 1;
		review.setViews(updateView);
		reviewService.updateReview(review);// view update

		model.addAttribute("Review", review);
		return "thymeleaf/reviewDetail";
	}// 상세 페이지
	
	@GetMapping("/uploadReview.do")
	public String form(HttpServletRequest request) {
		// 로그인 확인, 관리자인지 확인
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession == null)
			return "login";
		else {
			return "thymeleaf/reviewWrite";
		}
	}// 글 작성-forwarding

	@PostMapping("/uploadReview.do")
	public String uploadCommunity(HttpServletRequest request, @RequestParam("title") String title
			, @RequestParam("content") String content,
			RedirectAttributes redirectAttributes) {

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

		if (userSession == null)
			return "login";// 관리자 아니면 login화면으로

		LocalDateTime now = LocalDateTime.now();
		Timestamp upTime = Timestamp.valueOf(now);// 현재 시간 가져오기

		Review review = new Review();
		review.setTitle(title);
		review.setContent(content);
		review.setUpload_date(upTime);
		review.setMember(userSession.getMember());
		review.setImage(getThumbnail(content));
		review.setViews(0);

		int reviewId = reviewService.createReview(review);
		redirectAttributes.addAttribute("reviewId", reviewId);
		return "redirect:/book/Review/detail.do";
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
