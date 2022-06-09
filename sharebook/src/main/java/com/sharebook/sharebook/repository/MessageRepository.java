package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
	/*
	 * List Return Method 
	 */
	List<Message> findAllByMember(Member member);
}
