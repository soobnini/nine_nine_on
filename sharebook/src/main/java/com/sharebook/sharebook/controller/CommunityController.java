package com.sharebook.sharebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<Community> result = communityService.findPageCommunities(page);
		if (result != null)
			model.addAttribute("communities", result);
		return "community";
	}// 전체 community글목록

	@RequestMapping("/view/{categoryId}/{page}.do")
	public String viewCategoryCommunity(@PathVariable int categoryId, @PathVariable int page, ModelMap model)
			throws Exception {
		List<Community> result = communityService.findPageCommCategory(categoryId, page);
		if (result != null)
			model.addAttribute("communities", result);
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
	public String form() {
		//로그인 확인
		return "createCommunity";
	}//글 작성-forwarding
	
	@RequestMapping("/upload.do")
	public String uploadCommunity(@RequestParam("title") String title,@RequestParam("category") int category,@RequestParam("content") String content) {
		Community comm = new Community();
		comm.setCategory(category);
		comm.setTitle(title);
		comm.setContent(content);
		//comm.setMember(memeber);세션에서 가지고 오기
		//comm.setUpload_date(now);
		comm.setViews(0);
		int communityId = communityService.createCommunity(comm);
		String uri ="detail/"+ String.valueOf(communityId)+".do";
		return uri;
	}//글 작성-나중에 입력값 검증 넣어야(걍 form에서 못넘어가게해도 될거같은디)
	
	@RequestMapping("/createComment.do")
	public String createComment(@RequestParam("communityId") int cid,@RequestParam("content") String content) {
		//로그인 확인
		Community comm = communityService.getComm(cid);
		Comments comment = new Comments();
		comment.setCommunity(comm);
		comment.setContent(content);
		//comment.setMember(member);userseesion에서 가져오기
		//comment.setUpload_date(now);현재 시간으로 설정
		communityService.createComment(comment);
		return "redirect:detail/"+String.valueOf(cid);
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
