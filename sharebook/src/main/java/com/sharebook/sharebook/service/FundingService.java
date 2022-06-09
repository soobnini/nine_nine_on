package com.sharebook.sharebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Funding;
import com.sharebook.sharebook.domain.Funding_order;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Reward;
import com.sharebook.sharebook.repository.FundingRepository;
import com.sharebook.sharebook.repository.Funding_orderRepository;
import com.sharebook.sharebook.repository.RewardRepository;

@Service("fundingService")
public class FundingService {
	
	@Autowired
	public FundingRepository fundingRepository;
	
	@Autowired
	public Funding_orderRepository funding_orderRepository;
	
	@Autowired
	public RewardRepository rewardRepository;
	
	public void insertFunding(Funding funding) {
		fundingRepository.save(funding);
	}
	
	public void insertFunding_order(Funding_order funding_order) {
		funding_orderRepository.save(funding_order);
	}
	
	public void insertReward(Reward reward) {
		rewardRepository.save(reward);
	}
	
	public void updateFunding(Funding funding) {
		fundingRepository.save(funding);
	}
	
	public void updateFunding_order(Funding_order funding_order) {
		funding_orderRepository.save(funding_order);
	}
	
	public void updateReward(Reward reward) {
		rewardRepository.save(reward);
	}
	
	public void deleteFunding(Funding funding) {
		fundingRepository.delete(funding);
	}
	
	public void deleteFunding_order(Funding_order funding_order) {
		funding_orderRepository.delete(funding_order);
	}
	
	public void deleteReward(Reward reward) {
		rewardRepository.delete(reward);
	}
	
	public Funding getFunding(int funding_id) {
		return fundingRepository.getById(funding_id);
	}
	
	public List<Funding> getFundingList() {
		return fundingRepository.findAll();
	}
	
	public List<Reward> getRewardList(Funding funding) {
		return rewardRepository.findByFunding(funding);
	}
	
	public List<Funding> searchFundingListByTitle(String title) {
		return fundingRepository.findByTitleContaining(title);
	}
	
	public List<Funding> searchFundingListByAuthor(String author) {
		return fundingRepository.findByAuthorContaining(author);
	}
	
	public List<Funding> searchFundingListByMember_id(Member member) {
		return fundingRepository.findByMember(member);
	}
	
	public List<Funding_order> searchFunding_orderListByFunding_id(Funding funding) {
		return funding_orderRepository.findByFunding(funding);
	}
	
	public List<Funding_order> searchFunding_orderListByMember_id(Member member) {
		return funding_orderRepository.findByMember(member);
	}

}
