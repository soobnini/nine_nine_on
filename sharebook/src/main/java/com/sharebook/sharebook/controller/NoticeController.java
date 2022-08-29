package com.sharebook.sharebook.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.sharebook.sharebook.domain.Notice;
import com.sharebook.sharebook.service.NoticeService;

@Controller
@RequestMapping("/book/Notice")
public class NoticeController implements ApplicationContextAware {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/List.do")
	public String communityList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "searchText", required = false) String searchText, ModelMap model) throws Exception {
		Page<Notice> resultPage = null;
		List<Notice> result = null;
		int totalPage = 0;
		/*  검색 */
		if (searchText != null) {
			resultPage = noticeService.getAllNotice(page);
		}
		/* 전체글조회 */
		else {
			resultPage = noticeService.searchNotice(page, searchText);
		}

		result = resultPage.getContent();
		totalPage = resultPage.getTotalPages();
		if (result != null)
			model.addAttribute("noticeList", result);
		model.addAttribute("totalPage", totalPage);
		return "notice";
	}
	
	@RequestMapping("/detail.do")
	public String viewDetail(HttpServletRequest request, @RequestParam("noticeId") int noticeId, ModelMap model) throws Exception {
		Notice notice = noticeService.getNotice(noticeId);
		//List<Comments> comments = noticeService.findCommentByCommunity(notice);**Notice에도 댓글있음???
		int updateView = notice.getViews() + 1;
		notice.setViews(updateView);
		noticeService.updateNotice(notice);//view update
		
		model.addAttribute("Notice", notice);
		return "detailNotice";
	}//상세 페이지
	
	@GetMapping("/uploadNotice.do")
	public String form(HttpServletRequest request) {
		//로그인 확인, 관리자인지 확인
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if(userSession == null && userSession.getMember().getAdmin() == 0)
			return "login";
		else {
			return "createCommunity";
		}
	}//글 작성-forwarding
	@PostMapping("/uploadNotice.do")
	public String uploadCommunity(HttpServletRequest request, @RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("end") LocalDateTime end,@RequestParam("start") LocalDateTime start, MultipartFile image,RedirectAttributes redirectAttributes) {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		if(userSession == null || userSession.getMember().getAdmin() == 0)
			return "login";//관리자 아니면 login화면으로
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp upTime = Timestamp.valueOf(now);//현재 시간 가져오기
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setUpload_date(upTime);
		notice.setStart(Timestamp.valueOf(start));
		notice.setEnd(Timestamp.valueOf(end));
		notice.setMember(userSession.getMember());
		notice.setImage(uploadFile(image));//TODO:: 이미지 업로드, 위지윅 에디터 방식으로..?
		notice.setViews(0);
		
		int noticeId = noticeService.createNotice(notice);
		redirectAttributes.addAttribute("noticeId", noticeId);
		return "redirect:/book/Notice/detail.do";
	}//글 작성
	
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
	
	private String uploadFile(MultipartFile image) {
		String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
		System.out.println(this.uploadDir + filename + " 업로드");

		File file = new File(this.uploadDir + filename);

		try {
			image.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return filename;//TODO:: 그냥 로컬Dir경로 합쳐서 string 으로 해주면 안될려나
	}
}
