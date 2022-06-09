package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Membership;

public interface MembershipRepository extends CrudRepository<Membership, Integer> {
	/*
	 * List Return Method 
	 */
	List<Membership> findAllByMember(Member member);
}
