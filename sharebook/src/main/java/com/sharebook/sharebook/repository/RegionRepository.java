package com.sharebook.sharebook.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sharebook.sharebook.domain.Region;

public interface RegionRepository extends PagingAndSortingRepository<Region, Integer> {
	/*
	 * Store Return Method 
	 */
	Optional<Region> findRegionByName(String name);
	
	/*
	 * List Return Method 
	 */
}
