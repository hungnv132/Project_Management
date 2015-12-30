package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.project.AvailableMemberForm;

public interface UserDAO extends CommonDAO<User>{

	public enum FETCH_LAZY_FIELD {
		REPORT_OF_STAFF, REPORT_FOR_APPROVING, ALL, NONE
	};

	
	 User findByEmail(String email);
	 User findByEmailAndPassword(String email, String password) ;
	 User findByPassword( String password) ;
	 User findByEmailButNotSelf(String email, int id) ;
	 User findById(int id, FETCH_LAZY_FIELD fetchLazyField);
	 List<User> findByRole(ROLE role);	 
	 List<User> findAllByRole(ROLE role);	 
	 DataContainer<AvailableMemberForm>  findAvailableMember(DatatableForm form) ;
	 
	 List<User> findManagersOfMember(int memberId);	 
	 
}
