package com.sharebook.sharebook.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funding {
	private int funding_id;	// PK
	private String title;
	private String author;
	private String image;
	private String description;
	private int views;
	private int goal_amount;
	private Date deadline;
	private int member_id;	// FK
}
