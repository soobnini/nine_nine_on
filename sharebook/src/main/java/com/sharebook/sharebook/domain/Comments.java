package com.sharebook.sharebook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Table(name="COMMENTS")
@AllArgsConstructor 
@NoArgsConstructor
@Data
public class Comments implements Serializable{
	@Id
	@Column(name="COMMENTS_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "COMMENTS_SEQ")
	@SequenceGenerator(name="COMMENTS_SEQ", allocationSize=1)
	private int comment_id;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="UPLOAD_DATE")
	private Date upload_date;
	
	@ManyToOne
	@JoinColumn(name="COMMUNITY_ID")
	private Community community;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
}
