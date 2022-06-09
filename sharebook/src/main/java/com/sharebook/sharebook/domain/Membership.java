package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
class MembershipPK {
	//	참여한 채팅방의 id (FK)
	@ManyToOne
	@JoinColumn(name="CHAT_ROOM_ID")
	int chat_room_id;
	
	//	참여자의 id (FK)
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	int member_id;
}

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="MEMBERSHIP")
public class Membership {
	@EmbeddedId
	@Column(name="MEMBERSHIP_ID")
	MembershipPK id;
}
