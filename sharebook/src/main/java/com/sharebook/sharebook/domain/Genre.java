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
@Table(name="GENRE")
@AllArgsConstructor 
@NoArgsConstructor
@Data
public class Genre implements Serializable{
	@Id
	@Column(name="GENRE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "GENRE_SEQ")
	@SequenceGenerator(name="GENRE_SEQ", allocationSize=1)
	private int genreId;
	@Column(name="NAME")
	private String name;
}
