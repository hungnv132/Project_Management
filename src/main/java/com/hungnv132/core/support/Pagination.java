package com.hungnv132.core.support;

import java.util.List;

import javax.inject.Inject;

import com.google.gson.annotations.Expose;
import com.hungnv132.core.dao.CommonDAO;
import com.hungnv132.core.dao.MyRepository;
import com.hungnv132.core.dao.ReportDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.service.UserService;

public class Pagination<T> {
	@Expose
	private int draw;
	@Expose
	private int recordsTotal;
	@Expose
	private int recordsFiltered;
	@Expose
	private List<T> data;
	

	
	//  get sublist data a totally table, not filter
	public Pagination(int draw,int start, int length, CommonDAO<T> dao) { 
		this.draw =draw;
		this.recordsTotal = dao.count();
		this.data = dao.findByPage(start, length);
		this.recordsFiltered = this.recordsTotal;
	}
	
	
	// get sublist data that have filter by user_report or manager_approve,
	public Pagination(int draw,int start, int length, CommonDAO<T> dao, int userId, ROLE roleForGetting) {
		this.draw =draw;
		this.recordsTotal = dao.countAllWithUser(userId);
		this.data = dao.findByPageWithUser(start, length, userId, roleForGetting);
		this.recordsFiltered = this.recordsTotal;
		
	}
	/*public Pagination(int draw,int start, int length, CommonDAO<T> dao, int userId, ROLE roleForGetting, String filed) {
		this.draw =draw;
		this.recordsTotal = dao.countAllWithUser(userId);
		this.data = dao.findByPageWithUser-(start, length, userId, roleForGetting);
		this.recordsFiltered = this.recordsTotal;
		
	}*/
	
	
	
	public Pagination(ROLE forWho, DatatableForm form, CommonDAO<T> dao) {
		this.draw =form.getDraw();
		this.recordsTotal = dao.countAllWithUser(AppUtils.getJoinedUser().getId());
		this.data = dao.findByPageFor(forWho, form);
		this.recordsFiltered = this.recordsTotal;
		
	}
	public Pagination(){
	}
	
	
	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
