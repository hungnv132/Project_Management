package com.hungnv132.core.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.User;

public  abstract class MyRepository<T>{
//	@Inject
//	SessionFactory sessionFactory;
//	
//	
//	public void create(T object) {
//		Session session = null;
//		try {
//			session = sessionFactory.openSession();
//			session.save(object);
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			session.close();
//		}
//
//	}
//	
//	
//	public void update(T object) {
//		Session session = null;
//		Transaction tx =  null;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//			session.update(object);
//			tx.commit();
//		} catch (Exception e) {
//			tx.rollback();		
//		} finally {
//			session.close();
//		}
//	}
//
//
//	public void delete(T object) {
//		Session session = null;
//		try {
//			session = sessionFactory.openSession();
//			session.delete(object);
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			session.close();
//		}
//	}

   
}
