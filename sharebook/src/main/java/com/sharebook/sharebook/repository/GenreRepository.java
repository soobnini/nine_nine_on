package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
	/*
	 * Genre getById(int genreId);
	 */
}
