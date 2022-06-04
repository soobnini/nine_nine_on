package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
	/*
	 * List Return Method 
	 */
	@Query("SELECT m FROM message m WHERE m.chat_room_id="
			+ "(SELECT chat_room_id FROM membership WHERE member_id=?1)")
	List<Message> findMessageByMember_id(int member_id);
}
