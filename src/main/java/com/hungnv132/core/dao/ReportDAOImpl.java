package com.hungnv132.core.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
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
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.Report.REPORT_STATUS;
import com.hungnv132.core.domain.Task;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AliasToBeanNestedResultTransformer;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.DatatableForm.ColumnKeys;
import com.hungnv132.core.support.DatatableForm.OrderKeys;
import com.hungnv132.core.support.DatatableForm.SearchKeys;
import com.hungnv132.web.controller.report.DatatableReportForm;
import com.hungnv132.web.controller.report.DatatableReportForm.DT_REPORT_STATUS;
import com.hungnv132.web.controller.report.DatatableReportFormForAdmin;
import com.hungnv132.web.controller.report.DisplayReportForm;
import com.hungnv132.web.controller.report.ReportListForManagerForm;
import com.hungnv132.web.controller.report.TaskForm;
import com.hungnv132.web.controller.report.UpdateReportForm;

@Repository
@Transactional
public class ReportDAOImpl implements ReportDAO {

	final Logger logger = LogManager.getLogger(ReportDAOImpl.class);
	@Inject
	SessionFactory sessionFactory;

	@Override
	public List<Report> findAll() {
		List<Report> listReport = new ArrayList<Report>();
		Session session = sessionFactory.getCurrentSession();
		listReport = session.createCriteria(Report.class).list();
		return listReport;
	}

	@Override
	public List<Report> findByPage(int start, int length) {
		List<Report> listReport = new ArrayList<Report>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM report WHERE LIMIT ? , ? ").addEntity(
					Report.class);
			sqlQuery.setParameter(0, start);
			sqlQuery.setParameter(1, length);
			listReport = sqlQuery.list();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}

		return listReport;
	}

	@Override
	public int count() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Report");
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public int countAllWithUser(int userId) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Report WHERE member_report= :userId");
		query.setParameter("userId", userId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public Report findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Report) session.get(Report.class, id);
	}

	@Override
	public List<Report> findByStatus(REPORT_STATUS status) {
		List<Report> listReport = new ArrayList<Report>();
		Session session = sessionFactory.getCurrentSession();
		listReport = session.createQuery("FROM report WHERE status = :status").list();
		return listReport;
	}

	public void create(Report report) {
		Session session = sessionFactory.getCurrentSession();
		session.save(report);
	}

	public boolean update(Report report) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(report);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
		
	}
	public void updateReportWithTask(UpdateReportForm form) {
		Session session = sessionFactory.getCurrentSession();
		Report report = (Report)session.get(Report.class, form.getId());
		Project project = (Project)session.get(Project.class,form.getProjectId());
		report.setProject(project);
		report.setCreateAt(form.getCreateAt());
		List<Task> listTask = report.getTasks();
		for (int i = 0; i < form.getTasks().size(); i++) {
			TaskForm tf = form.getTasks().get(i);
			Task task = new Task(tf.getContent(), tf.getTimeStart(), tf.getTimeEnd());
			task.setReport(report);
			listTask.add(task);
		}
		session.update(report);
	}
	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Report  report = (Report)session.get(Report.class, id);
		try {
			session.delete(report);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	// return number of report that pm have to approve , find by manager_approve
	
	@Override
	public int countReportForPMApprove(int pmId){
		Session session = null;
		int count = 0;
		session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Report WHERE manager_approve= :pmId");
		query.setParameter("pmId", pmId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;

	}
	
	@Override
	public List<ReportListForManagerForm> findByPageWithPM(int start, int length, int pmId){
		List<Report> listReport = new ArrayList<Report>();
		Session session = null;
		
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("report.id"),"id" );
		proList.add(Projections.property("report.createAt"),"createAt" );
		proList.add(Projections.property("report.status"),"status" );
		proList.add(Projections.property("report.comment"),"comment" );
		proList.add(Projections.property("staff.firstName"),"staff.firstName" );
		proList.add(Projections.property("staff.midName"),"staff.midName" );
		proList.add(Projections.property("staff.lastName"),"staff.lastName" );
		proList.add(Projections.property("staff.position"),"staff.position" );
		
		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Report.class, "report")
							.setFirstResult(start)
							.setMaxResults(length)
							.addOrder(Order.desc("createAt"))
							.createAlias("memberReport", "staff")
							.createAlias("confirmBy", "manager")
							.add(Restrictions.eq("pm.id", pmId))
							.setProjection(proList)
							.setResultTransformer(new AliasToBeanNestedResultTransformer(ReportListForManagerForm.class));
		
//		return (List<ReportListForManagerForm>)criteria.list();
		return criteria.list();
	}
	
	@Override
	public DataContainer<ReportListForManagerForm> findByPageForMANAGER(DatatableForm form){
		DataContainer<ReportListForManagerForm> dataContainer = new DataContainer<ReportListForManagerForm>();
		
		DatatableReportForm request = (DatatableReportForm)form;
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("report.id"),"id" );
		proList.add(Projections.property("report.createAt"),"createAt" );
		proList.add(Projections.property("report.status"),"status" );
		proList.add(Projections.property("project.name"),"projectName" );
		proList.add(Projections.property("staff.firstName"),"staff.firstName" );
		proList.add(Projections.property("staff.midName"),"staff.midName" );
		proList.add(Projections.property("staff.lastName"),"staff.lastName");
		proList.add(Projections.property("staff.position"),"staff.position" );
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Report.class, "report")
							.createAlias("memberReport", "staff")
							.createAlias("project", "project")
							.createAlias("project.business", "business")
							.createAlias("business.members", "manager")							
							.add(Restrictions.eq("manager.id", request.getForWhoId()))
							.setProjection(proList);
		
		String searchValue = request.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			int size =  valueList.size();
			if (size == 1) {
				criteria.add(Restrictions.or(
						Restrictions.like("staff.firstName", "%" + searchValue + "%", MatchMode.ANYWHERE),
						Restrictions.like("staff.midName", "%" + searchValue + "%", MatchMode.ANYWHERE),
						Restrictions.like("staff.lastName", "%" + searchValue + "%", MatchMode.ANYWHERE)
						));
			}else{
				criteria.add(Restrictions.or(
								Restrictions.like("staff.firstName", "%" + valueList.get(0) + "%", MatchMode.ANYWHERE),
								Restrictions.like("staff.firstName", "%" + valueList.get(1) + "%", MatchMode.ANYWHERE),
								Restrictions.like("staff.lastName", "%" + valueList.get(size - 1) + "%", MatchMode.ANYWHERE)
							));
			}
			
		}
		DT_REPORT_STATUS reportStatus = request.getReportStatus();
		if (reportStatus != DT_REPORT_STATUS.ALL) {
			criteria.add(Restrictions.eq("report.status", reportStatus));
		}
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.asc("staff.firstName"));
					criteria.addOrder(Order.asc("staff.midName"));
					criteria.addOrder(Order.asc("staff.lastName"));
				}else{
					criteria.addOrder(Order.asc(fieldName));
				}
			}else{
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.desc("staff.firstName"));
					criteria.addOrder(Order.desc("staff.midName"));
					criteria.addOrder(Order.desc("staff.lastName"));
				}else{
					criteria.addOrder(Order.desc(fieldName));
				}
			}
		}
		dataContainer.setTotal(criteria.list().size());
		criteria.setFirstResult(request.getStart());
		criteria.setMaxResults(request.getLength());
		
		criteria.setResultTransformer(new AliasToBeanNestedResultTransformer(ReportListForManagerForm.class));
		dataContainer.setData((List<ReportListForManagerForm>)criteria.list());
		return dataContainer;
	}
	

	@Override
	public DataContainer<ReportListForManagerForm> findByPageForAdmin(DatatableForm form){
		DataContainer<ReportListForManagerForm> dataContainer = new DataContainer<ReportListForManagerForm>();
		
		DatatableReportFormForAdmin request = (DatatableReportFormForAdmin)form;
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("report.id"),"id" );
		proList.add(Projections.property("report.createAt"),"createAt" );
		proList.add(Projections.property("report.status"),"status" );
		proList.add(Projections.property("project.name"),"projectName" );
		proList.add(Projections.property("staff.firstName"),"staff.firstName" );
		proList.add(Projections.property("staff.midName"),"staff.midName" );
		proList.add(Projections.property("staff.lastName"),"staff.lastName");
		proList.add(Projections.property("staff.position"),"staff.position" );
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Report.class, "report")
							.createAlias("memberReport", "staff")
							.createAlias("project", "project")
							.add(Restrictions.eq("staff.id", request.getStaffId()))
							.setProjection(proList);
		
		String searchValue = request.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			if (valueList.size() > 0) {
				StringBuilder searchValuteString = new StringBuilder("%");
				Iterator<String > iterator = valueList.iterator();
				while (iterator.hasNext()) {
					searchValuteString.append(iterator.next() +"%");
				}
				criteria.add(Restrictions.like("project.name", searchValuteString.toString(), MatchMode.ANYWHERE));
			}
			
		}
		DT_REPORT_STATUS reportStatus = request.getReportStatus();
		if (reportStatus != DT_REPORT_STATUS.ALL) {
			criteria.add(Restrictions.eq("report.status", reportStatus));
		}
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.asc("staff.firstName"));
					criteria.addOrder(Order.asc("staff.midName"));
					criteria.addOrder(Order.asc("staff.lastName"));
				}else{
					criteria.addOrder(Order.asc(fieldName));
				}
			}else{
				if (fieldName.equals("fullName")) {
					criteria.addOrder(Order.desc("staff.firstName"));
					criteria.addOrder(Order.desc("staff.midName"));
					criteria.addOrder(Order.desc("staff.lastName"));
				}else{
					criteria.addOrder(Order.desc(fieldName));
				}
			}
		}
		dataContainer.setTotal(criteria.list().size());
		criteria.setFirstResult(request.getStart());
		criteria.setMaxResults(request.getLength());
		
		criteria.setResultTransformer(new AliasToBeanNestedResultTransformer(ReportListForManagerForm.class));
		dataContainer.setData((List<ReportListForManagerForm>)criteria.list());
		return dataContainer;
	}
	
	@Override
	public int countReportApproved(int userId) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Report WHERE member_report= :userId AND status = 'APPROVED'");
		query.setParameter("userId", userId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public int countReportThisMonthOfUser(int userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		String firstDate = sdf.format(date.getTime());

		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDate = sdf.format(date.getTime());

		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Report WHERE member_report= :userId AND (create_at BETWEEN  :firstDate AND :lastDate )");
		query.setParameter("userId", userId);
		query.setParameter("firstDate", firstDate);
		query.setParameter("lastDate", lastDate);
		count = ((Long) query.uniqueResult()).intValue();
		return count;

	}

	@Override
	public int countReportApprovedThisMonthOfUser(int userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		String firstDate = sdf.format(date.getTime());

		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDate = sdf.format(date.getTime());

		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Report WHERE status = 'APPROVED' AND  member_report= :userId AND (create_at BETWEEN  :firstDate AND :lastDate )");
		query.setParameter("userId", userId);
		query.setParameter("firstDate", firstDate);
		query.setParameter("lastDate", lastDate);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public Report findByIdWithUser(int id, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Report report = (Report) session.createCriteria(Report.class, "report")
							.add(Restrictions.eq("report.id", id))
							.createAlias("memberReport", "staff")
							.createAlias("project", "project")
							.add(Restrictions.eq("staff.id", userId))
							.uniqueResult();
		return report;
	}

	
	// default lấy ra tăng theo cột create
	@Override
	public List<Report> findByPageWithUser(int start, int length, int userId, ROLE showTo) {
		
		return findByPageWithUser_AtFieldOrder(start, length, userId, showTo,"asc" ,"createAt");

		// List<Report> listReport = new ArrayList<Report>();
		// Session session = null;
		// try {
		// session = sessionFactory.openSession();
		// SQLQuery sqlQuery =
		// session.createSQLQuery("SELECT * FROM report WHERE member_report= ? LIMIT ? , ? ").addEntity(Report.class);
		// sqlQuery.setParameter(0, userId);
		// sqlQuery.setParameter(1, start);
		// sqlQuery.setParameter(2, length);
		// listReport = sqlQuery.list();
		//
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// } finally {
		// session.close();
		// }
		//
		// return listReport;
	}
	


	// showTo: STAFF: nhân viên lấy ra những report của mình
	//  	   PM: pm lấy ra những report của nhân viên họ quản lý
	// fieldOrder: sắp xép theo trường nào 
	
	@Override
	public List<Report> findByPageWithUser_AtFieldOrder(int start, int length, int userId, ROLE showTo, 
			String order, String fieldOrder) {
		List<Report> listReport = new ArrayList<Report>();
		Session session = null;
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Report.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(length);
		criteria.addOrder(Order.asc(fieldOrder));
		if(order.equals("asc")){
			criteria.addOrder(Order.asc(fieldOrder));
		}else{
			criteria.addOrder(Order.desc(fieldOrder));
		}
		
		Criteria staff = criteria.createCriteria("staff");
		if (showTo ==ROLE.MANAGER) {
			staff = criteria.createCriteria("managerApprove");
		}
		staff.add(Restrictions.eq("id", userId));

		return criteria.list();
	}

	@Override
	public List<Report> findByPage(DatatableForm form) {
		return null;
	}

	@Override
	public List<Report> findByPageFor(ROLE forWho, DatatableForm form) {
		DatatableReportForm request = (DatatableReportForm)form;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Report.class,"report");
		

		criteria.createCriteria("project", "project");
		
		Criteria staff = criteria.createCriteria("memberReport");
		if (forWho == ROLE.MANAGER) {
			staff = criteria.createCriteria("confirmBy");
		}
		staff.add(Restrictions.eq("id", request.getForWhoId()));
		
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("report.id"),"id" );
		proList.add(Projections.property("report.createAt"),"createAt" );
		proList.add(Projections.property("report.status"),"status" );
		proList.add(Projections.property("project.name"),"projectName" );
		
		criteria.setProjection(proList);
		
		String searchValue = request.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			if (valueList.size() > 0) {
				StringBuilder searchValuteString = new StringBuilder("%");
				Iterator<String > iterator = valueList.iterator();
				while (iterator.hasNext()) {
					searchValuteString.append(iterator.next() +"%");
				}
				criteria.add(Restrictions.like("project.name", searchValuteString.toString(), MatchMode.ANYWHERE));
			}
			
		}
		
		DT_REPORT_STATUS reportStatus= request.getReportStatus();
		logger.debug("++++++++++++ reportStatus: "+ reportStatus);
		if (reportStatus != DT_REPORT_STATUS.ALL) {
			criteria.add(Restrictions.eq("status",reportStatus));
		}
		
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.data);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				criteria.addOrder(Order.asc(fieldName));
			}else{
				criteria.addOrder(Order.desc(fieldName));
			}
		}
		
		criteria.setFirstResult(request.getStart());
		criteria.setMaxResults(request.getLength());
		criteria.setResultTransformer(new AliasToBeanResultTransformer(DisplayReportForm.class));
		return criteria.list();
		
	}
	
	public List<Report> test(ROLE forWho,  int start, int length, int userId,DT_REPORT_STATUS reportStatus) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Report.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(length);
		Criteria staff = criteria.createCriteria("staff");
		if (forWho ==ROLE.MANAGER) {
			staff = criteria.createCriteria("managerApprove");
		}
		staff.add(Restrictions.eq("id", userId));
		
		if (reportStatus != DT_REPORT_STATUS.ALL) {
			criteria.add(Restrictions.eq("status",reportStatus));
		}
/*
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.data);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				criteria.addOrder(Order.asc(fieldName));
			}else{
				criteria.addOrder(Order.desc(fieldName));
			}
		}*/
		

		return criteria.list();
		
	}
	
	
	
	/*@Override
	public List<Report> findByPageWithUser_Desc_AtField(int start, int length, int userId, ROLE showTo, String fieldOrder) {
		List<Report> listReport = new ArrayList<Report>();
		Session session = null;
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Report.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(length);
		criteria.addOrder(Order.desc(fieldOrder));
		Criteria staff = criteria.createCriteria("staff");
		if (showTo ==ROLE.PM) {
			staff = criteria.createCriteria("managerApprove");
		}
		staff.add(Restrictions.eq("id", userId));

		return criteria.list();
	}*/

}
