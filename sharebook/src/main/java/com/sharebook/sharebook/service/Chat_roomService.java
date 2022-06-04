package com.sharebook.sharebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Chat_room;
import com.sharebook.sharebook.domain.Membership;
import com.sharebook.sharebook.domain.Message;
import com.sharebook.sharebook.repository.Chat_roomRepository;
import com.sharebook.sharebook.repository.MembershipRepository;
import com.sharebook.sharebook.repository.MessageRepository;

@Service
public class Chat_roomService {
	@Autowired
	private Chat_roomRepository chat_roomRepository;
	@Autowired
	private MembershipRepository membershipRepository;
	@Autowired
	private MessageRepository messageRepository;
	
	public void setChat_roomService(Chat_roomRepository chat_roomRepository,
			MembershipRepository membershipRepository, MessageRepository messageRepository) {
		this.chat_roomRepository = chat_roomRepository;
		this.membershipRepository = membershipRepository;
		this.messageRepository = messageRepository;
	}
	
	/*
	 * CRUDRepository Method 
	 */
	public Chat_room saveChat_room(Chat_room chat_room) {
		return chat_roomRepository.save(chat_room);
	}
	
	public Membership saveMembership(Membership membership) {
		return membershipRepository.save(membership);
	}
	
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}
	
	/*
	 * List Return Method 
	 */
	public List<Membership> findMembershipListByMember(int member_id){
		return membershipRepository.findMembershipByMember_id(member_id);
	}
	
	public List<Message> findMessageListByMember(int member_id){
		return messageRepository.findMessageByMember_id(member_id);
	}
}
