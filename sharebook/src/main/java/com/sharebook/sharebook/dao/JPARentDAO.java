package com.sharebook.sharebook.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sharebook.sharebook.domain.Rent;

@Repository
public class JPARentDAO implements RentDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Rent rent) throws DataAccessException {
		em.persist(rent);
	}

	@Override
	public void delete(int rent_id) throws DataAccessException {
		em.remove(em.find(Rent.class, rent_id));
	}

	@Override
	public List<Rent> findRentList(int user_id) throws DataAccessException {
		TypedQuery<Rent> query = em.createQuery(
				"select rent from RENT rent "
				+ "where rent.user_id=?1", Rent.class);
		query.setParameter(1, user_id);
		return query.getResultList();
	}

}
