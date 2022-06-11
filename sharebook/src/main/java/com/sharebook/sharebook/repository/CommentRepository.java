package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.sharebook.sharebook.domain.Comment;
import com.sharebook.sharebook.domain.Community;
import com.sharebook.sharebook.domain.Member;

public interface CommentRepository extends CrudRepository<Comment, Integer>{
	List<Comment> findAllByMember(Member member)throws DataAccessException;//작성자별
	List<Comment> findAllByCommunity(Community community)throws DataAccessException;
}
