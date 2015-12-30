package com.hungnv132.core.dao;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Evaluation;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.evaluation.DisplayEvaluationForm;

public interface EvaluationDAO  extends CommonDAO<Evaluation>{
	
	public DataContainer<Evaluation> findEvaluationByPage(DatatableForm form);
	public DataContainer<DisplayEvaluationForm> findAllByUserId(DatatableForm form);
}
