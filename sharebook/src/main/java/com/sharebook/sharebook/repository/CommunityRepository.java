package com.sharebook.sharebook.repository;

import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;

public interface CommunityRepository extends JpaRepository<Community, Integer>{
	List<Community> findByTitle(String title) throws DataAccessException;
	List<Community> findByMember(Member member) throws DataAccessException;
	List<Community> findByCategory(int category) throws DataAccessException;
	
	Page<Community> findAll(Pageable pagealbe)throws DataAccessException;
	Page<Community> findAllByCategory(int category,Pageable pagealbe)throws DataAccessException;
	
	List<Community> findByTitleContaining(@Param("keyWord") String keyword) throws DataAccessException;//키워드검색
}
