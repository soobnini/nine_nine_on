package com.sharebook.sharebook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

	public void setChat_roomService(Chat_roomRepository chat_roomRepository, MembershipRepository membershipRepository,
			MessageRepository messageRepository) {
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
		if (chat_room.isPresent()) {
			return chat_room.get();
		}
		return null;
	}

	public Membership findMembershipByMember(Member member) {
		Optional<Membership> membership = membershipRepository.findByMember(member);
		if (membership.isPresent()) {
			return membership.get();
		}
		return null;
	}

	public Chat_room findChatRoom(Member member, Member otherMember) {
		List<Membership> membership = findMembershipListByMember(member);
		List<Membership> otherMembership = findMembershipListByMember(otherMember);

		Chat_room chatRoom = null;
		for (int i = 0; i < membership.size(); i++) {
			Chat_room nowChatRoom = membership.get(i).getChatRoom();
			for (int j = 0; j < otherMembership.size(); j++) {
				if (nowChatRoom.equals(otherMembership.get(j).getChatRoom())) {
					chatRoom = nowChatRoom;
				}
			}
		}

		return chatRoom;
	}

	public List<Membership> findMembershipList(Member member) {
		List<Membership> membershipList = findMembershipListByMember(member);

		List<Membership> otherMembershipList = new ArrayList<>();
		for (int i = 0; i < membershipList.size(); i++) {
			otherMembershipList
					.add(findOtherParticipation(member, membershipList.get(i).getChatRoom()));
		}
		
		System.out.println(otherMembershipList.get(0).getMember().getNickname());
		
		return otherMembershipList;
	}

	public Membership findOtherParticipation(Member member, Chat_room room) {
		Membership otherMembership = new Membership();

		List<Membership> membershipList = findMembershipListByChatRoom(room);
		for (int i = 0; i < 2; i++) {
			if (membershipList.get(i).getMember().getMember_id() != member.getMember_id()) {
				otherMembership = membershipList.get(i);
			}
		}

		return otherMembership;
	}

	/*
	 * List Return Method
	 */
	public List<Membership> findMembershipListByMember(Member member) {
		return membershipRepository.findAllByMember(member);
	}

	public List<Membership> findMembershipListByChatRoom(Chat_room chat_room) {
		return membershipRepository.findAllByChatRoom(chat_room);
	}

	public List<Message> findMessageListByChatRoom(Chat_room chat_room) {
		return messageRepository.findAllByChatRoom(chat_room, Sort.by(Sort.Direction.ASC, "messageId"));
	}

	public List<Message> findMessageListByMember(Member member) {
		return messageRepository.findAllByMember(member);
	}

	public List<Message> findMessageListByMemberAndChatRoom(Member member, Chat_room chat_room) {
		return messageRepository.findAllByMemberAndChatRoom(member, chat_room);
	}
}
