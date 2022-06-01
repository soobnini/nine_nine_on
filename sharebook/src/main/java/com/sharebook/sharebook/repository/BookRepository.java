package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	/*
	 * Book Return Method 
	 */
	Book findBookByTitle(String title);
	Book findBookByAuthor(String author);
	
	/*
	 * List Return Method 
	 */
	List<Book> findBook();
	List<Book> findBookByMember_id(int member_id);
}
