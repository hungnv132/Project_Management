package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.Project;

public interface ProjectDAO  extends CommonDAO<Project>{
	
	public List<Project> findAllProjectName() ;
	
	public List<Project> findAllProjectNameByMemberId(int memberId) ;
	
	public Project findByIdWithoutBusiness(int id); 
}
