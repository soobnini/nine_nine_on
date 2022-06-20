package com.sharebook.sharebook.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Comments;
import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.repository.CommentsRepository;
import com.sharebook.sharebook.repository.CommunityRepository;

@Service
public class CommunityService {
	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	public void setCommunityRepository(CommunityRepository communityRepository, CommentsRepository commentsRepository) {
		this.communityRepository = communityRepository;
		this.commentsRepository = commentsRepository;
	}


	@Transactional
	public int createCommunity(Community community) {
		return communityRepository.save(community).getCommunityId();// 글 생성
	}

	@Transactional
	public int updateCommunity(Community community) {
		return communityRepository.save(community).getCommunityId();// pk 설정시 자동으로 merge 실행(update)
	}
	
	@Transactional
	public void deleteCommunity(Community community, int cid) {
		commentsRepository.removeAllByCommunity(cid);
		communityRepository.delete(community);// delete
	}
	
	public Community getComm(int community_id) {
		return communityRepository.getById(community_id);
	}

	public Iterable<Community> getAllCommunity() {
		return communityRepository.findAll();
	}//전체 글 리스트
	
	public Page<Community> findPageCommunities(int preq){
		 Page<Community> communityPage = communityRepository.findAll(PageRequest.of(preq, 10, Sort.by("communityId").descending()));
			/* List<Community> posts = communityPage.getContent(); */
		return communityPage;
	}//pagenation을 위한
	
	public Page<Community> findPageCommCategory(int category, int preq){
		 Pageable p = PageRequest.of(preq, 10, Sort.by("communityId").descending());
		 Page<Community> communityPage = communityRepository.findAllByCategory(category, p);
		return communityPage;
	}//카테고리별pagenation을 위한
	

	public List<Community> findCommunityByTitle(String title) {
		return communityRepository.findByTitle(title);
	}//제목으로 찾기
	
	public List<Community> findCommunityByKeyword( String Keyword) {
		return communityRepository.findByTitleContaining(Keyword);
	}//제목 키워드로 찾기
	
	public List<Community> findCommunityByUser(Member member) {
		return communityRepository.findByMember(member);
	}//작성자로 찾기
	
	public List<Community> findCommunityByCategory(int category) {
		return communityRepository.findByCategory(category);
	}//카테고리별 리스트
	
	public List<Comments> findCommentByCommunity(Community community) {
		return commentsRepository.findAllByCommunity(community);
	}//댓글 리스트
	
	@Transactional
	public void createComment(Comments comment) {
		commentsRepository.save(comment);
	}// 댓글 작성
}
