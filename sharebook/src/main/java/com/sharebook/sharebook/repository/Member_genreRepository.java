package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Member_genre;

public interface Member_genreRepository extends CrudRepository<Member_genre, Integer> {
	List<Member_genre> findByMember(Member member);
	List<Member_genre> findByGenre(Genre gnere);
}