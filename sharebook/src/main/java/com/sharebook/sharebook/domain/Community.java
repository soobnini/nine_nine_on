package com.sharebook.sharebook.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@SuppressWarnings("serial")
@Entity
@Table(name="Member")
@Data
public class Community implements Serializable{
	@Id
	private int community_id;
	private String title;
	public int category;
	private String content;
	private int views;
	private Date upload_date;
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	/*댓글 리스트
	 * @OneToMany(mappedBy="community") private List<Comment> commentList;
	 */
	public Community() {}//기본 생성자
}
