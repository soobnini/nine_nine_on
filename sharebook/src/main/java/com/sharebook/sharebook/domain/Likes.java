package com.sharebook.sharebook.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@EqualsAndHashCode
class LikesPK implements Serializable {
//	private int member_id;
//	private int book_id;
	
	Member member;
	Book book;
	
	public LikesPK() { }
}

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="LIKES")
@IdClass(LikesPK.class)
public class Likes {
//	@Id private int member_id;
//	@Id private int book_id;
	
	@Id
	@ManyToOne
	@JoinColumn(name="member_id")
	Member member;
	
	@Id
	@ManyToOne
	@JoinColumn(name="book_id")
	Book book;
}
