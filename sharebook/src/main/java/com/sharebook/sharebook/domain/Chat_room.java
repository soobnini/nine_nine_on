package com.sharebook.sharebook.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Chat_room {
	@Id
	int chat_room_id;
}
