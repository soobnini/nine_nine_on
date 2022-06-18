package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Likes_funding;
import com.sharebook.sharebook.domain.Member;

public interface Likes_fundingRepository extends JpaRepository<Likes_funding, Integer> {
	Likes_funding findByFundingAndMember(Funding funding, Member member);
	
	List<Likes_funding> findByFunding(Funding funding);
	
	List<Likes_funding> findByMember(Member member);
}
