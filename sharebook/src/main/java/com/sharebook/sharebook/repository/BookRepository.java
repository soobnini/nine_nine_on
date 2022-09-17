package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Genre;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Region;
import com.sharebook.sharebook.domain.Review;

public interface BookRepository extends JpaRepository<Book, Integer> {
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

	List<Book> findAllByTitleContaining(@Param("title") String title);

	List<Book> findAllByAuthorContaining(@Param("author") String author);

	List<Book> findAllByTitleContaining(@Param("title") String title, Sort sort);

	List<Book> findAllByAuthorContaining(@Param("author") String author, Sort sort);

	/*
	 * Paging
	 */
	Page<Book> findAll(Pageable pagealbe) throws DataAccessException;

	Page<Book> findAllByTitleContaining(Pageable pagealbe, @Param("title") String title) throws DataAccessException;

	Page<Book> findAllByAuthorContaining(Pageable pagealbe, @Param("author") String author) throws DataAccessException;

	Page<Book> findAllByStore_Region(Pageable pageable, Region region) throws DataAccessException;

	Page<Book> findAllByGenre(Pageable pageable, Genre genre) throws DataAccessException;
}
