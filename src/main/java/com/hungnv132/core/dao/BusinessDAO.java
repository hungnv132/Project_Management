package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.Business;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;

public interface BusinessDAO {
	
	public void create(Business object);
	public boolean update(Business object);
	public boolean delete(int id);
	
	public List<Business> findAll();
	public Business findById(int id);
	
	public int count();
	
	
}
