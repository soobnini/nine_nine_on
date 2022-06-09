package com.sharebook.sharebook.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Entity
@Table(name="REWARD")
public class Reward implements Serializable {
	@Id
	@Column(name="REWARD_ID")
	private int reward_id;	// PK

	@Column(name="PRICE")
	private int price;

	@Column(name="PRIZE")
	private String prize;
	
	@Column(name="IMAGE")
	private String image;

	@ManyToOne
	@JoinColumn(name="FUNDING_ID")
	@Column(name="FUNDING_ID")
	private int funding_id;	// FK
}
