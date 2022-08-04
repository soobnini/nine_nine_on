package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Likes;
import com.sharebook.sharebook.domain.Member;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
	List<Genre> findByBook(Book book);
}
