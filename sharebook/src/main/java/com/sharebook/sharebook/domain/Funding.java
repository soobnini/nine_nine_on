package com.sharebook.sharebook.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Entity
@Table(name="FUNDING")
public class Funding implements Serializable {
	@Id
	@Column(name="FUNDING_ID")
	private int funding_id;	// PK

	@Column(name="TITLE")
	private String title;

	@Column(name="AUTHOR")
	private String author;

	@Column(name="IMAGE")
	private String image;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="VIEWS")
	private int views;

	@Column(name="GOAL_AMOUNT")
	private int goal_amount;

	@Column(name="DEADLINE")
	private Date deadline;

	@Column(name="MEMBER_ID")
	private int member_id;	// FK
}
