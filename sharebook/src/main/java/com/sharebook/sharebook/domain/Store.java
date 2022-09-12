package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Table(name="STORE")
public class Store {
	@Id
	@Column(name="STORE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "STORE_SEQ")
	@SequenceGenerator(name="STORE_SEQ", allocationSize=1)
	int store_id;
	
	@Column(name="ADDRESS1")
	String address1;
	
	@Column(name="ADDRESS2")
	String address2;
	
	@Column(name="NAME")
	String name;
	
	@Column(name="DESCRIPTION")
	String description;
}
