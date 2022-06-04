package com.sharebook.sharebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	/*
	 * CRUDRepository Method 
	 */
	public Book saveBook(Book book) {
		// Update 시에도 사용!
		return bookRepository.save(book);
	}
	
	public void deleteBook(int book_id) {
		bookRepository.deleteById(book_id);
		return;
	}
	
	/*
	 * Book Return Method 
	 */
	public Book findBookByTitle(String title) {
		Optional<Book> book = bookRepository.findBookByTitle(title);
		if(book.isPresent())
			return book.get();
		return null;
	}
	
	public Book findBookByAuthor(String author) {
		Optional<Book> book = bookRepository.findBookByTitle(author);
		if(book.isPresent())
			return book.get();
		return null;
	}
	
	/*
	 * List Return Method 
	 */
	public List<Book> findBookList(){
		return bookRepository.findBook();
	}
	
	public List<Book> findBookListByMember(int member_id){
		return bookRepository.findBookByMember_id(member_id);
	}
}
