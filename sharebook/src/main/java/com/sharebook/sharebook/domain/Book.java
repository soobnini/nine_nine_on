package com.sharebook.sharebook.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	int book_id;
	String title;
	String author;
	String image;
	String description;
	int views;
	boolean available;
	
	//	책 등록한 사람 (FK)
	@ManyToOne
	@JoinColumn(name="member_id")
	int member_id;
}
