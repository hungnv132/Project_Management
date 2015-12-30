package com.hungnv132.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.Business;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AliasToBeanNestedResultTransformer;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.project.DisplayBusinessForm;
import com.hungnv132.web.controller.report.ReportListForManagerForm;

@Component
@Transactional
public class ProjectDAOImpl implements ProjectDAO {
	
	final Logger logger = LogManager.getLogger(ProjectDAOImpl.class);
	@Inject
	SessionFactory sessionFactory;
	

	@Override
	public List<Project> findAll() {
		List<Project> listProject = new ArrayList<Project>();
		Session session = sessionFactory.getCurrentSession();
		listProject = session.createCriteria(Project.class).list();
		return listProject;
	}
	@Override
	public List<Project> findAllProjectName() {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Project.class, "p");
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("p.id"), "id");
		list.add(Projections.property("p.name"), "name");
		criteria.setProjection(list);
		criteria.setResultTransformer(new AliasToBeanResultTransformer(Project.class));
		return criteria.list();
	}
	
	@Override
	public List<Project> findAllProjectNameByMemberId(int memberId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Project.class, "p");
		ProjectionList list = Projections.projectionList();
		list.add(Projections.distinct(Projections.property("p.id")), "id");
		list.add(Projections.property("p.name"), "name");
		list.add(Projections.property("p.description"), "description");
		criteria.createAlias("p.business", "b");
		criteria.createAlias("b.members", "member");
		
		criteria.add(Restrictions.eq("member.id", memberId));
		criteria.setProjection(list);
		criteria.setResultTransformer(new AliasToBeanResultTransformer(Project.class));
		
		return criteria.list();
		
	}
	
	@Override
	public int count() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Project");
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	public void create(Project project) {
		Session session = sessionFactory.getCurrentSession();
		session.save(project);
	}

	public boolean update(Project project) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(project);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
		
	}

	@Override
	public boolean delete(int id) {
		return false;
	}


	@Override
	public List<Project> findByPage(int start, int length) {
		return null;
	}

	@Override
	public List<Project> findByPage(DatatableForm form) {
		return null;
	}

	@Override
	public List<Project> findByPageWithUser(int start, int length, int userId, ROLE forGetting) {
		return null;
	}

	@Override
	public List<Project> findByPageFor(ROLE forWho, DatatableForm form) {
		return null;
	}

	@Override
	public Project findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Project project = (Project) session.get(Project.class, id);
		Hibernate.initialize(project.getBusiness());
		return project;
	}

	@Override
	public Project findByIdWithoutBusiness(int id) {
		Session session = sessionFactory.getCurrentSession();
		Project project = (Project) session.get(Project.class, id);
		return project;
	}
	@Override
	public Project findByIdWithUser(int id, int userId) {
		return null;
	}


	@Override
	public int countAllWithUser(int userId) {
		return 0;
	}

}
