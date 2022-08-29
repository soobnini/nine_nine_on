package com.sharebook.sharebook.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Notice;
import com.sharebook.sharebook.repository.NoticeRepository;

@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	public void setNoticeRepository(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}
	
	@Transactional
	public int createNotice(Notice notice) {
		return noticeRepository.save(notice).getNoticeId();
	}//공지 작성
	@Transactional
	public int updateNotice(Notice notice) {
		return noticeRepository.save(notice).getNoticeId();
	}//공지 수정
	@Transactional
	public void deleteNotice(Notice notice) {
		noticeRepository.delete(notice);
	}
	public Notice getNotice(int noticeId) {
		return noticeRepository.getById(noticeId);
	}//공지 삭제
	public Page<Notice> getAllNotice(int preq){
		Pageable p = PageRequest.of(preq, 10, Sort.by("noticeId").descending());
		Page<Notice> noticePage = noticeRepository.findAll(p);
		return noticePage;
	}//전체 공지
	public Page<Notice> searchNotice(int preq, String keyWord) {
		Page<Notice> noticePage = null;
			noticePage = noticeRepository
					.findByTitleContainingIgnoreCase(keyWord, PageRequest.of(preq, 10, Sort.by("noticeId").descending()));
		return noticePage;
	}// pagenation을 위한(전체 list에서 검색)
	/*
	 * public Page<Notice> getPastNotice(int preq){ Pageable p =
	 * PageRequest.of(preq, 10, Sort.by("noticeId").descending()); Page<Notice>
	 * pastNotice = noticeRepository.findByEndAfter(null, p); return pastNotice;
	 * }//종료된 공지
	 */
}
