package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Reward;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
	List<Reward> findByFunding(Funding funding);
}
