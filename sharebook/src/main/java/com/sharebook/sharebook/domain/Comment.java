package com.sharebook.sharebook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Table(name="Comment")
@AllArgsConstructor 
@NoArgsConstructor
@Data
public class Comment implements Serializable{
	@Id
	private int comment_id;
	private String content;
	private Date upload_date;
	@ManyToOne
	@JoinColumn(name="community_id")
	private Community community;
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
}
