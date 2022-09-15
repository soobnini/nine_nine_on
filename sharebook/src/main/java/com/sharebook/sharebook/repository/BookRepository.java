package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Genre;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Region;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
	/*
	 * Book Return Method 
	 */
	Optional<Book> findBookByTitle(String title);
	Optional<Book> findBookByAuthor(String author);
	
	/*
	 * List Return Method 
	 */
	List<Book> findAllByMember(Member member);
	
	List<Book> findAllByGenre(Genre genre);
	List<Book> findAllByGenre(Genre genre, Sort sort);
	
	List<Book> findFirst5ByOrderByViewsDesc();
	
	List<Book> findAllByMember_Address1AndMember_Address2(String address1, String address2);
	List<Book> findAllByStore_Region(Region Region);
	
	List<Book> findAllByTitleContaining(@Param("title")String title);
	List<Book> findAllByAuthorContaining(@Param("author")String author);
	
	List<Book> findAllByTitleContaining(@Param("title")String title, Sort sort);
	List<Book> findAllByAuthorContaining(@Param("author")String author, Sort sort);
}
