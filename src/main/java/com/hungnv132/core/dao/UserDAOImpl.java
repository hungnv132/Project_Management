package com.hungnv132.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.DatatableForm.ColumnKeys;
import com.hungnv132.core.support.DatatableForm.OrderKeys;
import com.hungnv132.core.support.DatatableForm.SearchKeys;
import com.hungnv132.web.controller.project.AvailableMemberForm;
import com.hungnv132.web.controller.project.DatatableAvailableMemberForm;
import com.hungnv132.web.controller.user.DatatableUserForm;
import com.hungnv132.web.controller.user.DatatableUserForm.DT_ROLE;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	final Logger logger = LogManager.getLogger(UserDAOImpl.class);
	@Inject
	SessionFactory sessionFactory;

	@Override
	public List<User> findAll() {
		List<User> listUser = new ArrayList<User>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			listUser = session.createCriteria(User.class).list();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// session.close();
		}

		return listUser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllByRole(ROLE role) {
		List<User> listUser = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		listUser = session.createCriteria(User.class)
				.add(Restrictions.eq("role", role)).list();
		return listUser;
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

	public User findByEmailAndPassword(String email, String password) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE email = :email AND password= :password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		user = (User) query.uniqueResult();
		return user;

	}

	public User findByPassword(String password) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE password= :password");
		query.setParameter("password", password);
		user = (User) query.uniqueResult();

		return user;

	}
	@Override
	public User findByEmailButNotSelf(String email, int id) {
		User user = new User();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE email = :email AND id != :id");
		query.setParameter("email", email);
		query.setParameter("id", id);
		user = (User) query.uniqueResult();

		return user;

	}

	@Override
	public List<User> findByPage(int start, int length) {
		List<User> listUser = new ArrayList<User>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM user LIMIT ? , ?").addEntity(User.class);
			sqlQuery.setParameter(0, start);
			sqlQuery.setParameter(1, length);
			listUser = sqlQuery.list();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}

		return listUser;
	}

	@Override
	public int count() {
		int count = 0;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select count(id) from User");
		count = ((Long) query.uniqueResult()).intValue();
		session.close();
		return count;
	}

	@Override
	public User findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, id);
	}
	@Override
	public User findById(int id, FETCH_LAZY_FIELD fetchLazyField) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		switch (fetchLazyField) {
		case ALL:
			Hibernate.initialize(user.getReports()); // or user.getReports().size()
			Hibernate.initialize(user.getRequestsForManager()); // or user.getRequestsForManager().size()
			break;
		case REPORT_OF_STAFF:
			Hibernate.initialize(user.getReports()); // or user.getReports().size()
			break;

		case REPORT_FOR_APPROVING:
			Hibernate.initialize(user.getRequestsForManager());
			break;
		default:
			break;
		}
		return user;

	}

	public void create(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	public boolean update(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(user);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
	}
	
	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		return true;
	}

	@Override
	public int countAllWithUser(int userId) {
		return this.count();
	}
	@Override
	public List<User> findManagersOfMember(int memberId){
		List<User> listUser = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		
		criteria.setResultTransformer(Transformers.aliasToBean(User.class));
		listUser = criteria.list();
		return listUser;
	}
	
	@Override
	public List<User> findByRole(ROLE role) {
		List<User> listUser = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("role", role));
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("id"), "id")
				.add(Projections.property("firstName"), "firstName")
				.add(Projections.property("midName"), "midName")
				.add(Projections.property("lastName"), "lastName")
				.add(Projections.property("role"), "role");
		criteria.setProjection(proList);
		criteria.setResultTransformer(Transformers.aliasToBean(User.class));
		listUser = criteria.list();

		return listUser;
	}
	

	@Override
	public List<User> findByPage(DatatableForm form ) {
		DatatableUserForm request = (DatatableUserForm)form;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.setFirstResult(request.getStart());
		criteria.setMaxResults(request.getLength());
		criteria.createAlias("position", "position");
		String searchValue = form.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			int size =  valueList.size();
			if (size == 1) {
				criteria.add(Restrictions.or(
						Restrictions.like("firstName", "%" + searchValue + "%", MatchMode.ANYWHERE),
						Restrictions.like("midName", "%" + searchValue + "%", MatchMode.ANYWHERE),
						Restrictions.like("lastName", "%" + searchValue + "%", MatchMode.ANYWHERE)
						));
			}else{
				criteria.add(Restrictions.or(
								Restrictions.like("firstName", "%" + valueList.get(0) + "%", MatchMode.ANYWHERE),
								Restrictions.like("firstName", "%" + valueList.get(1) + "%", MatchMode.ANYWHERE),
								Restrictions.like("lastName", "%" + valueList.get(size - 1) + "%", MatchMode.ANYWHERE)
							));
			}
			
		}
		DT_ROLE requestRole = request.getRole();
		int positionId = request.getPosition();
		if (requestRole != DT_ROLE.ALL) {
			criteria.add(Restrictions.eq("role", requestRole));
		}
		if (positionId != -1) {
			criteria.add(Restrictions.eq("position.id",positionId));
		}
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.asc("firstName"));
					criteria.addOrder(Order.asc("midName"));
					criteria.addOrder(Order.asc("lastName"));
				}else{
					criteria.addOrder(Order.asc(fieldName));
				}
			}else{
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.desc("firstName"));
					criteria.addOrder(Order.desc("midName"));
					criteria.addOrder(Order.desc("lastName"));
				}else{
					criteria.addOrder(Order.desc(fieldName));
				}
				
			}
		}
		
		return criteria.list();
	}
	
	@Override
	public DataContainer<AvailableMemberForm> findAvailableMember(DatatableForm form ) {
		DataContainer<AvailableMemberForm> container = new DataContainer<AvailableMemberForm>();
		DatatableAvailableMemberForm request = (DatatableAvailableMemberForm)form;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class, "user");
		criteria.createAlias("position", "position");
		
		ProjectionList list = Projections.projectionList();
		list.add(Projections.property("user.id"),"id");
		list.add(Projections.property("user.firstName"),"firstName");
		list.add(Projections.property("user.midName"),"midName");
		list.add(Projections.property("user.lastName"),"lastName");
		list.add(Projections.property("position.name"),"position");
		
		int positionId = request.getPosition();
		if (positionId != -1) {
			criteria.add(Restrictions.eq("position.id",positionId));
		}
		
		criteria.setProjection(list);
		
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.asc("user.firstName"));
					criteria.addOrder(Order.asc("user.midName"));
					criteria.addOrder(Order.asc("user.lastName"));
				}else{
					criteria.addOrder(Order.asc(fieldName));
				}
			}else{
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.desc("user.firstName"));
					criteria.addOrder(Order.desc("user.midName"));
					criteria.addOrder(Order.desc("user.lastName"));
				}else{
					criteria.addOrder(Order.desc(fieldName));
				}
				
			}
		}
		container.setTotal(criteria.list().size());
		criteria.setFirstResult(request.getStart());
		criteria.setMaxResults(request.getLength());
		
		criteria.setResultTransformer(Transformers.aliasToBean(AvailableMemberForm.class));
		container.setData(criteria.list());
		return container;
	}

	@Override
	public List<User> findByPageWithUser(int start, int length, int userId, ROLE forGetting) {
		return null;
	}

	@Override
	public User findByIdWithUser(int id, int userId) {
		return null;
	}

	@Override
	public List<User> findByPageFor(ROLE forWho, DatatableForm form) {
		return null;
	}

	

}
