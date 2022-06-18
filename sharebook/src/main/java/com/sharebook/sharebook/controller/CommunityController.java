package com.sharebook.sharebook.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.controller.UserSession;
import com.sharebook.sharebook.domain.Comments;
import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.service.CommunityService;

@Controller
@RequestMapping("/book/community")
public class CommunityController {
	@Autowired
	private CommunityService communityService;

	@RequestMapping("/view/{page}.do")
	public String viewCommunity(@PathVariable int page, ModelMap model)
			throws Exception {
		Page<Community> resultPage = communityService.findPageCommunities(page);
		List<Community> result = resultPage.getContent();
		int pagenum = resultPage.getTotalPages();
		if (result != null)
			model.addAttribute("communities", result);
			model.addAttribute("pagenum", pagenum);
		return "community";
	}// 전체 community글목록

	@RequestMapping("/viewCategory/{categoryId}/{page}.do")
	public String viewCategoryCommunity(@PathVariable int categoryId, @PathVariable int page, ModelMap model)
			throws Exception {
		Page<Community> resultPage = communityService.findPageCommCategory(categoryId, page);
		List<Community> result = resultPage.getContent();
		int pagenum = resultPage.getTotalPages();
		if (result != null)
			model.addAttribute("communities", result);
			model.addAttribute("pagenum", pagenum);
		return "community";
	}// 카테고리 별 글목록

	@RequestMapping("/detail.do")
	public String viewDetail(@RequestParam("commId") int communityId, ModelMap model) throws Exception {
		Community comm = communityService.getComm(communityId);
		List<Comments> comments = communityService.findCommentByCommunity(comm);
		model.addAttribute("Post", comm);
		model.addAttribute("Comments", comments);
		return "detailCommunity";
	}//커뮤니티 상세 페이지
	
	@RequestMapping("/uploadCommunity.do")
	public String form(HttpServletRequest request) {
		//로그인 확인
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if(userSession == null)
			return "login";
		else {
			return "createCommunity";
		}
	}//글 작성-forwarding
	
	@RequestMapping("/upload.do")
	public String uploadCommunity(HttpServletRequest request, @RequestParam("title") String title,@RequestParam("category") int category,@RequestParam("content") String content, RedirectAttributes redirectAttributes) {
		Community comm = new Community();
		comm.setCategory(category);
		comm.setTitle(title);
		comm.setContent(content);
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Member member = userSession.getMember();
		comm.setMember(member);
		//comm.setUpload_date(now);
		comm.setViews(0);
		int communityId = communityService.createCommunity(comm);
		redirectAttributes.addAttribute("commId", communityId);
		String uri ="redirect:/detail.do";
		return uri;
	}//글 작성-나중에 입력값 검증 넣어야(걍 form에서 못넘어가게해도 될거같은디)
	
	@RequestMapping("/createComment.do")
	public String createComment(HttpServletRequest request,@RequestParam("commId") int cid,@RequestParam("content") String content,  RedirectAttributes redirectAttributes) {
		//로그인 확인
		Community comm = communityService.getComm(cid);
		Comments comment = new Comments();
		comment.setCommunity(comm);
		comment.setContent(content);
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Member member = userSession.getMember();//userSession으로부터 멤버 객체 가져오기
		comment.setMember(member);
		LocalDateTime now = LocalDateTime.now();
		Timestamp upTime = Timestamp.valueOf(now);
		comment.setUpload_date(upTime);
		communityService.createComment(comment);
		redirectAttributes.addAttribute("commId", cid);
		return "redirect:detail.do";
	}//댓글 작성-로그인 확인 넣기
	
	@RequestMapping("/delete.do")
	public String deleteCommunity(@RequestParam("member") Member member, @RequestParam("cid") int cid) {
		//세션이랑 작성자 일치하는지 확인
		Community comm = communityService.getComm(cid);
		communityService.deleteCommunity(comm);
		String uri ="view/0.do";
		return uri;
	}//글 삭제

}
