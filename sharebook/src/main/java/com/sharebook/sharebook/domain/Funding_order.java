package com.sharebook.sharebook.domain;

import java.io.Serializable;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Embeddable
@EqualsAndHashCode
class Funding_orderPK implements Serializable {
	private int funding_id; // PK

	private int member_id; // FK
	
	private int reward_id; // FK
}

@Data
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Table(name="FUNDING_ORDER")
public class Funding_order {
	@EmbeddedId
	private Funding_orderPK id;
}
