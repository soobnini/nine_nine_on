package com.sharebook.sharebook.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Chat_room;
import com.sharebook.sharebook.domain.Membership;
import com.sharebook.sharebook.domain.Message;

public interface Chat_roomRepository extends CrudRepository<Chat_room, Integer>{
	/*
	 * Insert
	 * SaveChatRoom은 기본 제공되는 save() 사용
	 */
	@Modifying
	@Query(value = "INSERT INTO membership VALUES (chat_room_id, member_id)", nativeQuery = true)
	void saveMembership(@Param("chat_room_id") int chat_room_id, @Param("member_id") int member_id);
	
	@Modifying
	@Query(value = "INSERT INTO message(sent_time, content, member_id, chat_room_id) "
			+ "VALUES (sent_time, content, member_id, chat_room_id)", nativeQuery = true)
	void saveMessage(@Param("sent_time") Date sent_time, @Param("content") String content,
			@Param("member_id") int member_id, @Param("chat_room_id") int chat_room_id);
	
	/*
	 * List Return Method 
	 */
	@Query("SELECT member_id FROM membership WHERE chat_room_id="
			+ "(SELECT chat_room_id FROM membership WHERE member_id=?1)")
	List<Membership> findMembershipByMember_id(int member_id);
	
	@Query("SELECT m FROM message m WHERE m.chat_room_id="
			+ "(SELECT chat_room_id FROM membership WHERE member_id=?1)")
	List<Message> findMessageByMember_id(int member_id);
}
