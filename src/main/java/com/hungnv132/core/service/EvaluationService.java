package com.hungnv132.core.service;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.dao.EvaluationDAO;
import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Evaluation;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.JoinedUser;
import com.hungnv132.core.support.Pagination;
import com.hungnv132.web.controller.evaluation.CreateEvaluationForm;
import com.hungnv132.web.controller.evaluation.DatatableEvaluationForm;
import com.hungnv132.web.controller.evaluation.DatatableEvaluationFormForAdmin;
import com.hungnv132.web.controller.evaluation.DisplayEvaluationForm;
import com.hungnv132.web.controller.evaluation.UpdateEvaluationForm;

@Service
@Transactional
public class EvaluationService  {

	final Logger logger = LogManager.getLogger(EvaluationService.class);
	@Inject
	EvaluationDAO evaluationDAO;

	public void createNewEvaluation(CreateEvaluationForm form) {
//		BonusPoint bonusPoint = new BonusPoint();
//		BeanUtils.copyProperties(form, bonusPoint);
		
		User staff = new User(form.getStaffId());
		User manager = new User(form.getManagerId());
		Project project = new Project(form.getProjectId());
		
		Evaluation evaluation = new Evaluation();
		evaluation.setDefaultData();
		BeanUtils.copyProperties(form, evaluation);
//		evaluation.setBonusPoint(bonusPoint);
		evaluation.setStaff(staff);
		evaluation.setProject(project);
		evaluation.setEvaluatedBy(manager);
		
//		bonusPoint.setEvaluation(evaluation);
		evaluationDAO.create(evaluation);
	}

	public Evaluation findById(int id) {
		return evaluationDAO.findById(id);
	}
	
	public Evaluation findByIdWithUser(int id, JoinedUser user) {
		return evaluationDAO.findByIdWithUser(id, user.getId());
	}

	public Pagination<Evaluation> getPage(int draw, int start, int length) {
		return new Pagination<Evaluation>(draw, start, length, evaluationDAO);
	}
	
	public Pagination<Evaluation> getEvaluationPageForSTAFF(DatatableForm form) {
		return new Pagination<Evaluation>(ROLE.STAFF,form, evaluationDAO);
	}

	
	public Pagination<Evaluation> getEvaluationPage(DatatableEvaluationForm form) {
		DataContainer<Evaluation>  dataContainer =  evaluationDAO.findEvaluationByPage(form);
		Pagination<Evaluation> page = new Pagination<Evaluation>();
		page.setDraw(form.getDraw());
		page.setData(dataContainer.getData());
		page.setRecordsTotal(dataContainer.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	public Pagination<DisplayEvaluationForm> getAllEvaluationByUserId(DatatableEvaluationFormForAdmin form) {
		DataContainer<DisplayEvaluationForm>  dataContainer =  evaluationDAO.findAllByUserId(form);
		Pagination<DisplayEvaluationForm> page = new Pagination<DisplayEvaluationForm>();
		page.setDraw(form.getDraw());
		page.setData(dataContainer.getData());
		page.setRecordsTotal(dataContainer.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	public void createNewEvaluation(Evaluation evaluation) {
		evaluation.setDefaultData();
		evaluationDAO.create(evaluation);

	}

	public void updateEvaluation(UpdateEvaluationForm form) {
		Evaluation evaluation= evaluationDAO.findById(form.getId());
		
		User staff = new User(form.getStaffId());		
		Project project = new Project(form.getProjectId());
		
		evaluation.setUpdateAt(new LocalDateTime());
		BeanUtils.copyProperties(form, evaluation);
		evaluation.setStaff(staff);
		evaluation.setProject(project);
		evaluationDAO.update(evaluation);
	}
	
	
	public boolean deleteEvaluation(int id){
		return evaluationDAO.delete(id);
	}
	

}
