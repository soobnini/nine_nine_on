package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="FUNDING_ORDER")
public class Funding_order {
	@Id
	@Column(name="FUNDING_ORDER_ID")
	private int funding_order_id;
	
	@ManyToOne
	@JoinColumn(name="funding_id")
	private Funding funding; // PK
	
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member; // FK
	
	@ManyToOne
	@JoinColumn(name="reward_id")
	private Reward reward; // FK
}
