package com.sharebook.sharebook.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Embeddable
@EqualsAndHashCode
class LikesPK implements Serializable {
	private int member_id;

	private int book_id;
}

@Data
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Table(name="LIKES")
public class Likes {
	@EmbeddedId
	private LikesPK id;
}
