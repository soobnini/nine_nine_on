package com.sharebook.sharebook.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Rent {
	private int rent_id;
	private Date start_day;
	private Date end_day;
	private int book_id;
	private int member_id;
}
