package com.sharebook.sharebook.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Rent;
import com.sharebook.sharebook.repository.RentRepository;

@Service("rentService")
public class RentService {
	
	@Autowired
	RentRepository rentRepository;
	
	public void insertRent(Rent rent) {
		rentRepository.save(rent);
	}
	
	public void updateRent(Rent rent) {
		rentRepository.save(rent);
	}
	
	public void deleteRent(Rent rent) {
		rentRepository.delete(rent);
	}
	
	public List<Rent> getRentList(int member_id) {
		return rentRepository.findByMember_id(member_id);
	}
	
	public boolean isOverdue(Rent rent) {
		long diff = rent.getEnd_day().getTime() - rent.getStart_day().getTime();
		diff /= 1000;
		diff /= 60 * 60 * 24;
		
		if (diff > 31) {
			return true;
		}
		return false;
	}

}
