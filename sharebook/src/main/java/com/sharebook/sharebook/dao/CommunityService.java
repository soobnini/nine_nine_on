package com.sharebook.sharebook.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Comment;
import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.repository.CommentRepository;
import com.sharebook.sharebook.repository.CommunityRepository;

@Service
public class CommunityService {
	@Autowired
	private CommunityRepository communityRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	

	public void setCommunityRepository(CommunityRepository communityRepository) {
		this.communityRepository = communityRepository;
	}
	
	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Transactional
	public void createCommunity(Community community) {
		communityRepository.save(community);// 글 생성
	}

	@Transactional
	public void updateCommunity(Community community) {
		communityRepository.save(community);// pk 설정시 자동으로 merge 실행(update)
	}
	
	@Transactional
	public void deleteCommunity(Community community) {
		communityRepository.delete(community);// delete
	}

	public Iterable<Community> getAllCommunity() {
		return communityRepository.findAll();
	}//전체 글 리스트

	public List<Community> findCommunityByTitle(String title) {
		return communityRepository.findByTitle(title);
	}//제목으로 찾기
	@Transactional
	public List<Community> findCommunityByKeyword(String Keyword) {
		return communityRepository.findByTitleContaining(Keyword);
	}//제목 키워드로 찾기
	
	public List<Community> findCommunityByUser(Member member) {
		return communityRepository.findByMember(member);
	}//작성자로 찾기
	
	public List<Community> findCommunityByCategory(int category) {
		return communityRepository.findByCategory(category);
	}//카테고리별 리스트
	
	public List<Comment> findCommentByCommunity(Community community) {
		return commentRepository.findByCommunity(community);
	}//댓글 리스트
	
	@Transactional
	public void createComment(Comment comment) {
		commentRepository.save(comment);
	}// 댓글 작성
}
