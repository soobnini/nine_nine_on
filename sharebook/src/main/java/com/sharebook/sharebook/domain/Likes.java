package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
class LikesPK {
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private int member_id;
	@ManyToOne
	@JoinColumn(name="BOOK_ID")
	private int book_id;
}

@Data
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Table(name="LIKES")
public class Likes {
	@EmbeddedId
	@Column(name="LIKES_ID")
	private LikesPK id;
}
