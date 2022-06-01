package com.sharebook.sharebook.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;

public interface CommunityRepository extends CrudRepository<Community, Integer>{
	List<Community> findByTitle(String address1,String address2) throws DataAccessException;
	List<Community> findByMember(Member member) throws DataAccessException;
	List<Community> findByCategory(int category) throws DataAccessException;
}
