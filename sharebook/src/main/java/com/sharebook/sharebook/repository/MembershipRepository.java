package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Membership;

public interface MembershipRepository extends CrudRepository<Membership, Integer> {
	/*
	 * List Return Method 
	 */
	@Query("SELECT m FROM membership m WHERE chat_room_id="
			+ "(SELECT chat_room_id FROM membership WHERE member_id=?1)")
	List<Membership> findMembershipByMember_id(int member_id);
}
