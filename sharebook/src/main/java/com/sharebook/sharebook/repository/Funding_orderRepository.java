package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Funding_order;

public interface Funding_orderRepository extends JpaRepository<Funding_order, Integer> {
	List<Funding_order> findByFunding_id(String funding_id);
	
	List<Funding_order> findByMember_id(String member_id);
}
