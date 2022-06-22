package com.sharebook.sharebook.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="RENT")
public class Rent {
	@Id
	@Column(name="RENT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "RENT_SEQ")
	@SequenceGenerator(name="RENT_SEQ", allocationSize=1)
	private int rent_id;
	
	@Column(name="START_DAY")
	private Date start_day;
	
	@Column(name="END_DAY")
	private Date end_day;
	
	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
}
