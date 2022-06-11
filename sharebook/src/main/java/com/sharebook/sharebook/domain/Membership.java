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
@EqualsAndHashCode
class MembershipPK implements Serializable {
	Chat_room chatRoom;
	Member member;
	
	public MembershipPK() {}
}

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="MEMBERSHIP")
@IdClass(MembershipPK.class)
public class Membership {
	@Id
	@ManyToOne
	@JoinColumn(name="CHAT_ROOM_ID")
	Chat_room chatRoom;
	@Id
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	Member member;
}
