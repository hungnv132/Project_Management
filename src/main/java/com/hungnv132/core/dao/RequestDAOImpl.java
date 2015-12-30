package com.hungnv132.core.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.Request.REQUEST_STATUS;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AliasToBeanNestedResultTransformer;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.DatatableForm.ColumnKeys;
import com.hungnv132.core.support.DatatableForm.OrderKeys;
import com.hungnv132.core.support.DatatableForm.SearchKeys;
import com.hungnv132.web.controller.request.DatatableRequestForm;
import com.hungnv132.web.controller.request.DatatableRequestFormForAdmin;
import com.hungnv132.web.controller.request.DetailRequestController;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_STATUS;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_TYPE;
import com.hungnv132.web.controller.request.Detail_Request_For_PM_Form;

@Component
@Transactional
public class RequestDAOImpl implements RequestDAO {

	final Logger logger = LogManager.getLogger(RequestDAOImpl.class);
	
	@Inject
	SessionFactory sessionFactory;

	@Override
	public List<Request> findAll() {
		List<Request> listRequest = new ArrayList<Request>();
		Session session = sessionFactory.getCurrentSession();
		listRequest = session.createCriteria(Request.class).list();
		return listRequest;
	}

	@Override
	public List<Request> findByPage(int start, int length) {
		List<Request> listRequest = new ArrayList<Request>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM request WHERE LIMIT ? , ? ").addEntity(
					Request.class);
			sqlQuery.setParameter(0, start);
			sqlQuery.setParameter(1, length);
			listRequest = sqlQuery.list();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}

		return listRequest;
	}

	@Override
	public int count() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Request");
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public int countAllWithUser(int userId) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from Request WHERE member_request= :userId");
		query.setParameter("userId", userId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public Request findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Request) session.get(Request.class, id);
	}

	@Override
	public List<Request> findByStatus(REQUEST_STATUS status) {
		List<Request> listRequest = new ArrayList<Request>();
		Session session = sessionFactory.getCurrentSession();
		listRequest = session.createQuery("FROM request WHERE status = :status").list();
		return listRequest;
	}

	public void create(Request request) {
		Session session = sessionFactory.getCurrentSession();
		session.save(request);
	}

	public boolean update(Request request) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(request);
		} catch (HibernateException e) {
			return false; 
		}
		return true;
		
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Request  request = (Request)session.get(Request.class, id);
		try {
			session.delete(request);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public RequestType findRequestTypeById(int requestTypeId) {
		Session session = sessionFactory.getCurrentSession();
		RequestType  requestType = (RequestType)session.get(RequestType.class, requestTypeId);
		return requestType;
	}
	// return number of request that pm have to approve , find by manager_approve
	
	@Override
	public List<RequestType> findAllRequestType(){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(RequestType.class);
		return criteria.list();
	}
	
	@Override
	public int countRequestForPMApprove(int pmId){
		Session session = null;
		int count = 0;
		session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Request WHERE manager_approve= :pmId");
		query.setParameter("pmId", pmId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;

	}
	
	@Override
	public List<Detail_Request_For_PM_Form> findByPageWithPM(int start, int length, int pmId){
		List<Request> listRequest = new ArrayList<Request>();
		Session session = null;
		
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("request.id"),"id" );
		proList.add(Projections.property("request.createAt"),"createAt" );
		proList.add(Projections.property("request.status"),"status" );
		proList.add(Projections.property("request.requestType"),"requestType" );
		proList.add(Projections.property("request.reason"),"reason" );
		proList.add(Projections.property("staff.firstName"),"staff.firstName" );
		proList.add(Projections.property("staff.midName"),"staff.midName" );
		proList.add(Projections.property("staff.lastName"),"staff.lastName" );
		proList.add(Projections.property("staff.position"),"staff.position" );
		
		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Request.class, "request")
							.setFirstResult(start)
							.setMaxResults(length)
							.addOrder(Order.desc("request.createAt"))
							.createAlias("memberRequest", "staff")
							.createAlias("sendTo", "manager")
							.add(Restrictions.eq("pm.id", pmId))
							.setProjection(proList)
							.setResultTransformer(new AliasToBeanNestedResultTransformer(Detail_Request_For_PM_Form.class));
		return (List<Detail_Request_For_PM_Form>)criteria.list();
	}
	
	
	@Override
	public List<Request> findByPageWithUser(int start, int length, int userId, ROLE forGetting) {

		List<Request> listRequest = new ArrayList<Request>();
		Session session = null;
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Request.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(length);
		criteria.addOrder(Order.desc("createAt"));
		Criteria staff = criteria.createCriteria("memberRequest");
		if (forGetting ==ROLE.MANAGER) {
			staff = criteria.createCriteria("sendTo");
		}
		staff.add(Restrictions.eq("id", userId));

		return criteria.list();

	}

	@Override
	public int countRequestApproved(int userId) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Request WHERE member_request= :userId AND status = 'APPROVED'");
		query.setParameter("userId", userId);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}
	
	@Override
	public int countRequestThisMonthOfUser(int userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		String firstDate = sdf.format(date.getTime());

		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDate = sdf.format(date.getTime());

		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Request WHERE member_request= :userId AND (create_at BETWEEN  :firstDate AND :lastDate )");
		query.setParameter("userId", userId);
		query.setParameter("firstDate", firstDate);
		query.setParameter("lastDate", lastDate);
		count = ((Long) query.uniqueResult()).intValue();
		return count;

	}

	@Override
	public int countRequestApprovedThisMonthOfUser(int userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		String firstDate = sdf.format(date.getTime());

		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDate = sdf.format(date.getTime());

		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(id) from Request WHERE status = 'APPROVED' AND  member_request= :userId AND (create_at BETWEEN  :firstDate AND :lastDate )");
		query.setParameter("userId", userId);
		query.setParameter("firstDate", firstDate);
		query.setParameter("lastDate", lastDate);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public Request findByIdWithUser(int id, int userId) {
		return null;
	}

	@Override
	public List<Request> findByPage(DatatableForm form) {
		return null;
	}

	@Override
	public List<Request> findByPageFor(ROLE forWho, DatatableForm form) {
		DatatableRequestForm request = (DatatableRequestForm)form;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Request.class, "request");
		
		Criteria forWhoCriteria = criteria.createCriteria("memberRequest");
		if (forWho == ROLE.MANAGER) {
			forWhoCriteria= criteria.createCriteria("sendTo");
			criteria.createCriteria("memberRequest");
		}
		forWhoCriteria.add(Restrictions.eq("id", request.getForWhoId()));
		
		DT_REQUEST_STATUS requestStatus= request.getRequestStatus();
		if (requestStatus != DT_REQUEST_STATUS.ALL) {
			criteria.add(Restrictions.eq("request.status",requestStatus));
		}
		
		DT_REQUEST_TYPE requestType= request.getRequestType();
		if (requestType != DT_REQUEST_TYPE.ALL) {
			criteria.createCriteria("requestType", "requestType");
			criteria.add(Restrictions.eq("requestType.id", requestType.ordinal() + 1 ));
		}
		
//		String searchValue = form.getSearch().get(SearchKeys.value);
//		if (searchValue.trim().length()> 0) {
//			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
//			StringBuffer searchValueBuffer = new StringBuffer("%");
//			for (String value : valueList) {
//				searchValueBuffer.append(value+"%");
//			}
//			logger.info("++++++++ searchValueBuffer:"+ searchValueBuffer.toString());
//			criteria.add(Restrictions.like("reason", searchValueBuffer.toString(), MatchMode.ANYWHERE));
//		}
//		
//		for (Map<OrderKeys, String> field : request.getOrder()) {
//			int number = Integer.parseInt(field.get(OrderKeys.column));
//			String fieldName = request.getColumns().get(number).get(ColumnKeys.data);
//			String order = field.get(OrderKeys.dir);
//			if(order.equals("asc")){
//				criteria.addOrder(Order.asc(fieldName));
//			}else{
//				criteria.addOrder(Order.desc(fieldName));
//			}
//		}
		logger.info("++++++++ total before: + "+ criteria.list().size());
		criteria.setFirstResult(request.getStart());
		criteria.setMaxResults(request.getLength());
		logger.info("+++++++++ total after: + "+ criteria.list().size());
		return criteria.list();
	}

	@Override
	public DataContainer<Request> findRequestByPageFor(ROLE forWho, DatatableForm form) {
		DatatableRequestForm request = (DatatableRequestForm)form;
		DataContainer<Request> dataContainer = new DataContainer<Request>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteriaRequest = session.createCriteria(Request.class, "request");
		Criteria forWhoCriteria = null;
		if (forWho == ROLE.STAFF) {
			forWhoCriteria = criteriaRequest.createCriteria("memberRequest");
		}else{
			forWhoCriteria= criteriaRequest.createCriteria("sendTo");
			Criteria  memberRequest = criteriaRequest.createCriteria("memberRequest", "memberRequest");
			
/*			ProjectionList proList_StaffRequest = Projections.projectionList();
			proList_StaffRequest.add(Projections.property("memberRequest.firstName"));
			proList_StaffRequest.add(Projections.property("memberRequest.midName"));
			proList_StaffRequest.add(Projections.property("memberRequest.lastName"));
			
			memberRequest.setProjection(proList_StaffRequest);*/
			 
		}
		forWhoCriteria.add(Restrictions.eq("id", request.getForWhoId()));
		
		DT_REQUEST_STATUS requestStatus= request.getRequestStatus();
		if (requestStatus != DT_REQUEST_STATUS.ALL) {
			criteriaRequest.add(Restrictions.eq("request.status",requestStatus));
		}
		
		DT_REQUEST_TYPE requestType= request.getRequestType();
		if (requestType != DT_REQUEST_TYPE.ALL) {
			criteriaRequest.createCriteria("requestType", "requestType");
			criteriaRequest.add(Restrictions.eq("requestType.id", requestType.ordinal() + 1 ));
		}
		
		String searchValue = form.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			StringBuffer searchValueBuffer = new StringBuffer("%");
			for (String value : valueList) {
				searchValueBuffer.append(value+"%");
			}
			logger.info("++++++++ searchValueBuffer:"+ searchValueBuffer.toString());
			criteriaRequest.add(Restrictions.like("reason", searchValueBuffer.toString(), MatchMode.ANYWHERE));
		}
		
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					criteriaRequest.addOrder(Order.asc("memberRequest.firstName"));
					criteriaRequest.addOrder(Order.asc("memberRequest.midName"));
					criteriaRequest.addOrder(Order.asc("memberRequest.lastName"));
				}else{
					criteriaRequest.addOrder(Order.asc(fieldName));
				}
			}else{
				if (fieldName.equals("fullName")) {
					criteriaRequest.addOrder(Order.desc("memberRequest.firstName"));
					criteriaRequest.addOrder(Order.desc("memberRequest.midName"));
					criteriaRequest.addOrder(Order.desc("memberRequest.lastName"));
				}else{
					criteriaRequest.addOrder(Order.desc(fieldName));
				}
				
			}
		}
		logger.info("++++++++ total before: + "+ criteriaRequest.list().size());
		dataContainer.setTotal(criteriaRequest.list().size());
		criteriaRequest.setFirstResult(request.getStart());
		criteriaRequest.setMaxResults(request.getLength());
		logger.info("+++++++++ total after: + "+ criteriaRequest.list().size());
		dataContainer.setData(criteriaRequest.list());
		return dataContainer;
	}

	@Override
	public DataContainer<Request> findRequestByPageForAdmin( DatatableForm form){
		DatatableRequestFormForAdmin request = (DatatableRequestFormForAdmin)form;
		DataContainer<Request> dataContainer = new DataContainer<Request>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteriaRequest = session.createCriteria(Request.class, "request");
		criteriaRequest.createCriteria("memberRequest","memberRequest");
		criteriaRequest.add(Restrictions.eq("memberRequest.id", request.getStaffId()));
		
		DT_REQUEST_STATUS requestStatus= request.getRequestStatus();
		if (requestStatus != DT_REQUEST_STATUS.ALL) {
			criteriaRequest.add(Restrictions.eq("request.status",requestStatus));
		}
		
		DT_REQUEST_TYPE requestType= request.getRequestType();
		if (requestType != DT_REQUEST_TYPE.ALL) {
			criteriaRequest.createCriteria("requestType", "requestType");
			criteriaRequest.add(Restrictions.eq("requestType.id", requestType.ordinal() + 1 ));
		}
		
		String searchValue = form.getSearch().get(SearchKeys.value);
		if (searchValue.trim().length()> 0) {
			List<String> valueList = AppUtils.separateStringToList(searchValue, ' ');
			StringBuffer searchValueBuffer = new StringBuffer("%");
			for (String value : valueList) {
				searchValueBuffer.append(value+"%");
			}
			logger.info("++++++++ searchValueBuffer:"+ searchValueBuffer.toString());
			criteriaRequest.add(Restrictions.like("reason", searchValueBuffer.toString(), MatchMode.ANYWHERE));
		}
		
		for (Map<OrderKeys, String> field : request.getOrder()) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = request.getColumns().get(number).get(ColumnKeys.name);
			String order = field.get(OrderKeys.dir);
			if(order.equals("asc")){
				if (fieldName.equals("fullName")) {
					criteriaRequest.addOrder(Order.asc("memberRequest.firstName"));
					criteriaRequest.addOrder(Order.asc("memberRequest.midName"));
					criteriaRequest.addOrder(Order.asc("memberRequest.lastName"));
				}else{
					criteriaRequest.addOrder(Order.asc(fieldName));
				}
			}else{
				if (fieldName.equals("fullName")) {
					criteriaRequest.addOrder(Order.desc("memberRequest.firstName"));
					criteriaRequest.addOrder(Order.desc("memberRequest.midName"));
					criteriaRequest.addOrder(Order.desc("memberRequest.lastName"));
				}else{
					criteriaRequest.addOrder(Order.desc(fieldName));
				}
				
			}
		}
		logger.info("++++++++ total before: + "+ criteriaRequest.list().size());
		dataContainer.setTotal(criteriaRequest.list().size());
		criteriaRequest.setFirstResult(request.getStart());
		criteriaRequest.setMaxResults(request.getLength());
		logger.info("+++++++++ total after: + "+ criteriaRequest.list().size());
		dataContainer.setData(criteriaRequest.list());
		return dataContainer;
	}

}


