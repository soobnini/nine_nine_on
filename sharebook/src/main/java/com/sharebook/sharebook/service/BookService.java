package com.sharebook.sharebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Likes;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.repository.BookRepository;
import com.sharebook.sharebook.repository.LikesRepository;

@Service
public class BookService {
	@Autowired
	public BookRepository bookRepository;
	@Autowired
	public LikesRepository likesRepository;
	
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
	
	public Likes saveLikes(Likes likes) {
		// Update 시에도 사용!
		return likesRepository.save(likes);
	}
	
	/* 나중에 수정 필요
	public void deleteLikes(int likes_id) {
		likesRepository.deleteById(likes_id);
		return;
	}
	*/
	
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
	
	public Likes findLikesByMemberAndBook(Member member, Book book) {
		Optional<Likes> likes = likesRepository.findByMemberAndBook(member, book);
		if(likes.isPresent())
			return likes.get();
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
	
	public List<Likes> findLikesListByMember(Member member){
		return likesRepository.findAllByMember(member);
	}
	
	public List<Likes> findLikesListByBook(Book book){
		return likesRepository.findAllByBook(book);
	}
	
}
