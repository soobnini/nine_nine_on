package com.sharebook.sharebook.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sharebook.sharebook.domain.Reward;

@Repository
public class JPARewardDAO implements RewardDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(Reward reward) throws DataAccessException {
		em.persist(reward);
	}

	@Override
	public void delete(int reward_id) throws DataAccessException {
		em.remove(em.find(Reward.class, reward_id));
	}

	@Override
	public List<Reward> findRewardList(int funding_id) throws DataAccessException {
		TypedQuery<Reward> query = em.createQuery(
				"select reward from REWARD reward "
				+ "where reward.funding_id=?1", Reward.class);
		query.setParameter(1, funding_id);
		return query.getResultList();
	}

}
