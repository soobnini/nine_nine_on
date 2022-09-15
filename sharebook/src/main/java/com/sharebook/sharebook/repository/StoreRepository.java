package com.sharebook.sharebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sharebook.sharebook.domain.Region;
import com.sharebook.sharebook.domain.Store;

public interface StoreRepository extends PagingAndSortingRepository<Store, Integer> {
	/*
	 * Store Return Method 
	 */
	Optional<Store> findStoreByName(String name);
	
	/*
	 * List Return Method 
	 */
	List<Store> findAllByRegion(Region region);
}
