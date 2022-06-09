package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="CHAT_ROOM")
public class Chat_room {
	@Id
	@Column(name="CHAT_ROOM_ID")
	int chat_room_id;
}
