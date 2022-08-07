package com.sharebook.sharebook.service;

import java.util.Date;
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
	
	public int isOverdue(Rent rent) {
		Date today = new Date();
		long end_day = rent.getEnd_day().getTime() / 1000 / 60 / 60 / 24;
		long now = today.getTime() / 1000 / 60 / 60 / 24;
		
		return (int) (now - end_day) - 1;
	}

}
