package com.sharebook.sharebook.domain;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
class MembershipPK {
	//	참여한 채팅방의 id (FK)
	@ManyToOne
	@JoinColumn(name="chat_room_id")
	int chat_room_id;
	//	참여자의 id (FK)
	@ManyToOne
	@JoinColumn(name="member_id")
	int member_id;
}

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Membership {
	@EmbeddedId
	MembershipPK id;
}
