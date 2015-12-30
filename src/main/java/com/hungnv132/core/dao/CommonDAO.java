package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;

public interface CommonDAO<T> {
	
	
	public void create(T object);
	public boolean update(T object);
	public boolean delete(int id);
	
	public List<T> findAll();
	public List<T> findByPage(int start, int length);
	public List<T> findByPage(DatatableForm form);
	public List<T> findByPageWithUser(int start, int length, int userId, ROLE  role);
	public List<T> findByPageFor(ROLE  role, DatatableForm form);
	public T findById(int id);
	public T findByIdWithUser(int id, int userId);
	
	public int count();
	public int countAllWithUser(int userId); //count all reports, requests that belong to UserID
	
}
