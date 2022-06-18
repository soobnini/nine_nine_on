package com.sharebook.sharebook.domain;

import java.io.Serializable;

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

@AllArgsConstructor 
@NoArgsConstructor
@SuppressWarnings("serial")
@EqualsAndHashCode
class Likes_fundingPK implements Serializable {
	int member;
	int funding;
}

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="LIKES_FUNDING")
@IdClass(Likes_fundingPK.class)
public class Likes_funding {
	@Id
	@ManyToOne
	@JoinColumn(name="member_id")
	Member member;
	
	@Id
	@ManyToOne
	@JoinColumn(name="funding_id")
	Funding funding;
}
