package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	/*
	 * Book Return Method 
	 */
	Optional<Book> findBookByTitle(String title);
	Optional<Book> findBookByAuthor(String author);
	
	/*
	 * List Return Method 
	 */
	List<Book> findBook();
	List<Book> findBookByMember_id(int member_id);
}
