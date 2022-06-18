package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Member;

public interface FundingRepository extends JpaRepository<Funding, Integer> {
	List<Funding> findByTitleContaining(@Param("title") String title);
	
	List<Funding> findByAuthorContaining(@Param("author") String author);
	
	List<Funding> findByMember(Member member);
}
