package com.sharebook.sharebook.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Embeddable
@EqualsAndHashCode
class MembershipPK implements Serializable {
	//	참여한 채팅방의 id (FK)
	int chat_room_id;
	
	//	참여자의 id (FK)
	int member_id;
}

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="MEMBERSHIP")
public class Membership {
	@EmbeddedId
	MembershipPK id;
}
