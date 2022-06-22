package com.sharebook.sharebook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name="FUNDING_SEQ_GENERATOR",
					 sequenceName="FUNDING_SEQ",
					 initialValue=1, allocationSize=1)
@Table(name="FUNDING")
public class Funding implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            		generator="FUNDING_SEQ_GENERATOR")
	@Column(name="FUNDING_ID")
	private int funding_id;	// PK

	@Column(name="TITLE")
	private String title;

	@Column(name="AUTHOR")
	private String author;

	@Column(name="IMAGE")
	private String image;
	
	public String multipartFileIsNotNull(MultipartFile file) {
		if (file != null) {
			return "image";
		}
		return null;
	}

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="VIEWS")
	private int views;

	@Column(name="GOAL_AMOUNT")
	private int goal_amount;

	@Column(name="DEADLINE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadline;

	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
}
