package com.sharebook.sharebook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="BOOK")
public class Book {
	@Id
	@Column(name="BOOK_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "BOOK_SEQ")
	@SequenceGenerator(name="BOOK_SEQ", allocationSize=1)
	int bookId;
	
	@Column(name="TITLE")
	String title;
	
	@Column(name="AUTHOR")
	String author;
	
	@Column(name="PUBLISHER")
	String publisher;
	
	@Column(name="IMAGE")
	String image;
	
	@Column(name="DESCRIPTION")
	String description;
	
	@Column(name="INTRODUCE")
	String introduce;
	
	@Column(name="ISBN")
	String isbn;
	
	@Column(name="PUBLISH_YEAR")
	String publishYear;
	
	@Column(name="VIEWS")
	int views;
	
	@Column(name="AVAILABLE")
	boolean available;
	
	//	책 등록한 사람 (FK)
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	Member member;
	
	@ManyToOne
	@JoinColumn(name="GENRE_ID")
	Genre genre;
	
	@ManyToOne
	@JoinColumn(name="STORE_ID")
	Store store;
}
