package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
class Funding_orderPK {
	@ManyToOne
	@JoinColumn(name="FUNDING_ID")
	private int funding_id; // PK
	
	@OneToOne
	@JoinColumn(name="MEMBER_ID")
	private int member_id; // FK
	
	@ManyToOne
	@JoinColumn(name="REWARD_ID")
	private int reward_id; // FK
}

@Data
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Table(name="FUNDING_ORDER")
public class Funding_order {
	@EmbeddedId
	@Column(name="FUNDING_ORDER_ID")
	private Funding_orderPK id;
}
