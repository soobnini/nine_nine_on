package com.sharebook.sharebook.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

	List<Notice> findByTitle(String title) throws DataAccessException;
	List<Notice> findByMember(Member member) throws DataAccessException;
	
	Page<Notice> findAll(Pageable pagealbe)throws DataAccessException;
	Page<Notice> findByEndAfter(LocalDateTime now, Pageable pagealbe);//종료된 이벤트
	Page<Notice> findByStartAfterAndEndBefore(LocalDateTime now, LocalDateTime now2, Pageable pagealbe);
	List<Notice> findByTitleContaining(@Param("keyWord") String keyword) throws DataAccessException;//키워드검색

}
