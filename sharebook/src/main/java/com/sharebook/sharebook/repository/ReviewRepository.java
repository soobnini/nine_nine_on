package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Review;

public interface ReviewRepository  extends JpaRepository<Review, Integer> {
	List<Review> findByMember(Member member) throws DataAccessException;//멤버별 독후감찾기
	Page<Review> findAll(Pageable pagealbe)throws DataAccessException;
	Page<Review> findByTitleContainingIgnoreCase(@Param("keyWord") String title, Pageable pagealbe) throws DataAccessException;//키워드검색
}
