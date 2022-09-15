package com.sharebook.sharebook.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name="REGION")
@AllArgsConstructor 
@NoArgsConstructor
@Data
public class Region implements Serializable{
	@Id
	@Column(name="REGION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "REGION_SEQ")
	@SequenceGenerator(name="REGION_SEQ", allocationSize=1)
	private int region_id;
	@Column(name="NAME")
	private String name;
}
