package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {
	List<Rent> findByMember(Member member);
}
