package com.sharebook.sharebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Rent {
	private int book_id; // Pk
	private String title;
	private String author;
	private String image;
	private String description;
	private int views;
	private int available;
	private int member_id; // FK
}
