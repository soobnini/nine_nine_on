package com.sharebook.sharebook.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sharebook.sharebook.domain.Funding;

@Repository
public class JPAFundingDAO implements FundingDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Funding funding) throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return;
		}
		
		em.persist(funding);
	}

	@Override
	public void delete(int funding_id) throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return;
		}
		
		em.remove(findFundingById(funding_id));
	}

	@Override
	public void update(int funding_id, Funding funding) throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return;
		}
		
		em.merge(funding);
	}

	@Override
	public Funding findFundingById(int funding_id) throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return null;
		}
		
		return em.find(Funding.class, funding_id);
	}

	@Override
	public List<Funding> findFundingByTitle(String title) throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return null;
		}
		
		TypedQuery<Funding> query = em.createQuery(
				"select funding from FUNDING funding "
				+ "where funding.title=?1", Funding.class);
		query.setParameter(1, title);
		return query.getResultList();
	}

	@Override
	public List<Funding> findFundingByAuthor(String author) throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return null;
		}
		
		TypedQuery<Funding> query = em.createQuery(
				"select funding from FUNDING funding "
				+ "where funding.author=?1", Funding.class);
		query.setParameter(1, author);
		return query.getResultList();
	}

	@Override
	public List<Funding> findFundingList() throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return null;
		}
		
		TypedQuery<Funding> query = em.createQuery(
				"select funding from FUNDING funding ", Funding.class);
		return query.getResultList();
	}

	@Override
	public List<Funding> findFundingList(int user_id) throws DataAccessException {
		if (em == null) {
			System.out.println("==== EntityManager is null ====");
			return null;
		}
		
		TypedQuery<Funding> query = em.createQuery(
				"select funding from FUNDING funding "
				+ "where funding.user_id=?1", Funding.class);
		query.setParameter(1, user_id);
		return query.getResultList();
	}

}
