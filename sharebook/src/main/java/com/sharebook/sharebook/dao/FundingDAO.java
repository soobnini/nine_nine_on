package com.sharebook.sharebook.dao;

import org.springframework.dao.DataAccessException;

import com.sharebook.sharebook.domain.Funding;

import java.util.List;

public interface FundingDAO {

	void create(Funding funding) throws DataAccessException;
	
	void delete(int funding_id) throws DataAccessException;
	
	void update(int funding_id, Funding funding) throws DataAccessException;
	
	Funding findFundingById(int funding_id) throws DataAccessException;
	
	List<Funding> findFundingByTitle(String title) throws DataAccessException;
	
	List<Funding> findFundingByAuthor(String author) throws DataAccessException;
	
	List<Funding> findFundingList() throws DataAccessException;
	
	List<Funding> findFundingList(int user_id) throws DataAccessException;

}
