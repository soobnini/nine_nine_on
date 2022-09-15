package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sharebook.sharebook.domain.Chat_room;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {
	/*
	 * List Return Method 
	 */
	List<Message> findAllByChatRoom(Chat_room chat_room, Sort sort);
	List<Message> findAllByMember(Member member);
	List<Message> findAllByMemberAndChatRoom(Member member, Chat_room chat_room);
}
