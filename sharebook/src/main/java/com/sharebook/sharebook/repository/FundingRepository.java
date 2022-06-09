package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Member;

public interface FundingRepository extends JpaRepository<Funding, Integer> {
	List<Funding> findByTitleContaining(String title);
	
	List<Funding> findByAuthorContaining(String author);
	
	List<Funding> findByMember(Member member);
}
