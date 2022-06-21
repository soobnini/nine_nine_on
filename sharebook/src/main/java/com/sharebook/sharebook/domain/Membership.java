package com.sharebook.sharebook.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor 
@NoArgsConstructor
class MembershipPK implements Serializable {
	int chatRoom;
	int member;
}

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="MEMBERSHIP")
@IdClass(MembershipPK.class)
public class Membership {
	@Id
	@ManyToOne(targetEntity = Chat_room.class)
	@JoinColumn(name="CHAT_ROOM_ID")
	Chat_room chatRoom;
	@Id
	@ManyToOne(targetEntity = Member.class)
	@JoinColumn(name="MEMBER_ID")
	Member member;
}
