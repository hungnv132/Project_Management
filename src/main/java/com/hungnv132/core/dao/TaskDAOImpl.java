package com.hungnv132.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.Task;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.report.TaskForm;

@Repository
@Transactional
public class TaskDAOImpl implements TaskDAO {

	@Inject
	SessionFactory sessionFactory;

	@Override
	public List<Task> findAll() {
		List<Task> listTask = new ArrayList<Task>();
		Session session = sessionFactory.getCurrentSession();
		listTask = session.createCriteria(Task.class).list();
		return listTask;
	}


	@Override
	public int count() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Task");
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	
	public int countAllWithReport(int reportId) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Task WHERE report_id= :reportId");
		query.setParameter("reportId", reportId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public Task findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Task) session.get(Task.class, id);
	}


	public void create(Task task) {
		Session session = sessionFactory.getCurrentSession();
		session.save(task);
	}

	public boolean update(Task task) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(task);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	public void updateBulkTask(List<TaskForm> tasks) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < tasks.size(); i++) {
			TaskForm t = tasks.get(i);
			Task task = (Task) session.get(Task.class, t.getId());
			task.setContent(t.getContent());
			task.setTimeStart(t.getTimeStart());
			task.setTimeEnd(t.getTimeEnd());
			session.update(task);
			if (i  % 4 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();
		
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Task task = (Task) session.get(Task.class, id);
		session.delete(task);
		return true;
	}


	@Override
	public List<Task> findByPage(int start, int length) {
		return null;
	}


	@Override
	public int countAllWithUser(int userId) {
		return 0;
	}


	@Override
	public List<Task> findByPageWithUser(int start, int length, int userId, ROLE forGetting) {
		return null;
	}


	@Override
	public Task findByIdWithUser(int id, int userId) {
		return null;
	}


	@Override
	public List<Task> findByPage(DatatableForm form) {
		return null;
	}


	@Override
	public List<Task> findByPageFor(ROLE forWho, DatatableForm form) {
		return null;
	}






}
