package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Chat_room;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Membership;

public interface MembershipRepository extends CrudRepository<Membership, Integer> {
	/*
	 * Chat_room Return Method
	 */
	Optional<Membership> findByMember(Member member);
	
	/*
	 * List Return Method 
	 */
	List<Membership> findAllByMember(Member member);
	List<Membership> findAllByChatRoom(Chat_room chat_room);
}
