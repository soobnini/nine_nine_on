package com.sharebook.sharebook.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Table(name="NOTICE")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Notice implements Serializable{
	@Id
	@Column(name="NOTICE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "NOTICE_SEQ")
	@SequenceGenerator(name="NOTICE_SEQ", allocationSize=1)
	private int noticeId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="START_DAY")
	private Date start;
	
	@Column(name="END_DAY")
	private Date end;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="VIEWS")
	private int views;
	
	@Column(name="UPLOAD_DATE")
	private Date upload_date;
	
	@Column(name="IMAGE")
	private String image;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	@Column(name="ISPIN")
	private int ispin;
	
	@Column(name="CATEGORY")
	public int category;
}
