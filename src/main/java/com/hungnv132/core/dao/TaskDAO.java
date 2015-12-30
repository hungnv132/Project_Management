package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.Task;
import com.hungnv132.web.controller.report.TaskForm;

public interface TaskDAO extends CommonDAO<Task>{

	public void updateBulkTask(List<TaskForm> tasks) ;
	
}
