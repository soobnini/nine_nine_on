package com.sharebook.sharebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Chat_room;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Membership;
import com.sharebook.sharebook.domain.Message;
import com.sharebook.sharebook.repository.Chat_roomRepository;
import com.sharebook.sharebook.repository.MembershipRepository;
import com.sharebook.sharebook.repository.MessageRepository;

@Service
public class Chat_roomService {
	@Autowired
	public Chat_roomRepository chat_roomRepository;
	@Autowired
	public MembershipRepository membershipRepository;
	@Autowired
	public MessageRepository messageRepository;
	
	public void setChat_roomService(Chat_roomRepository chat_roomRepository,
			MembershipRepository membershipRepository, MessageRepository messageRepository) {
		this.chat_roomRepository = chat_roomRepository;
		this.membershipRepository = membershipRepository;
		this.messageRepository = messageRepository;
	}
	
	/*
	 * CRUDRepository Method 
	 */
	public Chat_room saveChatRoom(Chat_room chat_room) {
		return chat_roomRepository.save(chat_room);
	}
	
	public Membership saveMembership(Membership membership) {
		return membershipRepository.save(membership);
	}
	
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}
	
	/*
	 * find Method
	 */
	public Chat_room findChatRoomById(int chat_room_id) {
		Optional<Chat_room> chat_room = chat_roomRepository.findById(chat_room_id);
		if(chat_room.isPresent()) {
			 return chat_room.get();
		}
		return null;
	}
	
	public Membership findMembershipByMember(Member member) {
		Optional<Membership> membership = membershipRepository.findByMember(member);
		if(membership.isPresent()) {
			 return membership.get();
		}
		return null;
	}
	
	/*
	 * List Return Method 
	 */
	public List<Membership> findMembershipListByMember(Member member){
		return membershipRepository.findAllByMember(member);
	}
	
	public List<Membership> findMembershipListByChatRoom(Chat_room chat_room){
		return membershipRepository.findAllByChatRoom(chat_room);
	}
	
	public List<Message> findMessageListByMember(Member member){
		return messageRepository.findAllByMember(member);
	}
	
	public List<Message> findMessageListByMemberAndChatRoom(Member member, Chat_room chat_room){
		return messageRepository.findAllByMemberAndChatRoom(member, chat_room);
	}
}
