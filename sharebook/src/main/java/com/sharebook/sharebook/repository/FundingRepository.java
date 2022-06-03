package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Funding;

public interface FundingRepository extends JpaRepository<Funding, Integer> {
	List<Funding> findByTitle(String title);
	
	List<Funding> findByAuthor(String author);
	
	List<Funding> findByMember_id(int member_id);
}
