package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Funding_order;
import com.sharebook.sharebook.domain.Member;

public interface Funding_orderRepository extends JpaRepository<Funding_order, Integer> {
	List<Funding_order> findByFunding(Funding funding);
	
	List<Funding_order> findByMember(Member member);
}
