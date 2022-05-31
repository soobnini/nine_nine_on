package com.sharebook.sharebook.dao;

import org.springframework.dao.DataAccessException;

import com.sharebook.sharebook.domain.Rent;

import java.util.List;

public interface RentDAO {

	void create(Rent rent) throws DataAccessException;
	
	void delete(int rent_id) throws DataAccessException;
	
	List<Rent> findRentList(int user_id) throws DataAccessException;

}
