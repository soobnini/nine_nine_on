package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CHAT_ROOM_SEQ")
	@SequenceGenerator(name="CHAT_ROOM_SEQ", allocationSize=1)
	int chat_room_id;
}
