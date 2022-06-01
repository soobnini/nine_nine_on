package com.sharebook.sharebook.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Message {
	@Id
	int message_id;
	Date sent_time;
	String content;
	
	//	메시지 전송자(FK)
	@ManyToOne
	@JoinColumn(name="member_id")
	int member_id;
	//	메시지가 전송된 채팅방 id(FK)
	@ManyToOne
	@JoinColumn(name="chat_room_id")
	int chat_room_id;
}
