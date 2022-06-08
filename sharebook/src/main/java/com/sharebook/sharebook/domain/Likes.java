package com.sharebook.sharebook.domain;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
class LikesPK {
	@ManyToOne
	@JoinColumn(name="member_id")
	private int member_id;
	@ManyToOne
	@JoinColumn(name="book_id")
	private int book_id;
}

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Likes {
	@EmbeddedId
	private LikesPK id;
}
