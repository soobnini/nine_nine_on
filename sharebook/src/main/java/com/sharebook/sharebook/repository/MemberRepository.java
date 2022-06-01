package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {
	List<Member> findByAddress1AndAddress2(String address1,String address2) throws DataAccessException;
}
