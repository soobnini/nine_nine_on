package com.sharebook.sharebook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/List.do")
	public String communityList(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
								@RequestParam(value = "category", required = false, defaultValue = "0") int category,
								@RequestParam(value = "searchText", required = false) String searchText,
								ModelMap model) throws Exception
	{
		Page<Community> resultPage = null;
		List<Community> result = null;
		int totalPage = 0;
		/*커뮤니티 전체*/
		if(category == 0) {
			/*전체검색*/
			if(searchText != null) {
				//TODO::검색 기능 구현
				resultPage = communityService.searchPageCommunities(page, searchText);
			}
			/*전체 글조회*/
			else {
				resultPage = communityService.findPageCommunities(page);
			}
		}
		else {
			/*카테고리별 검색*/
			if(searchText !=null) {
				resultPage = communityService.searchCategoryPageCommunities(page, category, searchText);
			}
			/*전체글조회*/
			else {
				resultPage = communityService.findPageCommCategory(category, page);
			}
		}
		result = resultPage.getContent();
		totalPage = resultPage.getTotalPages();
		if (result != null)
			model.addAttribute("communities", result);
			model.addAttribute("totalPage", totalPage);
		return "thymeleaf/communityList";
	}

	/* 커뮤니티 리스트 기능 통합으로 미사용 예정
	 * @RequestMapping("/view/{page}.do") public String viewCommunity(@PathVariable
	 * int page, ModelMap model) throws Exception { Page<Community> resultPage =
	 * communityService.findPageCommunities(page); List<Community> result =
	 * resultPage.getContent(); int pagenum = resultPage.getTotalPages(); if (result
	 * != null) model.addAttribute("communities", result);
	 * model.addAttribute("pagenum", pagenum); return "community"; }// 전체
	 * community글목록
	 * 
	 * @RequestMapping("/viewCategory/{categoryId}/{page}.do") public String
	 * viewCategoryCommunity(@PathVariable int categoryId, @PathVariable int page,
	 * ModelMap model) throws Exception { Page<Community> resultPage =
	 * communityService.findPageCommCategory(categoryId, page); List<Community>
	 * result = resultPage.getContent(); int pagenum = resultPage.getTotalPages();
	 * if (result != null) model.addAttribute("communities", result);
	 * model.addAttribute("pagenum", pagenum); return "community"; }// 카테고리 별 글목록
	 */
	@RequestMapping("/detail.do")
	public String viewDetail(HttpServletRequest request, @RequestParam("commId") int communityId, ModelMap model) throws Exception {
		/*
		 * Community comm = communityService.getComm(communityId); List<Comments>
		 * comments = communityService.findCommentByCommunity(comm); boolean isMine;
		 * UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request,
		 * "userSession"); if(userSession == null) isMine = false; else
		 * if(userSession.getMember().equals(comm.getMember())) { isMine = true; } else
		 * { isMine = false; } int updateView = comm.getViews() + 1;
		 * comm.setViews(updateView); communityService.updateCommunity(comm);//view
		 * update
		 * 
		 * model.addAttribute("Post", comm); model.addAttribute("Comments", comments);
		 * model.addAttribute("isMine", isMine);
		 */
		return "thymeleaf/communityDetail";
	}//커뮤니티 상세 페이지
	
	@RequestMapping("/uploadCommunity.do")
	public String form(HttpServletRequest request) {
		/*
		 * //로그인 확인 UserSession userSession = (UserSession)
		 * WebUtils.getSessionAttribute(request, "userSession"); if(userSession == null)
		 * return "login"; else { return "createCommunity"; }
		 */
		return "thymeleaf/communityWrite";
	}//글 작성-forwarding
	
	@RequestMapping("/updateCommunity.do")
	public String form2(HttpServletRequest request,@RequestParam("cid") int cid,Model model) {
		//로그인 확인
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if(userSession == null)
			return "login";
		else {
			Community comm = communityService.getComm(cid);
			model.addAttribute("Post", comm);
			return "updateCommunity";
		}
	}//글 수정-forwarding
	
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
		LocalDateTime now = LocalDateTime.now();
		Timestamp upTime = Timestamp.valueOf(now);//현재 시간 가져오기
		comm.setUpload_date(upTime);
		comm.setViews(0);
		int communityId = communityService.createCommunity(comm);
		redirectAttributes.addAttribute("commId", communityId);
		return "redirect:/book/community/detail.do";
	}//글 작성-나중에 입력값 검증 넣어야(걍 form에서 못넘어가게해도 될거같은디)
	
	@RequestMapping("/update.do")
	public String updateCommunity(HttpServletRequest request, @RequestParam("title") String title,@RequestParam("category") int category,@RequestParam("content") String content, @RequestParam("cid") int cid, RedirectAttributes redirectAttributes) {
		Community comm = communityService.getComm(cid);
		comm.setCategory(category);
		comm.setTitle(title);
		comm.setContent(content);
		/*
		 * UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request,
		 * "userSession"); Member member = userSession.getMember();
		 * comm.setMember(member); LocalDateTime now = LocalDateTime.now(); Timestamp
		 * upTime = Timestamp.valueOf(now);//현재 시간 가져오기 comm.setUpload_date(upTime);
		 * comm.setViews(0);
		 */
		int communityId = communityService.updateCommunity(comm);
		redirectAttributes.addAttribute("commId", communityId);
		return "redirect:/book/community/detail.do";
	}//글 작성-나중에 입력값 검증 넣어야(걍 form에서 못넘어가게해도 될거같은디)
	
	@RequestMapping("/createComment.do")
	public String createComment(HttpServletRequest request,HttpServletResponse response,@RequestParam("commId") int cid,@RequestParam("content") String content,  RedirectAttributes redirectAttributes) throws IOException {
		//로그인 확인
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		if(userSession == null) {
			response.setContentType("text/html;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다.');</script>");
			out.println("<script>history.back();</script>");
			out.flush();
		}//로그인 필요 시 alert
		
		Member member = userSession.getMember();//userSession으로부터 멤버 객체 가져오기
		Community comm = communityService.getComm(cid);
		Comments comment = new Comments();
		comment.setCommunity(comm);
		comment.setContent(content);
		comment.setMember(member);
		LocalDateTime now = LocalDateTime.now();
		Timestamp upTime = Timestamp.valueOf(now);//현재 시간 가져오기
		comment.setUpload_date(upTime);
		communityService.createComment(comment);
		
		redirectAttributes.addAttribute("commId", cid);
		
		return "redirect:detail.do";
	}//댓글 작성-로그인 확인 넣기
	
	@RequestMapping("/delete.do")
	public String deleteCommunity(HttpServletRequest request, HttpServletResponse response, @RequestParam("cid") int cid) throws IOException {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if(userSession == null) {
			response.setContentType("text/html;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');</script>");
			out.println("<script>history.back();</script>");
			out.flush();
		}
		Community comm = communityService.getComm(cid);
		if(userSession.getMember().equals(comm.getMember())) {//세션이랑 작성자 일치하는지 확인
		communityService.deleteCommunity(comm,cid);
		}
		String uri ="redirect:view/0.do";
		return uri;
	}//글 삭제
	
	@RequestMapping("/search.do")
	public String searchCommunity(@RequestParam("keyWord") String keyWord, ModelMap model)
			throws Exception {
		List<Community> result = communityService.findCommunityByKeyword(keyWord);
		if (result != null)
			model.addAttribute("communities", result);
		return "community";
	}// 전체 community글목록

}
