package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Comments;
import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;

public interface CommentsRepository extends CrudRepository<Comments, Integer>{
	List<Comments> findAllByMember(Member member)throws DataAccessException;//작성자별
	List<Comments> findAllByCommunity(Community community)throws DataAccessException;
}
