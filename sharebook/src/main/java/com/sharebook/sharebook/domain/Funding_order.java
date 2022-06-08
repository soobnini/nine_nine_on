package com.sharebook.sharebook.domain;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
class Funding_orderPK {
	@ManyToOne
	@JoinColumn(name="funding_id")
	private int funding_id; // PK
	@OneToOne
	@JoinColumn(name="member_id")
	private int member_id; // FK
	@ManyToOne
	@JoinColumn(name="reward_id")
	private int reward_id; // FK
}

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Funding_order {
	@EmbeddedId
	private Funding_orderPK id;
}
