package com.sharebook.sharebook.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Review;
import com.sharebook.sharebook.repository.ReviewRepository;
@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional
	public int createReview(Review review) {
		return reviewRepository.save(review).getReviewId();
	}// 독후감 작성

	@Transactional
	public int updateReview(Review review) {
		return reviewRepository.save(review).getReviewId();
	}// 독후감 수정

	@Transactional
	public void deleteReview(Review review) {
		reviewRepository.delete(review);
	}// 독후감 삭제

	public Review getReview(int reviewId) {
		Review review = null;
		review = reviewRepository.getById(reviewId);
		return review;
	}
	
	public List<Review> getReviewByMember(Member member) {
		return (List<Review>) reviewRepository.findByMember(member);
	}

	public Page<Review> getAllReview(int preq, int orderBy) {
		Pageable p = null;
		if (orderBy == 1) {
			p = PageRequest.of(preq, 6, Sort.by("reviewId").descending());
		} 
		else if (orderBy == 2) {
			p = PageRequest.of(preq, 6, Sort.by("reviewId").ascending());
		} 
		else if (orderBy == 3) {
			p = PageRequest.of(preq, 6, Sort.by("views").descending());
		}
		else {
			p = PageRequest.of(preq, 6, Sort.by("reviewId").descending());
		}
		Page<Review> reviewPage = reviewRepository.findAll(p);
		return reviewPage;
	}// 전체 목록
	
	public Page<Review> getSearchReview(int preq, String keyWord, int orderBy) {
		Pageable p = null;
		if (orderBy == 1) {
			p = PageRequest.of(preq, 6, Sort.by("reviewId").descending());
		} 
		else if (orderBy == 2) {
			p = PageRequest.of(preq, 6, Sort.by("reviewId").ascending());
		} 
		else if (orderBy == 3) {
			p = PageRequest.of(preq, 6, Sort.by("views").descending());
		}
		else {
			p = PageRequest.of(preq, 6, Sort.by("reviewId").descending());
		}
		Page<Review> reviewPage = reviewRepository.findByTitleContainingIgnoreCase(keyWord,p);
		return reviewPage;
	}// 검색 목록
	
	public List<Review> findRecommendReview(){
		return (List<Review>) reviewRepository.findFirst4ByOrderByViewsDesc();
	}
	
}
