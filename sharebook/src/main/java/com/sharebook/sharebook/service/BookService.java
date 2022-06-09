package com.sharebook.sharebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	public BookRepository bookRepository;
	
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
	public Book findBookById(int bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if(book.isPresent())
			return book.get();
		return null;
	}
	
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
		return (List<Book>)bookRepository.findAll();
	}
	
	public List<Book> findBookListByMember(Member member){
		return bookRepository.findAllByMember(member);
	}
}
