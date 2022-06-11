package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Likes;
import com.sharebook.sharebook.domain.Member;

public interface LikesRepository extends CrudRepository<Likes, Integer> {
	/*
	 * Likes Return Method 
	 */
	Optional<Likes> findByMemberAndBook(Member member, Book book);
	/*
	 * List Return Method 
	 */
	List<Likes> findAllByMember(Member member);
	List<Likes> findAllByBook(Book book);
}
