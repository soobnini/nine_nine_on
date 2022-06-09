package com.sharebook.sharebook.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Table(name="COMMUNITY")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Community implements Serializable{
	@Id
	@Column(name="COMMUNITY_ID")
	private int community_id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CATEGORY")
	public int category;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="VIEWS")
	private int views;
	
	@Column(name="UPLOAD_DATE")
	private Date upload_date;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	/*댓글 리스트
	 * @OneToMany(mappedBy="community") private List<Comment> commentList;
	 */
}
