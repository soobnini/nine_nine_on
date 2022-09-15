package com.sharebook.sharebook.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="MESSAGE")
public class Message {
	@Id
	@Column(name="MESSAGE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MESSAGE_SEQ")
	@SequenceGenerator(name="MESSAGE_SEQ", allocationSize=1)
	int messageId;
	
	@Column(name="SENT_TIME")
	Date sent_time;
	
	@Column(name="CONTENT")
	String content;
	
	//	메시지 전송자(FK)
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	Member member;
	//	메시지가 전송된 채팅방 id(FK)
	@ManyToOne
	@JoinColumn(name="CHAT_ROOM_ID")
	Chat_room chatRoom;
}
