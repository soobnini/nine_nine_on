package com.sharebook.sharebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Rent;
import com.sharebook.sharebook.repository.RentRepository;

@Service("rentService")
public class RentService {
	
	@Autowired
	public RentRepository rentRepository;
	
	public void insertRent(Rent rent) {
		rentRepository.save(rent);
	}
	
	public void updateRent(Rent rent) {
		rentRepository.save(rent);
	}
	
	public void deleteRent(Rent rent) {
		rentRepository.delete(rent);
	}
	
	public Rent getRentByBook(Book book) {
		Optional<Rent> rent = rentRepository.findByBook(book);
		if(rent.isPresent())
			return rent.get();
		return null;
	}
	
	public List<Rent> getRentList(Member member) {
		return rentRepository.findByMember(member);
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
