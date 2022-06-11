package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@SequenceGenerator(name="FUNDING_ORDER_SEQ_GENERATOR",
					sequenceName="FUNDING_ORDER_SEQ",
					initialValue=1, allocationSize=1)
@Table(name="FUNDING_ORDER")
public class Funding_order {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="FUNDING_ORDER_SEQ_GENERATOR")
	@Column(name="FUNDING_ORDER_ID")
	private int funding_order_id; // PK
	
	@ManyToOne
	@JoinColumn(name="funding_id")
	private Funding funding; // FK
	
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member; // FK
	
	@ManyToOne
	@JoinColumn(name="reward_id")
	private Reward reward; // FK
}
