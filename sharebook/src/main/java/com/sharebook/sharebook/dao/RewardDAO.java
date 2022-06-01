package com.sharebook.sharebook.dao;

import org.springframework.dao.DataAccessException;

import com.sharebook.sharebook.domain.Reward;

import java.util.List;

public interface RewardDAO {

	void create(Reward reward) throws DataAccessException;
	
	void delete(int reward_id) throws DataAccessException;
	
	List<Reward> findRewardList(int funding_id) throws DataAccessException;

}
