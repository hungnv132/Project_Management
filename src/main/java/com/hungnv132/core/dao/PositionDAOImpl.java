package com.hungnv132.core.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.Position;

@Repository
@Transactional
public class PositionDAOImpl implements PositionDAO {

	final Logger logger = LogManager.getLogger(PositionDAOImpl.class);
	@Inject
	SessionFactory sessionFactory;

	@Override
	public List<Position> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Position.class).list();

	}
	
	@Override
	public Position findById(int positionId){
		Session session = sessionFactory.getCurrentSession();
		return (Position)session.get(Position.class, positionId);
	}
}
