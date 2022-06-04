package com.sharebook.sharebook.repository;

import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Chat_room;

public interface Chat_roomRepository extends CrudRepository<Chat_room, Integer>{
	/*
	 * 자동생성되는 save() 사용하기 때문에 비어있음
	 */
}
