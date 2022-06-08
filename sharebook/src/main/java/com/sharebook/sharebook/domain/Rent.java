package com.sharebook.sharebook.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Rent {
	@Id
	private int rent_id;
	private Date start_day;
	private Date end_day;
	@OneToOne
	@JoinColumn(name="book_id")
	private int book_id;
	@ManyToOne
	@JoinColumn(name="member_id")
	private int member_id;
}
