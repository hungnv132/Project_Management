package com.hungnv132.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.User;

@Component
@Transactional
public class TestDAO {

	@Inject
	SessionFactory sessionFactory;

	public int findAll() {
		int count = -1;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("select count(id) from User");
			count = ((Long) query.uniqueResult()).intValue();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// session.close();
		}

		return count;
	}

	public User findByEmail(String email) {
		User user = new User();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM User WHERE email = :email");
			query.setParameter("email", email);
			user = (User) query.uniqueResult();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}

		return user;

	}

	public List<Report> findByPageWithPM(int start, int length, int pmId) {
		List<Report> listReport = new ArrayList<Report>();
		Session session = null;

		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("report.id"), "id");
		proList.add(Projections.property("report.createAt"), "createAt");
		proList.add(Projections.property("report.status"), "status");
		proList.add(Projections.property("report.comment"), "comment");
		proList.add(Projections.property("staff.firstName"), "staff.firstName");
		proList.add(Projections.property("staff.midName"), "staff.midName");
		proList.add(Projections.property("staff.lastName"), "staff.lastName");
		proList.add(Projections.property("staff.position"), "staff.position");

		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Report.class, "report").setFirstResult(start).setMaxResults(length)
				.addOrder(Order.desc("createAt")).createAlias("staff", "staff").createAlias("managerApprove", "manager")
				.add(Restrictions.eq("pm.id", pmId));
		// .setProjection(proList)

		// return (List<ConfirmReportInfoForm>)criteria.list();
		return criteria.list();
	}
/*
	public void creattingForTest(Test test) {
		
		Session session = sessionFactory.openSession();
		session.save(test);
		session.close();
	}

	public Test gettingForTest(int testId){
		Session session  = sessionFactory.openSession();
		Test test = (Test)  session.get(Test.class, testId);
		session.close();
		return test;
	}*/
}
