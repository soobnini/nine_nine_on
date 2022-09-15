package com.sharebook.sharebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Region;
import com.sharebook.sharebook.domain.Store;
import com.sharebook.sharebook.repository.RegionRepository;
import com.sharebook.sharebook.repository.StoreRepository;

@Service
public class StoreService {
	@Autowired
	public StoreRepository storeRepository;
	@Autowired
	public RegionRepository regionRepository;
	
	public void setStoreService(StoreRepository storeRepository, RegionRepository regionRepository) {
		this.storeRepository = storeRepository;
		this.regionRepository = regionRepository;
	}
	
	public Region findRegionById(int regionId) {
		Optional<Region> region = regionRepository.findById(regionId);
		if(region.isPresent())
			return region.get();
		return null;
	}
	
	public Store findStoreById(int storeId) {
		Optional<Store> store = storeRepository.findById(storeId);
		if(store.isPresent())
			return store.get();
		return null;
	}
	
	public List<Region> findRegionList(){
		return (List<Region>)regionRepository.findAll();
	}
	
	public List<Store> findStoreList(){
		return (List<Store>)storeRepository.findAll();
	}
	
	public List<Store> findStoreListByRegion(Region region){
		return (List<Store>)storeRepository.findAllByRegion(region);
	}
}
