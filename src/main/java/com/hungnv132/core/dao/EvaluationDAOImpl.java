package com.hungnv132.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Evaluation;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.DatatableForm.ColumnKeys;
import com.hungnv132.core.support.DatatableForm.OrderKeys;
import com.hungnv132.core.support.DatatableForm.SearchKeys;
import com.hungnv132.web.controller.evaluation.DatatableEvaluationForm;
import com.hungnv132.web.controller.evaluation.DatatableEvaluationFormForAdmin;
import com.hungnv132.web.controller.evaluation.DisplayEvaluationForm;

@Component
@Transactional
public class EvaluationDAOImpl implements EvaluationDAO {

	final Logger logger = LogManager.getLogger(EvaluationDAOImpl.class);
	@Inject
	SessionFactory sessionFactory;

	@Override
	public List<Evaluation> findAll() {
		List<Evaluation> listEvaluation = new ArrayList<Evaluation>();
		Session session = sessionFactory.getCurrentSession();
		listEvaluation = session.createCriteria(Evaluation.class).list();
		return listEvaluation;
	}
 	@Override
	public List<Evaluation> findByPage(int start, int length) {
		List<Evaluation> listEvaluation = new ArrayList<Evaluation>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM evaluations WHERE LIMIT ? , ? ").addEntity(
					Evaluation.class);
			sqlQuery.setParameter(0, start);
			sqlQuery.setParameter(1, length);
			listEvaluation = sqlQuery.list();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}

		return listEvaluation;
	}

	@Override
	public int count() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from evaluations");
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public int countAllWithUser(int userId) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from evaluations WHERE user_evaluation= :userId");
		query.setParameter("userId", userId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public Evaluation findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Evaluation) session.get(Evaluation.class, id);
	}

	public void create(Evaluation evaluation) {
		Session session = sessionFactory.getCurrentSession();
		session.save(evaluation);
	}

	public boolean update(Evaluation evaluation) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(evaluation);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
		
	}
	
	

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Evaluation evaluation = (Evaluation) session.get(Evaluation.class, id);
		try {
			session.delete(evaluation);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
	}

	
	@Override
	public DataContainer<Evaluation> findEvaluationByPage(DatatableForm form) {
		DatatableEvaluationForm evaluationForm = (DatatableEvaluationForm) form ;
		DataContainer<Evaluation> dataContainer = new DataContainer<Evaluation>();
		Session session = sessionFactory.getCurrentSession();
		
		/*ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("staffEvaluation.firstName").as("firstName"));
		proList.add(Projections.property("staffEvaluation.midName").as("midName"));
		proList.add(Projections.property("staffEvaluation.lastName").as("lastName"));
		proList.add(Projections.property("evaluation.createAt").as("createAt"));
		proList.add(Projections.sqlProjection("SUM(skill + behavior + task_completion + task_complexity + working_discipline ) as totalPoint",  new String[]{"totalPoint"}, new Type[]{new IntegerType()}));
		proList.add(Projections.groupProperty("evaluation.id").as("id"));
		
		
		Criteria criteriaEvaluation = session
				.createCriteria(Evaluation.class, "evaluation")
				.createAlias("staffEvaluation", "staffEvaluation")
				.createAlias("managerEvaluation", "managetEvaluation")
				.add(Restrictions.eq("managerEvaluation.id", evaluationForm.getManagerId()))
				;
		criteriaEvaluation.setProjection(proList);
		
//		for (Map<OrderKeys, String> field : evaluationForm.getOrder()) {
//			int number = Integer.parseInt(field.get(OrderKeys.column));
//			String fieldName = evaluationForm.getColumns().get(number).get(ColumnKeys.name);
//			String order = field.get(OrderKeys.dir);
//			if(order.equals("asc")){
//				if (fieldName.equals("fullName")) {
//					criteriaEvaluation.addOrder(Order.asc("staffEvaluation.firstName"));
//					criteriaEvaluation.addOrder(Order.asc("staffEvaluation.midName"));
//					criteriaEvaluation.addOrder(Order.asc("staffEvaluation.lastName"));
//				}else if (fieldName.equals("totalPoint")) {
//					proList.add(Projections.sqlProjection("order totalPoint asc",  new String[]{}, new Type[]{new IntegerType()}));
//				} else {
//					criteriaEvaluation.addOrder(Order.asc(fieldName));
//				}
//			}else{
//				if (fieldName.equals("fullName")) {
//					criteriaEvaluation.addOrder(Order.desc("staffEvaluation.firstName"));
//					criteriaEvaluation.addOrder(Order.desc("staffEvaluation.midName"));
//					criteriaEvaluation.addOrder(Order.desc("staffEvaluation.lastName"));
//				}else if (fieldName.equals("totalPoint")) {
//					proList.add(Projections.sqlProjection("order totalPoint desc",  new String[]{}, new Type[]{new IntegerType()}));
//				} else{
//					criteriaEvaluation.addOrder(Order.desc(fieldName));
//				}
//				
//			}
//		}
	
		
		
		dataContainer.setTotal(criteriaEvaluation.list().size());
		criteriaEvaluation.setFirstResult(evaluationForm.getStart());
		criteriaEvaluation.setMaxResults(evaluationForm.getLength());
//		criteriaEvaluation.setResultTransformer(new AliasToBeanResultTransformer(DisplayEvaluationForm.class));
		dataContainer.setData(criteriaEvaluation.list());*/
		
		
		StringBuilder sqlQuery = new StringBuilder(
					"SELECT eva.id as id, DATE_FORMAT(eva.create_at,'%d-%m-%Y') as createAt, staff.first_Name as firstName, staff.mid_name as midName, staff.last_name as lastName, "
					+ "SUM(eva.skill + eva.behavior + eva.task_completion + eva.task_complexity + eva.working_discipline) as totalPoint "
					+ "FROM evaluations eva "
					+ "INNER JOIN users staff ON eva.staff_id = staff.id "
					+ "INNER JOIN users manager ON eva.evaluated_by = manager.id "
					+ "WHERE manager.id = :managerId ");
		String searchValue = form.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			int size =  valueList.size();
			if (size == 1) {
				sqlQuery.append("AND ( staff.first_name LIKE  \'%" + searchValue + "%\' OR staff.mid_name LIKE  \'%" + searchValue + "%\' OR staff.last_name LIKE  \'%" + searchValue + "%\') ");
			}else{
				sqlQuery.append("AND ( staff.first_name LIKE  \'%" + valueList.get(0) + "%\' OR staff.mid_name LIKE  \'%" + valueList.get(1) + "%\' OR staff.last_name LIKE  \'%" + valueList.get(size - 1) + "%\') ");
			}
			
		}
		
		sqlQuery.append("GROUP BY eva.id ");
		
		for (Map<OrderKeys, String> field : evaluationForm.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = evaluationForm.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					sqlQuery.append("ORDER BY firstName ASC, midName ASC, lastName ASC, createAt ASC ");
				}else {
					sqlQuery.append("ORDER BY "+ fieldName+" ASC ");
				}
			}else{
				
					if (fieldName.equals("fullName")) {
						sqlQuery.append("ORDER BY firstName DESC, midName DESC, lastName DESC, createAt DESC ");
					}else {
						sqlQuery.append("ORDER BY "+ fieldName+" DESC ");
					}
			}
		}
		SQLQuery query1 =  session.createSQLQuery(sqlQuery.toString());
		query1.setParameter("managerId", evaluationForm.getManagerId());
		dataContainer.setTotal(query1.list().size());
		
		sqlQuery.append("LIMIT :start, :length ");
		SQLQuery query =  session.createSQLQuery(sqlQuery.toString());
		query.setParameter("managerId", evaluationForm.getManagerId());;
		query.setParameter("start", evaluationForm.getStart());
		query.setParameter("length", evaluationForm.getLength());
		query.setResultTransformer(new AliasToBeanResultTransformer(DisplayEvaluationForm.class));
		dataContainer.setData(query.list());
		return dataContainer;
	}

	
	public DataContainer<DisplayEvaluationForm> findAllByUserId(DatatableForm form){
		DatatableEvaluationFormForAdmin evaluationForm = (DatatableEvaluationFormForAdmin) form ;
		DataContainer<DisplayEvaluationForm> dataContainer = new DataContainer<DisplayEvaluationForm>();
		Session session = sessionFactory.getCurrentSession();
		
		StringBuilder sqlQuery = new StringBuilder(
					"SELECT eva.id as id, DATE_FORMAT(eva.create_at,'%d-%m-%Y') as createAt, staff.first_Name as firstName, staff.mid_name as midName, staff.last_name as lastName, project.name as projectName, "
					+ "SUM(eva.skill + eva.behavior + eva.task_completion + eva.task_complexity + eva.working_discipline) as totalPoint "
					+ "FROM evaluations eva "
					+ "INNER JOIN users staff ON eva.staff_id = staff.id "
					+ "INNER JOIN users manager ON eva.evaluated_by = manager.id "
					+ "INNER JOIN projects project ON eva.project_id = project.id "
					+ "WHERE staff.id = :staffId ");
		String searchValue = form.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			StringBuilder strBuilder = new  StringBuilder("\'%");
			for (String value : valueList) {
				strBuilder.append(value+ "%");
			}
			sqlQuery.append("AND project.name LIKE " + strBuilder.toString() +"\' ");
		}
		
		sqlQuery.append("GROUP BY eva.id ");
		
		for (Map<OrderKeys, String> field : evaluationForm.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = evaluationForm.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					sqlQuery.append("ORDER BY firstName ASC, midName ASC, lastName ASC, createAt ASC ");
				}else {
					sqlQuery.append("ORDER BY "+ fieldName+" ASC ");
				}
			}else{
				
					if (fieldName.equals("fullName")) {
						sqlQuery.append("ORDER BY firstName DESC, midName DESC, lastName DESC, createAt DESC ");
					}else {
						sqlQuery.append("ORDER BY "+ fieldName+" DESC ");
					}
			}
		}
		SQLQuery query1 =  session.createSQLQuery(sqlQuery.toString());
		query1.setParameter("staffId", evaluationForm.getStaffId());
		dataContainer.setTotal(query1.list().size());
		
		sqlQuery.append("LIMIT :start, :length ");
		SQLQuery query =  session.createSQLQuery(sqlQuery.toString());
		query.setParameter("staffId", evaluationForm.getStaffId());;
		query.setParameter("start", evaluationForm.getStart());
		query.setParameter("length", evaluationForm.getLength());
		query.setResultTransformer(new AliasToBeanResultTransformer(DisplayEvaluationForm.class));
		dataContainer.setData(query.list());
		return dataContainer;
	}
	
	@Override
	public List<Evaluation> findByPage(DatatableForm form) {
		return null;
	}

	@Override
	public List<Evaluation> findByPageWithUser(int start, int length, int userId, ROLE forGetting) {
		return null;
	}

	@Override
	public List<Evaluation> findByPageFor(ROLE forWho, DatatableForm form) {
		return null;
	}

	@Override
	public Evaluation findByIdWithUser(int id, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Evaluation evaluation  = (Evaluation)session
				.createCriteria(Evaluation.class, "evaluation")
				.createAlias("staff", "staff")
				.createAlias("evaluatedBy", "manager")
				.createAlias("project", "project")
				.add(Restrictions.eq("manager.id", userId))
				.add(Restrictions.eq("evaluation.id", id))
				.uniqueResult();
		return evaluation;
	}


}
