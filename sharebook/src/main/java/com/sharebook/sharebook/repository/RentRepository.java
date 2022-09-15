package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {
	Optional<Rent> findByBook(Book book);
	List<Rent> findByMember(Member member);
	List<Rent> findAllByBook_Member(Member member);
}
