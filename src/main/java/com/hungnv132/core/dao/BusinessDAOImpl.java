package com.hungnv132.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.Business;


@Component
@Transactional
public class BusinessDAOImpl implements BusinessDAO{
	
	final Logger logger = LogManager.getLogger(BusinessDAOImpl.class);
	@Inject
	SessionFactory sessionFactory;
	

	@Override
	public List<Business> findAll() {
		List<Business> listBusiness = new ArrayList<Business>();
		Session session = sessionFactory.getCurrentSession();
		listBusiness = session.createCriteria(Business.class).list();
		return listBusiness;
	}
	
	@Override
	public int count() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Business");
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	public void create(Business business) {
		Session session = sessionFactory.getCurrentSession();
		session.save(business);
	}

	public boolean update(Business business) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(business);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
		
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Business business = (Business) session.get(Business.class, id);
		try {
			session.delete(business);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
	}

	@Override
	public Business findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Business business = (Business) session.get(Business.class, id);
		Hibernate.initialize(business.getMembers());
		return business;
	}

	

}
